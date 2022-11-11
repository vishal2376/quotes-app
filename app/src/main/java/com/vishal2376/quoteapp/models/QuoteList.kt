package com.vishal2376.quoteapp.models

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Quotes>,
    val totalCount: Int,
    val totalPages: Int
)