package com.kpass.category.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.keep.model.Category
import com.kpass.category.CategoryViewModel
import java.util.Collections

class CustomItemTouchHelperCallback (
    private val viewModel: CategoryViewModel
) : ItemTouchHelper.Callback() {

    private var tempCategoryList: List<Category> = emptyList()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // Default: Tidak ada gerakan
        var dragFlags = 0

        // Periksa apakah viewHolder memiliki tombol drag
        if ((viewHolder as? CategoryAdapter.CategoryViewHolder.CategoryItem)?.binding?.dragBtn?.isPressed == true) {
            dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        }

        return makeMovementFlags(dragFlags, 0) // Swipe flags = 0
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition

        val adapter = recyclerView.adapter as? CategoryAdapter
        val currentList = adapter?.currentList?.toMutableList()

        currentList?.let {
            // Swap posisi item
            Collections.swap(it, fromPosition, toPosition)

            // Perbarui daftar sementara dengan urutan baru
            tempCategoryList = it.filterIsInstance<CategoryListAdapterItem.CategoryItem>()
                .mapIndexed { index, item ->
                    item.category.copy(position = index) // Update posisi
                }

            // Perbarui adapter
            adapter.submitList(it)
        }

        return true
    }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {}

    fun onSaveClicked() {
        viewModel.updateCategoryPosition(tempCategoryList)
    }

}