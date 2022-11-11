package com.vishal2376.quoteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.quoteapp.models.QuoteList
import com.vishal2376.quoteapp.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val randomNumber = (Math.random() * 4 + 1).toInt()
            repository.getQuotes(randomNumber)
        }
    }

    val quotes:LiveData<QuoteList> get() = repository.quotes
}