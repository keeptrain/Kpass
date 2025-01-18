package com.keep.category.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.keep.category.databinding.CategoryItemBinding


class CategoryAdapter (private val eventListener : CategoryAdapterEvent)
    : ListAdapter<CategoryListAdapterItem, CategoryAdapter.CategoryViewHolder>(DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return when (EnumCategoryListAdapterViewType.getEnumByOrdinal(viewType)) {
            EnumCategoryListAdapterViewType.CATEGORY -> {
                CategoryViewHolder.CategoryItem(
                    CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),
                    eventListener
                )
            }
            EnumCategoryListAdapterViewType.EMPTY -> TODO()
        }
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = when (holder) {
        is CategoryViewHolder.CategoryItem -> {
            holder.bind(
                getItem(position) as CategoryListAdapterItem.CategoryItem)
        }
        else -> {}
    }

    sealed class CategoryViewHolder(
        binding : ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        class CategoryItem (
            private val binding: CategoryItemBinding,
            private val eventListener : CategoryAdapterEvent
        ) : CategoryViewHolder(binding){
            fun bind(categoryItem : CategoryListAdapterItem.CategoryItem) {
                with(binding) {
                    tvCategory.apply {
                        text = categoryItem.category.name

                    }
                    root.setOnLongClickListener {
                        eventListener.onMoreClick(categoryItem.category)
                        true
                    }
                }
            }
        }
    }

    companion object {
        val DIFFUTILS = object : DiffUtil.ItemCallback<CategoryListAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: CategoryListAdapterItem,
                newItem: CategoryListAdapterItem
            ): Boolean {
                return when {
                    oldItem is CategoryListAdapterItem.CategoryItem && newItem is CategoryListAdapterItem.CategoryItem ->
                        oldItem.category.id == newItem.category.id
                    oldItem is CategoryListAdapterItem.EmptyItem && newItem is CategoryListAdapterItem.EmptyItem -> true
                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: CategoryListAdapterItem,
                newItem: CategoryListAdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
