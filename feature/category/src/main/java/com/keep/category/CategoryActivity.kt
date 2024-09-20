package com.keep.category

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

    //private lateinit var categoryAdapter: CategoryAdapter

    private val testAdapter = CategoryAdapter(this)

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

        recyclerView.adapter = testAdapter

        // Observing LiveData for category list

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
            }
        }





    }

    private fun initialWork() {

       binding.btnAdd.setOnClickListener {
           newCategoryDialog()
       }
        // New category button
        /*with(binding) {
            btnAdd.setOnClickListener{
                newCategoryDialog()
            }
        }*/
        /*categoryAdapter = CategoryAdapter(
            onMoreButtonClick = { category ->
                bottomSheetDialog(category)
            }
        )*/

    }

    private fun newCategoryDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.edit_text_category, null)
        val categoryEditText = dialogLayout.findViewById<EditText>(R.id.edt_category)

        with(alertDialogBuilder) {
            setView(dialogLayout)
            setPositiveButton(R.string.ok_positive_btn) { dialog, which ->
                val categoryName = categoryEditText.text.toString() //
                if (categoryName.isNotEmpty()) {
                    viewModel.insertCategory(categoryName)
                } else {
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

    private fun bottomSheetDialog(category: Category) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetLayout = bottomSheetDialog.layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        with(bottomSheetDialog) {
            setContentView(bottomSheetLayout)

            // Handle klik pada Edit
            bottomSheetLayout.findViewById<TextView>(R.id.edit_option).setOnClickListener {
                // Tambahkan aksi untuk Edit
                Toast.makeText(this@CategoryActivity, "Edit clicked", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss() // Tutup BottomSheet setelah dipilih
            }

            // Handle klik pada Delete
            bottomSheetLayout.findViewById<TextView>(R.id.delete_option).setOnClickListener {
                // Tambahkan aksi untuk Delete
                Toast.makeText(this@CategoryActivity, "Delete clicked", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss() // Tutup BottomSheet setelah dipilih
            }

            show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun addCategory() {
        newCategoryDialog()
    }

    override fun editCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun onMoreClick(category: Category) {

        viewModel.categories.observe(this) {test ->
            testAdapter.submitList(test)
        }
        bottomSheetDialog(category)
    }
}