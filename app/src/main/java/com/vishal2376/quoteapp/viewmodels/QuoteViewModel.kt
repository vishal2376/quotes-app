package com.vishal2376.quoteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.quoteapp.QuoteRepository
import com.vishal2376.quoteapp.models.QuoteList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes:LiveData<QuoteList> get() = repository.quotes
}