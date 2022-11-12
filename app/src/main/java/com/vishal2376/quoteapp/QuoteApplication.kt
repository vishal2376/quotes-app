package com.vishal2376.quoteapp

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.vishal2376.quoteapp.api.QuoteService
import com.vishal2376.quoteapp.api.RetrofitHelper
import com.vishal2376.quoteapp.db.QuoteDatabase
import com.vishal2376.quoteapp.repository.QuoteRepository
import com.vishal2376.quoteapp.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 20, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)
        repository = QuoteRepository(quoteService, quoteDatabase, applicationContext)
    }
}