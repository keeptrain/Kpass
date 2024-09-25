package com.keep.category

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.databinding.FragmentAddCategoryDialogBinding
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val CATEGORY_EXTRA_KEY = "category_extra"
    }

    private lateinit var binding : FragmentAddCategoryDialogBinding


    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(CATEGORY_EXTRA_KEY,Category::class.java)
        } else {
            arguments?.getSerializable(CATEGORY_EXTRA_KEY) as? Category
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCategoryDialogBinding.inflate(inflater,container,false)
        return binding.root
    }
}