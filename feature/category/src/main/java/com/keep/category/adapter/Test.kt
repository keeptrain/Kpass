package com.keep.category.adapter

import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.widget.AlertDialogLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryActivityViewModel

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


}
