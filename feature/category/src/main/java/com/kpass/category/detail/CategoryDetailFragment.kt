package com.kpass.category.detail

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keep.model.Category
import com.kpass.feature.category.R
import com.kpass.feature.category.databinding.FragmentCategoryDetailBinding

class CategoryDetailFragment : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? =null
    private val binding get() = _binding!!

    private val viewModel: CategoryDetailViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val CATEGORY_DETAIL_EXTRA_KEY = "category_detail_extra"
    }
}