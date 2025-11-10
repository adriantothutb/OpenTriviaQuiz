package com.opentriviaquiz.domain.repo // balík pre rozhrania repository

import com.opentriviaquiz.domain.model.Category // import doménového modelu

// Rozhranie pre prácu s quiz dátami (abstrakcia nad sieťou/lokálnym úložiskom)
interface TriviaRepository {
    suspend fun getCategories(): List<Category> // načítaj kategórie (suspend pre coroutines)
}