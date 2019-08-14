package com.example.mvvmonboard.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmonboard.R
import com.example.mvvmonboard.data.db.entities.User
import com.example.mvvmonboard.databinding.ActivitySignupBinding
import com.example.mvvmonboard.ui.home.HomeActivity
import com.example.mvvmonboard.util.hide
import com.example.mvvmonboard.util.show
import com.example.mvvmonboard.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progressBar?.show()
    }

    override fun onSuccess(user: User?) {
        progressBar?.hide()
    }

    override fun onFailure(message: String?) {
        progressBar?.hide()
        root_layout?.snackbar(message)
    }
}
