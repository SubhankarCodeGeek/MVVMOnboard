package com.example.mvvmonboard.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Subhankar on August'16 2019
 */

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val quote: String? = null,
    val author: String? = null,
    val thumbnail: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)