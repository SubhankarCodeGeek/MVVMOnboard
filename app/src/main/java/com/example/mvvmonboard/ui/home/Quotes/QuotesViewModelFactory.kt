package com.example.mvvmonboard.ui.home.Quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmonboard.data.repository.QuoteRepository


/**
 * Created by Subhankar on August'16 2019
 */

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(private val repository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }

}