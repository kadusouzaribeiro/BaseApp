package br.com.android.baseapp.data

/**
 * Created by Carlos Souza on 15,junho,2022
 */
data class Response<out T>(val status: ResponseStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Response<T> =
            Response(status = ResponseStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Response<T> =
            Response(status = ResponseStatus.ERROR, data = null, message = message)

        fun <T> loading(data: T?): Response<T> =
            Response(status = ResponseStatus.LOADING, data = null, message = null)
    }
}

enum class ResponseStatus { SUCCESS, ERROR, LOADING }
