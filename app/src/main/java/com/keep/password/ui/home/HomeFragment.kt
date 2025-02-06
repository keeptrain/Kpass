package com.keep.password.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.keep.model.Category
import com.keep.newentry.NewEntryActivity
import com.keep.password.MainActivity
import com.keep.password.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

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

        mainActivity.setupSearchView(searchBar)

        buttonNew.setOnClickListener { view ->
            val intent = Intent(requireContext(), NewEntryActivity::class.java)
            mainActivity.launchNewEntryActivity.launch(intent)
        }
    }

    private fun chipDefault() : Chip {
        val chip = Chip(requireContext())
        return chip.apply {
            text = getString(com.keep.password.R.string.all)
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
//

}