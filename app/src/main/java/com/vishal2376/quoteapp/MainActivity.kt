package com.vishal2376.quoteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishal2376.quoteapp.adapters.QuoteAdapter
import com.vishal2376.quoteapp.repository.Response
import com.vishal2376.quoteapp.viewmodels.QuoteViewModel
import com.vishal2376.quoteapp.viewmodels.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvQuote)
        val adapter = QuoteAdapter()

        val repository = (application as QuoteApplication).repository

        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(repository))[QuoteViewModel::class.java]

        quoteViewModel.quotes.observe(this) { quoteResponse ->
            when (quoteResponse) {
                is Response.Success -> {
                    quoteResponse.data?.let {
                        adapter.submitList(it.results)
                    }
                }
                is Response.Error -> {
                    quoteResponse.errorMessage.let {
                        Toast.makeText(this,it, Toast.LENGTH_LONG).show()
                    }
                }
                is Response.Loading -> {
                    Toast.makeText(this,"Loading",Toast.LENGTH_LONG).show()
                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}