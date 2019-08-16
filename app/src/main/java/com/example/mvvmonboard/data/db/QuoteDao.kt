package com.example.mvvmonboard.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmonboard.data.db.entities.Quote

/**
 * Created by Subhankar on August'16 2019
 */

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes: List<Quote>?)

    @Query("SELECT * FROM Quote")
    fun getQuotes(): LiveData<List<Quote>>
}