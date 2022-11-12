package com.vishal2376.quoteapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.quoteapp.api.QuoteService
import com.vishal2376.quoteapp.db.QuoteDatabase
import com.vishal2376.quoteapp.models.QuoteList
import com.vishal2376.quoteapp.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val context: Context
) {

    private val quoteLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>> get() = quoteLiveData
    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isOnline(context)) {
            val result = quoteService.getQuotes(page)
            if (result.body() != null) {
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
//                quoteLiveData.postValue(Response.Success(result.body()))
            }

            try {
                val quotes = quoteDatabase.quoteDao().getQuotes()
                val quotesList = QuoteList(1, 1, 1, quotes, 1, 1)
                quoteLiveData.postValue(Response.Success(quotesList))
                Log.e("@@@@", quotes.size.toString())
            } catch (e: Exception) {
                quoteLiveData.postValue(Response.Error("Store Error"))
            }
        }
    }


    suspend fun getQuoteBackground() {
        val randomNumber = (Math.random() * 10).toInt()

        val result = quoteService.getQuotes(randomNumber)
        if (result.body() != null) {
            quoteDatabase.quoteDao().addQuote(result.body()!!.results)
        }
    }

}