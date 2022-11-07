package com.vishal2376.quoteapp

import android.app.Application
import com.vishal2376.quoteapp.api.QuoteService
import com.vishal2376.quoteapp.api.RetrofitHelper
import com.vishal2376.quoteapp.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        repository = QuoteRepository(quoteService)
    }
}