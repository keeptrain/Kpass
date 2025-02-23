package com.kpass.category.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryDetailViewModel::class.java]
    }

    @Suppress("DEPRECATION")
    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(CATEGORY_DETAIL_EXTRA_KEY,Category::class.java)
        } else {
            arguments?.getParcelable(CATEGORY_DETAIL_EXTRA_KEY)
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

        viewModel.categories.observe(viewLifecycleOwner) { listCategory ->
            val categoryMap = listCategory.associateBy { it.id }
            category?.id?.let { id ->
                categoryMap[id]?.let { matchedCategory ->
                    setupToolbar(matchedCategory)
                    initListeners(matchedCategory)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupToolbar(category: Category) {
        with(binding) {

            toolbarCategory.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbarCategory.apply {

                title = category.name

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
                        if (true) {
                            viewModel.deleteCategory(category)
                            findNavController().popBackStack()
                        }
                    }
                }
                true
            }
        }
    }

    private fun initListeners(category: Category) {
        with(binding) {
            tvCategoryName.text = category.name
        }
    }

    private fun showUpsertCategoryBottomSheet(category: Category? = null) {
        val addCategoryDialogFragment = UpsertCategoryBottomDialog().apply {
            arguments = bundleOf(CATEGORY_EXTRA_KEY to category)
        }
        addCategoryDialogFragment.show(childFragmentManager, "EditCategoryDialogFragment")
    }

    companion object {
        const val CATEGORY_DETAIL_EXTRA_KEY = "category_detail_extra"
    }
}