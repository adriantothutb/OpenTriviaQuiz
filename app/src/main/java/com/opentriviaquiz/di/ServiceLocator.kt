package com.opentriviaquiz.di // jednoduché "DI" (dependency injection) bez knižníc

import com.opentriviaquiz.data.remote.OpenTdbApi // import nášho API rozhrania
import com.squareup.moshi.Moshi                   // JSON parser (Moshi)
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory //
import okhttp3.OkHttpClient                       // HTTP klient
import okhttp3.logging.HttpLoggingInterceptor     // logovanie request/response
import retrofit2.Retrofit                          // Retrofit builder
import retrofit2.converter.moshi.MoshiConverterFactory // prevod JSON <-> Kotlin data class

// Objekt, ktorý drží single instance (singleton) sietových závislostí
object ServiceLocator {

    // Interceptor pre logovanie – pomáha vidieť requesty v Logcate počas vývoja
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC // BASIC = riadok requestu + status + pár hlavičiek
    }

    // OkHttp klient – pridáme logging interceptor (pri release by sme ho mohli vypnúť)
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging) // pridaj logovanie
        .build()                 // zostav klienta

    // Inštancia Moshi – bez prídavných adaptérov (stačí pre naše DTO)
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build() // vytvor objekt Moshi

    // Retrofit builder – nastav baseUrl, konvertor a klienta
    private val retrofit = Retrofit.Builder()
        .baseUrl(OpenTdbApi.BASE_URL)                    // základná URL "https://opentdb.com/"
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // JSON konvertor (Moshi)
        .client(okHttp)                                  // priraď OkHttp klienta
        .build()                                         // zostav Retrofit

    // Verejná API inštancia – použijeme ju v repository
    val api: OpenTdbApi = retrofit.create(OpenTdbApi::class.java) // vygeneruj implementáciu OpenTdbApi
}

