package com.kpass.category.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.model.Category
import com.kpass.category.CategoryFragment.Companion.CATEGORY_EXTRA_KEY
import com.kpass.category.CategoryViewModel
import com.kpass.feature.category.R
import com.kpass.feature.category.databinding.FragmentInsertBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpsertCategoryBottomDialog : BottomSheetDialogFragment() {

    private var _insertBinding: FragmentInsertBottomSheetDialogBinding? = null
    private val insertBinding get() = _insertBinding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
    }

    @Suppress("DEPRECATION")
    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CATEGORY_EXTRA_KEY,Category::class.java)
        } else {
            arguments?.getParcelable(CATEGORY_EXTRA_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _insertBinding = FragmentInsertBottomSheetDialogBinding.inflate(inflater,container,false)
        return insertBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsertView()
        setupObserver()
        setupListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _insertBinding = null
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
            com.keep.password.core.common.R.string.save
        } ?: com.keep.password.core.common.R.string.add
        insertBinding.btnAdd.text = requireContext().getString(addButtonText)

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
        lifecycleScope.launch {
            val lastPosition = viewModel.getLastPosition()
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
                        } ?: viewModel.insertCategoryWithFieldsValidation(
                            Category(
                                name = categoryName, position = lastPosition
                            )
                        )
                    }
                }
            }
        }
    }
}
