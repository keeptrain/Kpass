package com.keep.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep.category.dialog.BottomCategoryDialogFragment.BottomSheetState
import com.keep.category.adapter.CategoryAdapter
import com.keep.category.adapter.CategoryAdapterEvent
import com.keep.category.adapter.CustomItemTouchHelperCallback
import com.keep.category.dialog.BottomCategoryDialogFragment
import com.keep.category.dialog.ReorderCategoryBottomDialog
import com.keep.model.Category
import com.keep.password.feature.category.R
import com.keep.password.feature.category.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(),CategoryAdapterEvent {

    private lateinit var binding : FragmentCategoryBinding

    private val viewModel : CategoryViewModel by viewModels()

    private var categoryAdapter = CategoryAdapter(this@CategoryFragment)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityCategoryBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupToolbar()
//        setupRecyclerView()
//        initialAdapter()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
        initialAdapter()
    }

    private fun setupToolbar() {
        with(binding) {
            btnAdd.setOnClickListener {
                addCategory()
            }

            toolbarCategory.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbarCategory.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_reorder -> {
                        showReorderCategoryBottomSheet()
                    }
                }
                true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter

            val callback = CustomItemTouchHelperCallback(viewModel)
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun initialAdapter() {
        lifecycleScope.launch {
                viewModel.categories.observe(viewLifecycleOwner) { listCategory ->
                    categoryAdapter.submitList(
                        viewModel.generateCategoryAdapterList(listCategory)
                    )
                }
        }
    }

    private fun showAddCategoryBottomSheet(category: Category? = null) {
        val addCategoryDialogFragment = BottomCategoryDialogFragment().apply {
            arguments = Bundle().apply {
                category?.let {
                    putParcelable(BottomCategoryDialogFragment.Companion.CATEGORY_EXTRA_KEY, category)
                    viewModel.insertCategory(category)
                }
            }
        }
        addCategoryDialogFragment.switchState(BottomSheetState.INSERT)
        addCategoryDialogFragment.show(parentFragmentManager, "AddCategoryDialogFragment")
    }

    private fun showMoreCategoryBottomSheet(category: Category) {
        val moreCategoryDialogFragment = BottomCategoryDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BottomCategoryDialogFragment.Companion.CATEGORY_EXTRA_KEY, category)
            }
        }
        moreCategoryDialogFragment.switchState(BottomSheetState.MORE)
        moreCategoryDialogFragment.show(parentFragmentManager, "MoreCategoryDialogFragment")

    }

    private fun showReorderCategoryBottomSheet() {
        val bottomSheet = ReorderCategoryBottomDialog(viewModel,categoryAdapter,this)
        bottomSheet.show(parentFragmentManager, "ReorderCategoryBottomDialog")
    }

    override fun addCategory() {
        showAddCategoryBottomSheet()
    }

    override fun updateCategory(category: Category) {
        showMoreCategoryBottomSheet(category)
    }

    override fun deleteCategory(category: Category) {
        showMoreCategoryBottomSheet(category)
    }

    override fun onMoreClick(category: Category) {
        showMoreCategoryBottomSheet(category)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressedDispatcher.onBackPressed()
//        return true
//    }

}