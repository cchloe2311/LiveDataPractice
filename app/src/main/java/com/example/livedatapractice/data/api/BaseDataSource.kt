package com.example.livedatapractice.data.api

import com.example.livedatapractice.util.Resource
import com.example.livedatapractice.util.getErrorResource
import com.example.livedatapractice.util.getSuccessResource
import java.lang.Exception
import retrofit2.Response

/**
 * getResult 함수로 retrofit response를 Response 객체로 캡슐화 -> catch errors
 * abstract인 이유? -> getResult 함수를 protect로 선언해 이 추상클래스를 상속받은 클래스에서만 접근할 수 있도록 -> 옳은 방법일까? 그냥 파일에 선언하고 어디서든 접근할 수 있게하는건 안좋은가?
 */
abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return getSuccessResource(body)
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> = getErrorResource("Network call has failed for a following reason: $message")
}