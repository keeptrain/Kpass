package com.kpass.newentry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep.model.Entry
import com.keep.password.feature.newentry.databinding.FragmentNewEntryBinding
import com.kpass.newentry.adapter.EntryFieldAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewEntryFragment : Fragment() {

    private var _binding : FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewEntryViewModel by viewModels()

    private val entryFieldAdapter by lazy {
        EntryFieldAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewEntryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        chooseCategory()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        with(binding.appBarNewentry) {
            newentryToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                initListener()
                findNavController().navigateUp()
            }
        }
    }

    private fun initListener() {
        val title = binding.appBarNewentry.edtTitle.text.toString()
        val selectedCategory = viewModel.selectedCategory.value

        if (selectedCategory != null && title.isNotEmpty()) {
            viewModel.upsertNewEntry(Entry(
                title = title,
                categoryId = selectedCategory
            ))
        } else {
            Toast.makeText(requireContext(),"Cannot null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseCategory() {
        viewModel.category.observe(viewLifecycleOwner) { categoryList ->
            val name = categoryList.map {
                it.name
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, name)
            binding.spinnerCategory.adapter= adapter
            binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = categoryList[position]
                    viewModel.setSelectedCategory(selectedCategory.id)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setSelectedCategory(null)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvEntryFields.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = entryFieldAdapter
        }
    }
}