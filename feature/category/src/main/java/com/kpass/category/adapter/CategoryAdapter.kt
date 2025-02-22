package com.kpass.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.keep.common.extension.hide
import com.keep.common.extension.show
import com.kpass.feature.category.databinding.CategoryItemBinding
import com.kpass.feature.category.databinding.EmptyItemBinding

class CategoryAdapter (
    private val eventListener : CategoryAdapterEvent,
) : ListAdapter<CategoryListAdapterItem, CategoryAdapter.CategoryViewHolder>(DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return when (EnumCategoryListAdapterViewType.getEnumByOrdinal(viewType)) {
            EnumCategoryListAdapterViewType.CATEGORY -> {
                CategoryViewHolder.CategoryItem(
                    CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    eventListener,
                )
            }
            EnumCategoryListAdapterViewType.EMPTY -> {
                CategoryViewHolder.EmptyItem(
                    EmptyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                )
            }
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = when (holder) {
        is CategoryViewHolder.CategoryItem -> {
            holder.bind(
                getItem(position) as CategoryListAdapterItem.CategoryItem
            )
        }

        is CategoryViewHolder.EmptyItem -> {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItem(position).viewType.ordinal
    }

    sealed class CategoryViewHolder(
        binding: ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        class CategoryItem (
            val binding: CategoryItemBinding,
            private val eventListener: CategoryAdapterEvent,
        ) : CategoryViewHolder(binding) {

            fun bind(categoryItem: CategoryListAdapterItem.CategoryItem) {
                with(binding) {
                    tvCategory.apply {
                        text = categoryItem.category.name
                    }

                    if (eventListener.reorderCategory() == true) {
                        dragBtn.show()
                    }  else {
                        dragBtn.hide()
                        root.setOnClickListener {
                            eventListener.toDetailScreen(categoryItem.category)
                            true
                        }
                    }
                }
            }
        }

        class EmptyItem(
            val binding: EmptyItemBinding,
        ) : CategoryViewHolder(binding) {
            fun bind() {
                with(binding) {
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
                        oldItem.category.position == newItem.category.position

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
