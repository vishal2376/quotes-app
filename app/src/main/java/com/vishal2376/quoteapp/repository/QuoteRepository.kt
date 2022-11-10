package com.vishal2376.quoteapp.repository

import android.content.Context
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

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList> get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isOnline(context)) {
            val result = quoteService.getQuotes(page)
            if (result.body() != null) {
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        }else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quotesList = QuoteList(1,1,1,quotes,1,1)
            quoteLiveData.postValue(quotesList)
        }

    }

}