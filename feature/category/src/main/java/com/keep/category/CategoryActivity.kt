package com.keep.category

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep.category.adapter.CategoryAdapter
import com.keep.category.adapter.CategoryAdapterEvent
import com.keep.category.databinding.ActivityCategoryBinding
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity(),CategoryAdapterEvent {

    private lateinit var binding : ActivityCategoryBinding

    private val viewModel : CategoryActivityViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Category"

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter()
        recyclerView.adapter = categoryAdapter

        // Observing LiveData for category list
        viewModel.categories.observe(this) { categoryList ->
            categoryAdapter.submitList(categoryList)  // Update RecyclerView
        }

        initialWork()
    }

    private fun initialWork() {
        with(binding) {
            btnAdd.setOnClickListener{
                newCategoryDialog()
            }
        }

    }

    private fun newCategoryDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.edit_text_category, null)
        val categoryEditText = dialogLayout.findViewById<EditText>(R.id.edt_category)

        with(alertDialogBuilder) {
            setView(dialogLayout)
            setPositiveButton(R.string.ok_positive_btn) { dialog, which ->
                val categoryName =
                    categoryEditText.text.toString() // Ambil teks setelah dialog ditampilkan
                if (categoryName.isNotEmpty()) {
                    viewModel.insertCategory(categoryName)
                } else {
                    // Tambahkan logika error handling jika kategori kosong
                    Toast.makeText(
                        this@CategoryActivity,
                        "Category cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            setNegativeButton(R.string.cancel_negative_btn) { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun editCategory(category: Category) {
        TODO("Not yet implemented")
    }
}