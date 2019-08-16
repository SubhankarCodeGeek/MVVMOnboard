package com.example.mvvmonboard.data.network.response

import com.example.mvvmonboard.data.db.entities.Quote

/**
 * Created by Subhankar on August'16 2019
 */

data class QuoteResponse(val isSuccessful: Boolean, val quotes: List<Quote>)