package com.keep.category.dialog


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryActivityViewModel
import com.keep.model.Category
import com.keep.password.feature.category.R
import com.keep.password.feature.category.databinding.FragmentInsertBottomSheetDialogBinding
import com.keep.password.feature.category.databinding.FragmentMoreBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BottomCategoryDialogFragment : BottomSheetDialogFragment() {

    private var _insertBinding: FragmentInsertBottomSheetDialogBinding? = null
    private val insertBinding get() = _insertBinding!!

    private var _moreBinding: FragmentMoreBottomSheetDialogBinding? = null
    private val moreBinding get() = _moreBinding!!

//    private var _reorderBinding: FragmentReoderBottomSheetDialogBinding? = null
//    private val reorderBinding get() = _reorderBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryActivityViewModel::class.java]
    }

    private var currentState: BottomSheetState = BottomSheetState.INSERT

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

//            BottomSheetState.REORDER -> {
//                _reorderBinding = FragmentReoderBottomSheetDialogBinding.inflate(inflater,container,false)
//                _reorderBinding?.root
//                val dialog = dialog as? BottomSheetDialog
//                val bottomSheet =
//                    dialog?.findViewById<View>(R.id.constraint)
//
//                if (bottomSheet != null) {
//                    // Terapkan BottomSheetBehavior
//                    val behavior = BottomSheetBehavior.from(bottomSheet)
//
//                    behavior.apply {
//                        state = BottomSheetBehavior.STATE_EXPANDED // Set expanded
//                    }
//                } else {
//                    Log.e("BottomSheet", "Failed to find bottom sheet view!")
//                }
//            }
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
            }

            deleteOption.setOnClickListener {
                viewModel.deleteCategory(category!!)
                dismiss()
            }
        }
    }

//    private fun setupReorderView() {
//        reorderBinding.apply {
//
//
//
//        }
//    }

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
//            BottomSheetState.REORDER -> {// Ambil BottomSheet dari dialog
//            }
        }
    }

    fun switchState(state: BottomSheetState) {
        currentState = state

        // Rekreasi ulang tampilan berdasarkan state baru
        _insertBinding = null
        _moreBinding = null
//        _reorderBinding = null
        dialog?.setContentView(onCreateView(layoutInflater,null,null)!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _insertBinding = null
        _moreBinding = null
//        _reorderBinding = null
    }

    enum class BottomSheetState {
        INSERT,
        MORE,
        //REORDER
    }

    companion object {
        const val CATEGORY_EXTRA_KEY = "category_extra"
    }
}
