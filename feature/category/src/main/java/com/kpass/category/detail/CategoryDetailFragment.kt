package com.kpass.category.detail

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.keep.model.Category
import com.kpass.category.CategoryFragment.Companion.CATEGORY_EXTRA_KEY
import com.kpass.category.dialog.UpsertCategoryBottomDialog
import com.kpass.feature.category.R
import com.kpass.feature.category.databinding.FragmentCategoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailFragment : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryDetailViewModel by viewModels()

    @Suppress("DEPRECATION")
    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CATEGORY_EXTRA_KEY,Category::class.java)
        } else {
            arguments?.getParcelable(CATEGORY_EXTRA_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        setupToolbar(category)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupToolbar(category: Category?) {
        with(binding) {

            toolbarCategory.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbarCategory.apply {

                title = category?.name

                menu.findItem(R.id.action_reorder).isVisible = false
                menu.findItem(R.id.action_edit).isVisible = true
                menu.findItem(R.id.action_delete).isVisible = true
            }

            toolbarCategory.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_edit -> {
                        showUpsertCategoryBottomSheet(category)
                    }
                    R.id.action_delete -> {
                        if (category!=null) {
                            viewModel.deleteCategory(category)
                            findNavController().popBackStack()
                        }
                    }
                }
                true
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            tvCategoryName.text = category?.name
        }
    }

    private fun showUpsertCategoryBottomSheet(category: Category? = null) {
        val addCategoryDialogFragment = UpsertCategoryBottomDialog().apply {
            arguments = Bundle().apply {
                category?.let {
                    putParcelable(CATEGORY_EXTRA_KEY, category)
                }
            }
        }
        addCategoryDialogFragment.show(parentFragmentManager, "EditCategoryDialogFragment")
    }

    companion object {
        const val CATEGORY_DETAIL_EXTRA_KEY = "category_detail_extra"
    }
}