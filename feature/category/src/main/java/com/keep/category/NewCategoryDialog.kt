package com.keep.category

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.keep.category.databinding.BottomSheetDialogBinding
import com.keep.category.databinding.EditTextCategoryBinding
import com.keep.model.Category
import dagger.hilt.android.AndroidEntryPoint

class NewCategoryDialog(
    private val activity: AppCompatActivity,
    private val onCategoryAdded: (Category) -> Unit,
) {

    private lateinit var binding : EditTextCategoryBinding

    private val viewModel : CategoryActivityViewModel by lazy {
        ViewModelProvider(activity)[CategoryActivityViewModel::class.java]
    }

    private val category : Category? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.intent.getSerializableExtra(TASK_EXTRA_KEY,Category::class.java)
        } else {
            activity.intent.getSerializableExtra(TASK_EXTRA_KEY) as? Category
        }
    }

    companion object {
        const val TASK_EXTRA_KEY = "task_extra"
    }



    fun show() {
        binding = EditTextCategoryBinding.inflate(LayoutInflater.from(activity))

        val addButtonLayout = LayoutInflater.from(activity).inflate(R.layout.add_category_button, null)

        val alertDialogBuilder = AlertDialog.Builder(activity)

        alertDialogBuilder.setView(binding.root)
            .setPositiveButton(R.string.ok_positive_btn) { dialog, p ->
                val categoryName = binding.edtCategory.text.toString()
                setupObserver()
                category?.let {
                    val categoryCopy = it.copy(name = categoryName)
                    if (category != categoryCopy) {
                        viewModel.insertCategoryWithFieldsValidation(categoryCopy)
                    } else {
                        dialog.dismiss()
                    }
                } ?: viewModel.insertCategoryWithFieldsValidation(Category(name = categoryName))
            }
            .setNegativeButton(R.string.cancel_negative_btn) { dialog, _ -> dialog.dismiss() }
            .show()

    }

    private fun test(){

    }


    private fun setupObserver() {
        viewModel.apply {
            validationResult.observe(activity) { result ->
                result.getContentIfNotHandled()?.let { uiText ->
                    binding.edlCategory.error = uiText.errorMessage?.asString(activity)
                }
            }
            insertResult.observe(activity) { result ->
                result.getContentIfNotHandled()?.let { result ->
                    if (result) {
                        Toast.makeText(activity, "Category added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    private fun setupListener() {
    }
}
