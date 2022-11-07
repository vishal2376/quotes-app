package com.vishal2376.quoteapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishal2376.quoteapp.api.QuoteService
import com.vishal2376.quoteapp.models.QuoteList

class QuoteRepository(private val quoteService: QuoteService) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList> get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result.body() != null) {
            quoteLiveData.postValue(result.body())
        }

    }

}