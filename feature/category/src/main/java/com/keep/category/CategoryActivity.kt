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
import com.keep.category.databinding.ActivityCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCategoryBinding

    private val viewModel : CategoryActivityViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter()
        recyclerView.adapter = categoryAdapter



        binding.btnAdd.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            val inflater: LayoutInflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_category, null)
            val categoryEditText = dialogLayout.findViewById<EditText>(R.id.edt_category)

            with(alertDialogBuilder) {
                setView(dialogLayout)
                setPositiveButton(R.string.ok_positive_btn) { dialog, which ->
                    val categoryName = categoryEditText.text.toString() // Ambil teks setelah dialog ditampilkan
                    if (categoryName.isNotEmpty()) {
                        viewModel.insertCategory(categoryName)
                    } else {
                        // Tambahkan logika error handling jika kategori kosong
                        Toast.makeText(this@CategoryActivity, "Category cannot be empty", Toast.LENGTH_SHORT).show()
                    }

                }
                setNegativeButton(R.string.cancel_negative_btn) { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }
    }
}