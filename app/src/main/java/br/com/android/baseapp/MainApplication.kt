package br.com.android.baseapp

import android.app.Application
import br.com.android.baseapp.di.apiModule
import br.com.android.baseapp.di.networkModule
import br.com.android.baseapp.di.repositoryModule
import br.com.android.baseapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Carlos Souza on 21,junho,2022
 */
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                networkModule,
                apiModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}