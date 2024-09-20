package com.keep.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keep.category.CategoryActivity
import com.keep.category.databinding.CategoryItemBinding
import com.keep.model.Category

class CategoryAdapter (private val eventListener : CategoryAdapterEvent)
    : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val viewBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(viewBinding,eventListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    class CategoryViewHolder(
        private val binding : CategoryItemBinding,
        val eventListener: CategoryAdapterEvent
    ) : RecyclerView.ViewHolder(binding.root) {
        //private val textView: TextView = itemView.findViewById(com.keep.category.R.id.tv_category)
        //private val moreBtn = itemView.findViewById<Button>(com.keep.category.R.id.more_btn)

        fun bind(category: Category) {
            with(binding) {
                tvCategory.text = category.name
            }
            /*binding.tvCategory.text = category.name
            moreBtn.setOnClickListener {
                //onMoreButtonClick(category)
            }*/

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
