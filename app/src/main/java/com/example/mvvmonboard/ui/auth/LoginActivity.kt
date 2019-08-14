package com.example.mvvmonboard.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmonboard.R
import com.example.mvvmonboard.data.db.entities.User
import com.example.mvvmonboard.databinding.ActivityLoginBinding
import com.example.mvvmonboard.ui.home.HomeActivity
import com.example.mvvmonboard.util.hide
import com.example.mvvmonboard.util.show
import com.example.mvvmonboard.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
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
//        root_layout?.snackbar("${user?.name} is logged in")
//        toast("${user?.name} is logged in")
    }

    override fun onFailure(message: String?) {
        progressBar?.hide()
        root_layout?.snackbar(message)
//        toast(message)
    }
}
