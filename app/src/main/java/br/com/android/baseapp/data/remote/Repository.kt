package br.com.android.baseapp.data.remote

import br.com.android.baseapp.data.remote.dto.Fact

/**
 * Created by Carlos Souza on 21,junho,2022
 */
class Repository(private val api: Api) {
    suspend fun getFacts() : List<Fact> = api.getFacts()
}