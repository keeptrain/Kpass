package com.keep.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.keep.category.databinding.CategoryItemBinding
import com.keep.model.Category

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
        //val viewBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        //return CategoryViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = when (holder) {
        is CategoryViewHolder.CategoryItem -> {
            holder.bind(
                getItem(position) as CategoryListAdapterItem.CategoryItem)
        }
        /*val category = getItem(position)
        when (holder) {
            is CategoryViewHolder.CategoryItem -> {
                holder.bind(
                    getItem(position) as CategoryListAdapterItem.CategoryItem)
            }
        }*/
        else -> {}
    }

    sealed class CategoryViewHolder(
        binding : ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        class CategoryItem (
            private val binding: CategoryItemBinding,
            private val eventListener : CategoryAdapterEvent) : CategoryViewHolder(binding){

                fun bind(categoryItem : CategoryListAdapterItem.CategoryItem) {
                    binding.category = categoryItem.category
                    binding.eventListener = eventListener
                    binding.tvCategory.text = categoryItem.category.name
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
                    oldItem is CategoryListAdapterItem.CategoryItem && newItem is CategoryListAdapterItem.CategoryItem -> {
                        return when {
                            oldItem.category.id != newItem.category.id -> false
                            oldItem.category.id != newItem.category.name -> false
                            else -> true
                        }
                    }

                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: CategoryListAdapterItem,
                newItem: CategoryListAdapterItem
            ): Boolean {
                return when {
                    oldItem is CategoryListAdapterItem.CategoryItem && newItem is CategoryListAdapterItem.CategoryItem -> {
                        return when {
                            oldItem.category.id != newItem.category.id -> false
                            oldItem.category.id != newItem.category.name -> false
                            else -> true
                        }
                    }

                    else -> false
                }
            }
        }
    }
}



// Callback for calculating the diff between two non-null items in a list.
class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
