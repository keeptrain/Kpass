package com.kpass.newentry.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.keep.password.feature.newentry.databinding.EntryFieldItemBinding

class EntryFieldAdapter(
) : ListAdapter<EntryFieldAdapterItem, EntryFieldAdapter.EntryFieldsViewHolder>(DIFFUTILS){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntryFieldsViewHolder {
        return when (EnumEntryFieldViewType.getEnumByOrdinal(viewType)) {
            EnumEntryFieldViewType.ENTRY_FIELDS -> {
                EntryFieldsViewHolder.EntryFieldsItem(
                    EntryFieldItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            EnumEntryFieldViewType.EMPTY -> {
                EntryFieldsViewHolder.EmptyEntryFieldsItem(
                    EntryFieldItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: EntryFieldsViewHolder,
        position: Int
    ) {
        when(holder) {
            is EntryFieldsViewHolder.EntryFieldsItem -> {
                holder.bind(
                    getItem(position) as EntryFieldAdapterItem.EntryFieldsItem
                )
            }
            is EntryFieldsViewHolder.EmptyEntryFieldsItem -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItem(position).viewType.ordinal
    }

    sealed class EntryFieldsViewHolder(
        binding : ViewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        class EntryFieldsItem(
            val binding : EntryFieldItemBinding
        ) : EntryFieldsViewHolder(binding) {
            fun bind(entryFieldsItem: EntryFieldAdapterItem.EntryFieldsItem) {
                with(binding) {
                    edlUsername.hint = entryFieldsItem.entryFields.fieldName
                }
            }
        }

        class EmptyEntryFieldsItem(
            binding : EntryFieldItemBinding
        ) : EntryFieldsViewHolder(binding) {
            fun bind() {
            }
        }
    }

    companion object {
        val DIFFUTILS = object : DiffUtil.ItemCallback<EntryFieldAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: EntryFieldAdapterItem,
                newItem: EntryFieldAdapterItem
            ): Boolean {
                return when {
                    oldItem is EntryFieldAdapterItem.EntryFieldsItem && newItem is EntryFieldAdapterItem.EntryFieldsItem -> true
                    oldItem is EntryFieldAdapterItem.EmptyItem && newItem is EntryFieldAdapterItem.EmptyItem -> true
                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: EntryFieldAdapterItem,
                newItem: EntryFieldAdapterItem
            ): Boolean {
                return oldItem != newItem
            }
        }
    }
}