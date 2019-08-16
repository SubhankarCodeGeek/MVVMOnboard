package com.example.mvvmonboard.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.example.mvvmonboard.data.repository.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()
}
