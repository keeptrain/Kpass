package com.keep.category.dialog


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryActivityViewModel
import com.keep.category.R
import com.keep.category.databinding.FragmentInsertBottomSheetDialogBinding
import com.keep.category.databinding.FragmentMoreBottomSheetDialogBinding

import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BottomCategoryDialogFragment : BottomSheetDialogFragment() {

    private var _insertBinding: FragmentInsertBottomSheetDialogBinding? = null
    private val insertBinding get() = _insertBinding!!

    private var _moreBinding: FragmentMoreBottomSheetDialogBinding? = null
    private val moreBinding get() = _moreBinding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryActivityViewModel::class.java]
    }

    private var currentState: BottomSheetState = BottomSheetState.INSERT

    @Suppress("DEPRECATION")
    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(CATEGORY_EXTRA_KEY,Category::class.java)
        } else {
            arguments?.getSerializable(CATEGORY_EXTRA_KEY) as? Category
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (currentState) {
            BottomSheetState.INSERT -> {
                _insertBinding = FragmentInsertBottomSheetDialogBinding.inflate(inflater,container,false)
                setupInsertView()
                _insertBinding?.root
            }

            BottomSheetState.MORE -> {
                _moreBinding = FragmentMoreBottomSheetDialogBinding.inflate(inflater,container,false)
                setupMoreView()
                _moreBinding?.root
            }
        } as View?
    }

    private fun setupInsertView() {
        insertBinding.ivClose.setOnClickListener {
            dismiss()
        }

        val titleText = category?.let {
            R.string.title_edit_category
        } ?: R.string.title_create_category

        val hintEditLayout = category?.let {
            R.string.hint_edit_category
        } ?: R.string.hint_create_category

        insertBinding.tvTitleDialog.text = requireContext().getString(titleText)

        insertBinding.edlCategory.hint = requireContext().getString(hintEditLayout)

        category?.let {
            insertBinding.edtCategory.setText(it.name)
        }

        val addButtonText = category?.let {
            R.string.save
        } ?: R.string.add
        insertBinding.btnAdd.text = requireContext().getString(addButtonText)

    }

    private fun setupMoreView() {
        moreBinding.apply {
            // Setup views for MORE state
            editOption.setOnClickListener {
                switchState(BottomSheetState.INSERT).let {
                    setupObserver()
                    setupListener()
                }
                viewModel.updateCategory(category!!)
            }

            deleteOption.setOnClickListener {
                viewModel.deleteCategory(category!!)
                dismiss()
            }
        }
    }

    private fun setupObserver() {
        viewModel.apply {
            validationResult.observe(viewLifecycleOwner){ result ->
                result.getContentIfNotHandled()?.let { uiText ->
                    insertBinding.edlCategory.error = uiText.errorMessage?.asString(requireContext())
                }
            }
            insertResult.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { result ->
                    if (result) {
                        dismissAllowingStateLoss()
                    }
                }
            }

            resetErrorEvent.observe(viewLifecycleOwner) {
                insertBinding.edlCategory.error = null
            }
        }
    }

    private fun setupListener() {
        insertBinding.btnAdd.setOnClickListener {
            val categoryName = insertBinding.edtCategory.text.toString()
            viewModel.isCategoryNameExists(categoryName) { exist ->
                if (exist) {
                    insertBinding.edlCategory.error = getString(R.string.exist_category)
                } else {
                    category?.let {
                        val categoryCopy = it.copy(name = categoryName)
                        if (category != categoryCopy) {
                            viewModel.insertCategoryWithFieldsValidation(categoryCopy)
                        }
                    } ?: viewModel.insertCategoryWithFieldsValidation(Category(
                        name = categoryName))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (currentState) {
            BottomSheetState.INSERT -> {
                setupObserver()
                setupListener()
            }
            BottomSheetState.MORE -> {
                setupMoreView()
            }
        }
    }

    fun switchState(state: BottomSheetState) {
        currentState = state
        // Rekreasi ulang tampilan berdasarkan state baru
        _insertBinding = null
        _moreBinding = null
        dialog?.setContentView( onCreateView(layoutInflater, null, null)!! )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _insertBinding = null
        _moreBinding = null
    }

    companion object {
        const val CATEGORY_EXTRA_KEY = "category_extra"
    }

    enum class BottomSheetState {
        INSERT,
        MORE
    }

}

