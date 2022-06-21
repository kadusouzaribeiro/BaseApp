package br.com.android.baseapp.di

import android.util.Log
import br.com.android.baseapp.data.remote.Api
import br.com.android.baseapp.data.remote.Repository
import br.com.android.baseapp.viewmodel.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Carlos Souza on 15,junho,2022
 */

val networkModule = module {

    val baseUrl = "https://cat-fact.herokuapp.com/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        val interceptor =
            HttpLoggingInterceptor { message ->
                Log.d("Retrofit", message)
            }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return clientBuilder.addInterceptor(interceptor).build()
    }

    fun retrofit(client: OkHttpClient, baseURL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(baseURL)
            .build()
    }

    single { getClient() }
    single { retrofit(get(), baseUrl) }

}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
    single { provideApi(get()) }
}

val repositoryModule = module {
    single { Repository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}