package com.keep.password.feature.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.google.android.material.chip.Chip
import com.keep.model.Category
import com.keep.password.core.common.R
import com.keep.password.feature.home.HomeViewModel
import com.keep.password.feature.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.forEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAppBar()
//        setupRecyclerView()
//        initialAdapter()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAppBar() {
        val appBar = binding.appBarMain
        val searchBar = appBar.searchBar
        val buttonNew = appBar.buttonNew

        setupChipGroup()
    }

    private fun chipDefault() : Chip {
        val chip = Chip(requireContext())
        return chip.apply {
            text = getString(R.string.all)
            isCheckable = true
            isChecked = true
        }
    }

    private fun chipGroup(categories: List<Category>) {
        val chipGroup = binding.appBarMain.chipCategoryMain.chipgroup
        chipGroup.removeAllViews()

        chipGroup.addView(chipDefault())

        categories.forEach {
            val chip = Chip(requireContext())
            chip.text = it.name

            chipGroup.addView(chip)
        }
    }

    private fun setupChipGroup() {
        homeViewModel.categories.observe(viewLifecycleOwner) {
            chipGroup(it)
        }
    }

//    private fun setupRecyclerView() {
//        val rv = binding.recyclerViewEntry
//        rv.layoutManager = LinearLayoutManager(context)
//        rv.adapter = categoryAdapter
//    }
//
//    fun initialAdapter() {
//        homeViewModel.categories.observe(viewLifecycleOwner) {
//            categoryAdapter.submitList(homeViewModel.generateCategoryAdapterList(it))
//        }
//    }

}