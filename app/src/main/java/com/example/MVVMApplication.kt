package com.example

import android.app.Application
import com.example.mvvmonboard.data.db.AppDatabase
import com.example.mvvmonboard.data.network.MyApi
import com.example.mvvmonboard.data.network.NetworkConnectionInterceptor
import com.example.mvvmonboard.data.repository.QuoteRepository
import com.example.mvvmonboard.data.repository.UserRepository
import com.example.mvvmonboard.preferences.PreferenceProvider
import com.example.mvvmonboard.ui.auth.AuthViewModelFactory
import com.example.mvvmonboard.ui.home.Quotes.QuotesViewModelFactory
import com.example.mvvmonboard.ui.home.profile.ProfileViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Subhankar on August'13 2019
 */

class MVVMApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { QuoteRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}