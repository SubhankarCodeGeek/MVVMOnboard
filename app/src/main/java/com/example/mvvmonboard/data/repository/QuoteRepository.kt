package com.example.mvvmonboard.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmonboard.data.db.AppDatabase
import com.example.mvvmonboard.data.db.entities.Quote
import com.example.mvvmonboard.data.network.MyApi
import com.example.mvvmonboard.data.network.SafeApiRequest
import com.example.mvvmonboard.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by Subhankar on August'16 2019
 */

class QuoteRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        if (isFetchNeed()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeed(): Boolean {
        return true
    }

    private fun saveQuotes(list: List<Quote>?) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(list)
        }
    }
}