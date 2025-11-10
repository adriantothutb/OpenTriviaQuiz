package com.opentriviaquiz.domain.model // balík pre „čisté“ doménové modely

// Minimálny model kategórie, s ktorým bude pracovať UI
data class Category(
    val id: Int,     // ID kategórie (ako v API)
    val name: String // zobrazený názov (už bez HTML entít)
)