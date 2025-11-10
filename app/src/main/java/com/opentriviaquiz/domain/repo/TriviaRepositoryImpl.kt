package com.opentriviaquiz.data.repo // balík pre konkrétne implementácie dátových zdrojov

import androidx.core.text.HtmlCompat                    // utilita na dekódovanie HTML entít (&quot;, &amp; ...)
import com.opentriviaquiz.data.remote.OpenTdbApi        // naše API rozhranie
import com.opentriviaquiz.domain.model.Category         // doménový model
import com.opentriviaquiz.domain.repo.TriviaRepository  // rozhranie repo

// Implementácia repository, ktorá číta dáta zo sieťového API
class TriviaRepositoryImpl(
    private val api: OpenTdbApi // závislosť: implementáciu dodá ServiceLocator
) : TriviaRepository {

    // Pomocná funkcia: prevedie HTML entity na normálne znaky (OpenTDB ich často vracia)
    private fun unescape(s: String): String =
        HtmlCompat.fromHtml(s, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() // vráti plain text

    // Načítanie zoznamu kategórií z API, mapovanie na doménový model
    override suspend fun getCategories(): List<Category> {
        val dto = api.getCategories() // sieťové volanie (suspend)
        return dto.trivia_categories  // pole CategoryDto
            .map { Category(id = it.id, name = unescape(it.name)) } // mapovanie s unescape názvu
    }
}