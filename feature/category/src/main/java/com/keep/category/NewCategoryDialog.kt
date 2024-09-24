package com.keep.category

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NewCategoryDialog(
    private val activity: AppCompatActivity,
    private val onCategoryAdded: (String) -> Unit
) {
    fun show() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        val dialogLayout = activity.layoutInflater.inflate(R.layout.edit_text_category, null)
        val categoryEditText = dialogLayout.findViewById<EditText>(R.id.edt_category)

        alertDialogBuilder.setView(dialogLayout)
            .setPositiveButton(R.string.ok_positive_btn) { _, _ ->
                val categoryName = categoryEditText.text.toString()
                if (categoryName.isNotEmpty()) {
                    onCategoryAdded(categoryName)
                } else {
                    Toast.makeText(activity, "Category cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(R.string.cancel_negative_btn) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
