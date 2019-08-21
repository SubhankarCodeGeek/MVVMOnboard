package com.example.mvvmonboard.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmonboard.data.db.AppDatabase
import com.example.mvvmonboard.data.db.entities.Quote
import com.example.mvvmonboard.data.network.MyApi
import com.example.mvvmonboard.data.network.SafeApiRequest
import com.example.mvvmonboard.preferences.PreferenceProvider
import com.example.mvvmonboard.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit


/**
 * Created by Subhankar on August'16 2019
 */

class QuoteRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

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
        val lastSaveAt = prefs.getLastSaveAt()
        if (lastSaveAt == null || isFetchNeed(LocalDateTime.parse(lastSaveAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeed(saveAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(saveAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(list: List<Quote>?) {
        Coroutines.io {
            prefs.saveLastAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(list)
        }
    }

    companion object {
        private const val MINIMUM_INTERVAL = 6
    }
}