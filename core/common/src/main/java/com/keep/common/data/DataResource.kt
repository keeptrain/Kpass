package com.keep.common.data

sealed interface DataResource<out T> {
    data class Success<T>(val data: T) : DataResource<T>
    data class Error(val exception: Throwable? = null)  : DataResource<Nothing>
    object Loading : DataResource<Nothing> {}
}


