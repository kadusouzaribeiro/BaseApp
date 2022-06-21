package br.com.android.baseapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.android.baseapp.data.Response
import br.com.android.baseapp.data.remote.Repository
import br.com.android.baseapp.data.remote.dto.Fact
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Created by Carlos Souza on 21,junho,2022
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

    val state = MutableLiveData<Response<List<Fact>?>>()

    fun getFacts() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            state.postValue(Response.error(null, throwable.message ?: "Error loading API"))
        }) {
            state.postValue(Response.loading(null))
            repository.getFacts().let {
                state.postValue(Response.success(it))
            }
        }
    }
}