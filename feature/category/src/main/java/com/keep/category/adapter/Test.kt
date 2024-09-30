package com.keep.category.adapter

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.widget.AlertDialogLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryActivityViewModel
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

    fun tests() = object : aaii(test = "") {
        override fun tests(): Any {
            TODO("Not yet implemented")
        }

    }


}

abstract class aaii (test: String) {
    abstract fun tests(): Any
}

class aib (test:String): aaii(test = test) {
    override fun tests(): Any {
        TODO("Not yet implemented")
    }
}

class uias (val tests : Int, test2: String) {

}

fun main() {
    val uais = uias(12,"")
}

