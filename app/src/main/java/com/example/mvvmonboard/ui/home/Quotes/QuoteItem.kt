package com.example.mvvmonboard.ui.home.Quotes

import com.example.mvvmonboard.R
import com.example.mvvmonboard.data.db.entities.Quote
import com.example.mvvmonboard.databinding.QuoteItemBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by Subhankar on August'21 2019
 */

class QuoteItem(private val quote: Quote) : BindableItem<QuoteItemBinding>() {

    override fun getLayout(): Int = R.layout.quote_item

    override fun bind(viewBinding: QuoteItemBinding, position: Int) {
        viewBinding.quote = quote
    }
}