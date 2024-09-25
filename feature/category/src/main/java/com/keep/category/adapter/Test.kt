package com.keep.category.adapter

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.widget.AlertDialogLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryActivityViewModel
import com.keep.category.NewCategoryDialog.Companion.TASK_EXTRA_KEY
import com.keep.model.Category

class Test :BottomSheetDialogFragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CategoryActivityViewModel::class.java]
    }



    private fun test() {
        viewModel.apply {
            insertResult.observe(viewLifecycleOwner) {result ->
            }
        }
    }

    fun tests() = object : aaii {
        override fun tests(): Any {
            TODO("Not yet implemented")
        }

    }

}

interface aaii {
    fun tests(): Any
}

