package com.keep.category

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.keep.category.databinding.BottomSheetDialogBinding
import com.keep.category.databinding.EditTextCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

class NewCategoryDialog(
    private val activity: Context,
    private val onCategoryAdded: (String) -> Unit
) {

    private lateinit var binding : EditTextCategoryBinding
    fun show() {
        binding = EditTextCategoryBinding.inflate(LayoutInflater.from(activity))

        val alertDialogBuilder = AlertDialog.Builder(activity)

        val addButtonLayout = LayoutInflater.from(activity).inflate(R.layout.add_category_button, null)

        alertDialogBuilder.setView(binding.root)
            .setPositiveButton(R.string.ok_positive_btn) { _, _ ->
                val categoryName = binding.edtCategory.text.toString()
                if (categoryName.isNotEmpty()) {
                    onCategoryAdded(categoryName)
                } else {
                    Toast.makeText(activity, "Category cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(R.string.cancel_negative_btn) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun setupObserver() {

    }
}
