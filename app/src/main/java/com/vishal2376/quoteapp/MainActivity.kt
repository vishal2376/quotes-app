package com.vishal2376.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.vishal2376.quoteapp.models.QuoteList
import com.vishal2376.quoteapp.viewmodels.QuoteViewModel
import com.vishal2376.quoteapp.viewmodels.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).repository

        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(repository))[QuoteViewModel::class.java]

        quoteViewModel.quotes.observe(this){
            Log.e("@@@",it.toString())
        }

    }
}