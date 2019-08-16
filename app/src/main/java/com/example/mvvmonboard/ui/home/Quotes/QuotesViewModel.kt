package com.example.mvvmonboard.ui.home.Quotes

import androidx.lifecycle.ViewModel;
import com.example.mvvmonboard.data.repository.QuoteRepository
import com.example.mvvmonboard.util.lazyDeferred

class QuotesViewModel(private val repository: QuoteRepository) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}
