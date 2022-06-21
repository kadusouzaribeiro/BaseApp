package br.com.android.baseapp.data.remote

import br.com.android.baseapp.data.remote.dto.Fact
import retrofit2.http.GET

/**
 * Created by Carlos Souza on 21,junho,2022
 */
interface Api {
    @GET("facts")
    suspend fun getFacts() : List<Fact>
}