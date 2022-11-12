package com.vishal2376.quoteapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vishal2376.quoteapp.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, private val params:WorkerParameters): Worker(context,params){
    override fun doWork(): Result {

        val repository = (context as QuoteApplication).repository

        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuoteBackground()
        }
        return Result.success()
    }

}