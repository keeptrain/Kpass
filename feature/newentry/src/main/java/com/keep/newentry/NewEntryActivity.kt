package com.keep.newentry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keep.newentry.databinding.ActivityNewEntryBinding

class NewEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNewentry.newentryToolbar)


        /*enableEdgeToEdge()
        setContentView(R.layout.activity_new_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}