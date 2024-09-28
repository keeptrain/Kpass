package com.keep.category

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.databinding.FragmentAddCategoryDialogBinding
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddCategoryDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val CATEGORY_EXTRA_KEY = "category_extra"
    }


    private lateinit var binding : FragmentAddCategoryDialogBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryActivityViewModel::class.java]
    }


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
        binding = FragmentAddCategoryDialogBinding.inflate(inflater,container,false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        val titleText = category?.let {
            R.string.title_edit_category
        } ?: R.string.title_create_category
        binding.tvTitleDialog.text = requireContext().getString(titleText)

        binding.ivClose.setOnClickListener {
            dismiss()
        }

        val addButtonText = category?.let {
            R.string.save
        } ?: R.string.add
        binding.btnAdd.text = requireContext().getString(addButtonText)

        category?.let {
            binding.edtCategory.setText(it.name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupListener()
    }

    private fun setupObserver() {
        viewModel.apply {
            validationResult.observe(viewLifecycleOwner){ result ->
                result.getContentIfNotHandled()?.let { uiText ->
                    binding.edlCategory.error = uiText.errorMessage?.asString(requireContext())
                }
            }
            insertResult.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { result ->
                    if (result) {
                        dismissNow()
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            val categoryName = binding.edtCategory.text.toString()

            viewModel.isCategoryNameExists(categoryName) { exist ->
                if (exist) {
                    binding.edlCategory.error = getString(R.string.exist_category)
                } else {
                    category?.let {
                        val categoryCopy = it.copy(name = categoryName)
                        if (category != categoryCopy) {
                            viewModel.insertCategoryWithFieldsValidation(categoryCopy)
                        }
                    } ?: viewModel.insertCategoryWithFieldsValidation(Category(name = categoryName))
                }
            }
        }
    }

}