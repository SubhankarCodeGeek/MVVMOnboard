package com.example.mvvmonboard.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmonboard.data.repository.UserRepository

/**
 * Created by Subhankar on August'16 2019
 */

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}