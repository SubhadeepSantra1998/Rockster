package com.example.rockstar.common.util

sealed class Resource<out T> {

    data class Success<out R>(val data:R) : Resource<R>()
    data class Failure(val msg:Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}