package com.example.livedatapractice.util

sealed class Resource<T>(val data: T?, val message: String?)

class SuccessResource<T>(data: T?, message: String?): Resource<T>(data, message)
class ErrorResource<T>(data: T?, message: String?): Resource<T>(data, message)
class LoadingResource<T>(data: T?, message: String?): Resource<T>(data, message)

fun <T> getSuccessResource(data: T): Resource<T> = SuccessResource(data, null)
fun <T> getErrorResource(message: String, data: T? = null): Resource<T> = ErrorResource(data, message)
fun <T> getLoadingResource(data: T? = null): Resource<T> = LoadingResource(data, null)