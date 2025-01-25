package com.keep.category.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.keep.category.CategoryActivity
import com.keep.category.databinding.CategoryItemBinding
import com.keep.category.databinding.EmptyItemBinding


class CategoryAdapter (
    private val eventListener : CategoryAdapterEvent
) : ListAdapter<CategoryListAdapterItem, CategoryAdapter.CategoryViewHolder>(DIFFUTILS) {

    /* Membuat tampilan awal untuk setiap item dalam RecyclerView */
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
            holder.bind(
                getItem(position) as CategoryListAdapterItem.EmptyItem
            )
        }
    }

    sealed class CategoryViewHolder(
        binding: ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        class CategoryItem(
            val binding: CategoryItemBinding,
            private val eventListener: CategoryAdapterEvent,
        ) : CategoryViewHolder(binding) {
            fun bind(categoryItem: CategoryListAdapterItem.CategoryItem) {
                with(binding) {
                    tvCategory.apply {
                        text = categoryItem.category.name
                    }
                    root.setOnClickListener {
                        val intent = Intent(root.context, CategoryActivity::class.java)
                        true
                    }
                    root.setOnLongClickListener {
                        eventListener.onMoreClick(categoryItem.category)
                        true
                    }
                }
            }
        }

        class EmptyItem(
            private val binding: EmptyItemBinding,
        ) : CategoryViewHolder(binding) {
            fun bind(categoryItem: CategoryListAdapterItem.EmptyItem) {
                binding.tvCategory.text = ""
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
