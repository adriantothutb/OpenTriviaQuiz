package com.opentriviaquiz.data.remote // balík pre sieťové API služby

import retrofit2.http.GET   // anotácia pre HTTP GET
import retrofit2.http.Query // anotácia pre query parametre

// Rozhranie pre OpenTDB API – Retrofit z neho vygeneruje implementáciu
interface OpenTdbApi {

    // Endpoint na získanie kategórií
    // Dokumentácia: https://opentdb.com/api_category.php
    @GET("api_category.php") // relatívna cesta za baseUrl
    suspend fun getCategories(): CategoryListDto // suspend = volanie prebehne v coroutine

    // Endpoint na získanie otázok podľa parametrov
    // Dokumentácia: https://opentdb.com/api.php
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int,         // povinný počet otázok
        @Query("category") categoryId: Int?,  // voliteľné ID kategórie (null = ľubovoľná)
        @Query("difficulty") difficulty: String?, // voliteľne "easy" | "medium" | "hard"
        @Query("type") type: String?,         // voliteľne "multiple" | "boolean"
        @Query("token") token: String? = null // voliteľný session token, default null
    ): QuestionsResponseDto

    companion object {
        const val BASE_URL = "https://opentdb.com/" // základná URL pre všetky volania
    }
}

