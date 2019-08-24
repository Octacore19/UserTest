package com.chegsmania.usertest.database.remote

import com.chegsmania.usertest.model.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsApiService {
    @GET("-LmKkNDAh60zMZt1GrI4")
    fun getUserProfile() : Call<Model.User>

    companion object {
        fun create(): UsApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://35.226.14.35:8080/api/v1/users/")
                .build()
            return retrofit.create(UsApiService::class.java)
        }
    }
}