package com.keep.category

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep.category.dialog.BottomCategoryDialogFragment.BottomSheetState
import com.keep.category.adapter.CategoryAdapter
import com.keep.category.adapter.CategoryAdapterEvent
import com.keep.category.adapter.CustomItemTouchHelperCallback
import com.keep.category.databinding.ActivityCategoryBinding
import com.keep.category.dialog.BottomCategoryDialogFragment
import com.keep.category.dialog.ReorderCategoryBottomDialog
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryActivity : AppCompatActivity(),CategoryAdapterEvent {

    private lateinit var binding : ActivityCategoryBinding

    private val viewModel : CategoryActivityViewModel by viewModels()

    private var categoryAdapter = CategoryAdapter(this@CategoryActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                onBackPressedDispatcher.onBackPressed()
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.observe(this@CategoryActivity) { listCategory ->
                    categoryAdapter.submitList(
                        viewModel.generateCategoryAdapterList(listCategory)
                    )
                }
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
        addCategoryDialogFragment.show(supportFragmentManager, "AddCategoryDialogFragment")
    }

    private fun showMoreCategoryBottomSheet(category: Category) {
        val moreCategoryDialogFragment = BottomCategoryDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BottomCategoryDialogFragment.Companion.CATEGORY_EXTRA_KEY, category)
            }
        }
        moreCategoryDialogFragment.switchState(BottomSheetState.MORE)
        moreCategoryDialogFragment.show(supportFragmentManager, "MoreCategoryDialogFragment")

    }

    private fun showReorderCategoryBottomSheet() {
        val bottomSheet = ReorderCategoryBottomDialog(viewModel,categoryAdapter,this)
        bottomSheet.show(supportFragmentManager, "ReorderCategoryBottomDialog")
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}