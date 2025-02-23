package com.kpass.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep.common.extension.collectFlow
import com.keep.common.navigation.navigateWithAnimate
import com.keep.model.Category
import com.kpass.category.adapter.CategoryAdapter
import com.kpass.category.adapter.CategoryAdapterEvent
import com.kpass.category.adapter.CustomItemTouchHelperCallback
import com.kpass.category.detail.CategoryDetailFragment.Companion.CATEGORY_DETAIL_EXTRA_KEY
import com.kpass.category.navigation.CategoryNavigationNode
import com.kpass.feature.category.R
import com.kpass.feature.category.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment(),CategoryAdapterEvent {

    private var _binding : FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CategoryViewModel by viewModels()

    @Inject
    lateinit var navController: NavController

    private val categoryAdapter by lazy {
        CategoryAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        collectFlow(viewModel.state, uiState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun toDetailScreen(category: Category) {
        findNavController().navigateWithAnimate(
            CategoryNavigationNode.DETAIL_DESTINATION,
            bundleOf(CATEGORY_DETAIL_EXTRA_KEY to category)
        )
    }

    override fun reorderCategory(): Boolean {
        return false
    }

    private val uiState: suspend (CategoryViewModel.UiState) -> Unit = { state ->
        when (state.currentState) {
            CategoryViewModel.STATE.INITIAL -> {
               setupRecyclerView()
                initialAdapter()
                initUi()
            }
            CategoryViewModel.STATE.CREATE -> {

            }
            CategoryViewModel.STATE.REORDER -> {
                Log.d("CategoryFragment", "REORDER")
            }
        }
    }

    private fun initUi() {
        with(binding) {
            btnAdd.setOnClickListener {
                findNavController().navigate(CategoryNavigationNode.BOTTOM_DESTINATION)
            }
        }
    }

    private fun setupToolbar() {
        with(binding) {

            toolbarCategory.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbarCategory.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_reorder -> {
                        findNavController().navigate(
                            CategoryNavigationNode.REORDER_DESTINATION
                        )

                    }
                }
                true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter

            val callback = CustomItemTouchHelperCallback(viewModel)
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun initialAdapter() {
        lifecycleScope.launch {
            viewModel.categories.observe(viewLifecycleOwner) { listCategory ->
                categoryAdapter.submitList(
                    viewModel.generateCategoryAdapterList(listCategory)
                )
            }
        }
    }

    companion object {
        const val CATEGORY_EXTRA_KEY = "category_extra"
    }
}