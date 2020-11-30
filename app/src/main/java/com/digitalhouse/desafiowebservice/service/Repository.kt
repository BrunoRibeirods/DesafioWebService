package com.digitalhouse.desafiowebservice.service


import com.digitalhouse.desafiowebservice.entities.Data
import com.digitalhouse.desafiowebservice.entities.Res
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Repository {

    @GET("comics")
    suspend fun getAllComics(
            @Query("offset")p1: Int,
            @Query("limit")p2: Int,
            @Query("ts") p3: String,
            @Query("apikey")p4: String,
            @Query("hash")p5: String
    ): Res



}

val retrofit = Retrofit.Builder()
    .baseUrl("https://gateway.marvel.com/v1/public/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(Repository::class.java)