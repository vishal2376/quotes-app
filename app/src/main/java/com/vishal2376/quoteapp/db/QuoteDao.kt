package com.vishal2376.quoteapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vishal2376.quoteapp.models.Quotes

@Dao
interface QuoteDao {
    @Insert
    suspend fun addQuote(quote: List<Quotes>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Quotes>
}