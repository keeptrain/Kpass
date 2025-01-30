package com.keep.password.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.chip.Chip
import com.keep.model.Category
import com.keep.newentry.NewEntryActivity
import com.keep.password.MainActivity
import com.keep.password.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }

//    private val categoryAdapter = CategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAppBar()
        setupChipGroup()

//        val rv = binding.recyclerCategory
//        rv.layoutManager = LinearLayoutManager(context)
//        rv.adapter = categoryAdapter
//
//        homeViewModel.categories.observe(viewLifecycleOwner) {
//            categoryAdapter.submitList(homeViewModel.generateCategoryAdapterList(it))
//        }

    }

    private fun setupAppBar() {
        val appBar = binding.appBarMain
        val searchBar = appBar.searchBar
        val buttonNew = appBar.buttonNew

        searchBar.setNavigationOnClickListener {
            mainActivity.openDrawer()
        }

        searchBar.setOnClickListener {
            mainActivity.setupSearchView(searchBar)
        }

        buttonNew.setOnClickListener { view ->
            val intent = Intent(requireContext(), NewEntryActivity::class.java)
            mainActivity.launchNewEntryActivity.launch(intent)
        }
    }

    private fun chipGroup(categories: List<Category>) {
        val chipGroup = binding.appBarMain.chipCategoryMain.chipgroup
        chipGroup.removeAllViews()

        categories.forEach {
            val chip = Chip(requireContext())
            chip.text = it.name

            chipGroup.addView(chip)
        }
    }

    private fun setupChipGroup() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.categories.observe(viewLifecycleOwner) {
                    chipGroup(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}