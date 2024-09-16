package com.keep.newentry

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.keep.newentry.databinding.ActivityNewEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewEntryBinding

    private val viewModel: NewEntryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.appBarNewentry.newentryToolbar) {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }


        //val viewmodelname = viewModel.getCategoryName()

        viewModel.category.observe(this) { categoryList ->
            val name = categoryList.map {
                it.name
            }
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, name)
            binding.spinnerCategory.adapter= adapter
        }




    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}