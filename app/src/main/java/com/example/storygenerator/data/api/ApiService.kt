package com.example.storygenerator.data.api

import com.example.storygenerator.data.responses.ContentResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_QUERY)
    fun getListContents(@Query("CType") typeCategory: Int): Single<ContentResponse>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://rzhunemogu.ru")
                .build()

            return retrofit.create(ApiService::class.java);
        }

        private const val GET_QUERY = "/RandJSON.aspx"
    }
}