// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.data.mock

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2/17/17.
 *
 * Pulled from: https://github.com/square/retrofit/blob/master/retrofit-mock/src/main/java/retrofit2/mock/Calls.java
 */
class FakeCall<T>(private val response: Response<T>?, private val error: IOException?) : Call<T> {
    private val canceled = AtomicBoolean()
    private val executed = AtomicBoolean()

    init {
        if ((response == null) == (error == null)) {
            throw AssertionError("Only one of response or error can be set.")
        }
    }

    @Throws(IOException::class)
    override fun execute(): Response<T> {
        when {
            !executed.compareAndSet(false, true) -> throw IllegalStateException("Already executed")
            canceled.get() -> throw IOException("canceled")
            response != null -> return response
            error != null -> throw error
            else -> throw IOException("Should either have a response or throw exception")
        }
    }

    override fun enqueue(callback: Callback<T>?) {
        if (!executed.compareAndSet(false, true)) {
            throw IllegalStateException("Already executed")
        }
        callback?.let {
            when {
                canceled.get() -> callback.onFailure(this, IOException("canceled"))
                response != null -> callback.onResponse(this, response)
                else -> callback.onFailure(this, error)
            }
        } ?: throw NullPointerException("callback == null")
    }

    override fun isExecuted(): Boolean = executed.get()

    override fun cancel() = canceled.set(true)

    override fun isCanceled(): Boolean = canceled.get()

    override fun clone(): Call<T> = FakeCall(response, error)

    override fun request(): Request = response?.raw()?.request()
        ?: Request.Builder().url("http://localhost").build()
}

