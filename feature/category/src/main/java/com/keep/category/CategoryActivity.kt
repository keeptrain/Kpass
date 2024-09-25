package com.keep.category

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keep.category.adapter.CategoryAdapter
import com.keep.category.adapter.CategoryAdapterEvent
import com.keep.category.databinding.ActivityCategoryBinding
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity(),CategoryAdapterEvent {

    private lateinit var binding : ActivityCategoryBinding

    private val viewModel : CategoryActivityViewModel by viewModels()

    private val categoryAdapter = CategoryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialWork()

        setSupportActionBar(binding.toolbarCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Category"

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = categoryAdapter

        // Observing LiveData for category list
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.observe(this@CategoryActivity) {test ->
                    categoryAdapter.submitList(viewModel.generateCategoryAdapterList(test))
                }
            }
        }

    }

    private fun initialWork() {
        // New category button
        binding.btnAdd.setOnClickListener {
            showAddCategoryBottomSheet()
        }

    }

    // Fungsi untuk menampilkan AddCategoryDialogFragment
    private fun showAddCategoryBottomSheet(category: Category? = null) {
        val addCategoryDialogFragment = AddCategoryDialogFragment().apply {
            arguments = Bundle().apply {
                category?.let {
                    putSerializable(AddCategoryDialogFragment.CATEGORY_EXTRA_KEY, it)
                    viewModel.insertCategory(category)
                }
            }
        }

        // Menampilkan BottomSheetDialogFragment
        addCategoryDialogFragment.show(supportFragmentManager, "AddCategoryDialogFragment")
    }

    private fun bottomSheetDialog(category: Category) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetLayout = bottomSheetDialog.layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        with(bottomSheetDialog) {
            setContentView(bottomSheetLayout)

            // Handle klik pada Edit
            bottomSheetLayout.findViewById<TextView>(R.id.edit_option).setOnClickListener {
                // Tambahkan aksi untuk Edit
                viewModel.updateCategory(category)
                Toast.makeText(this@CategoryActivity, "Edit clicked", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss() // Tutup BottomSheet setelah dipilih
            }

            // Handle klik pada Delete
            bottomSheetLayout.findViewById<TextView>(R.id.delete_option).setOnClickListener {
                // Tambahkan aksi untuk Delete
                viewModel.deleteCategory(category)
                Toast.makeText(this@CategoryActivity, "Delete ${category.name}", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss() // Tutup BottomSheet setelah dipilih

            }
            show()
        }

    }

    private fun showAddCategory(category: Category? = null) {
        bundleOf(AddCategoryDialogFragment.CATEGORY_EXTRA_KEY to category)
    }


    override fun addCategory() {
        /*showAddCategory()
        NewCategoryDialog(this) { category ->
          viewModel.insertCategory(category)
        }.show()*/
        showAddCategoryBottomSheet()
    }

    override fun editCategory(category: Category) {
        bottomSheetDialog(category)
    }

    override fun deleteCategory(category: Category) {

    }

    override fun onMoreClick(category: Category) {
        bottomSheetDialog(category)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}