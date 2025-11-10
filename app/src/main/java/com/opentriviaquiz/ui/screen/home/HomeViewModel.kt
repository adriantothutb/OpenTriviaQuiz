package com.opentriviaquiz.ui.screen.home // balík obrazovky Home

import androidx.lifecycle.ViewModel                       // základná trieda architektúry MVVM
import androidx.lifecycle.viewModelScope                  // coroutine scope viazaný na životný cyklus VM
import com.opentriviaquiz.data.repo.TriviaRepositoryImpl  // implementácia repository
import com.opentriviaquiz.di.ServiceLocator               // poskytnutie OpenTdbApi inštancie
import com.opentriviaquiz.domain.model.Category           // doménový model kategórie
import kotlinx.coroutines.flow.MutableStateFlow           // mutable flow – interný stav
import kotlinx.coroutines.flow.StateFlow                  // read-only flow – pre UI
import kotlinx.coroutines.flow.update                     // pohodlná mutácia StateFlow
import kotlinx.coroutines.launch                          // spustenie coroutine vo viewModelScope

// UI stav pre Home obrazovku – všetko potrebné pre vykreslenie
data class HomeUiState(
    val loading: Boolean = true,                // práve prebieha načítanie?
    val categories: List<Category> = emptyList(), // zoznam kategórií pre Dropdown
    val error: String? = null,                  // text chyby (ak sa niečo pokazí)
    val selectedCategory: Category? = null,     // vybraná kategória (alebo null)
    val amount: Int = 10                        // počet otázok (predvolene 10)
)

// ViewModel pre Home – rieši načítanie kategórií a uchováva voľby používateľa
class HomeViewModel : ViewModel() {

    // Vytvor repository – jednoducho cez náš ServiceLocator (bez DI frameworku)
    private val repo = TriviaRepositoryImpl(ServiceLocator.api) // repo s napojeným API

    // Interný mutable stav – doň zapisujeme zmeny
    private val _ui = MutableStateFlow(HomeUiState()) // počiatočný stav: loading = true
    // Verejný stav pre UI – iba na čítanie (compose si ho „odoberá“)
    val ui: StateFlow<HomeUiState> = _ui               // vystavíme read-only StateFlow

    init {
        // Pri vytvorení VM hneď načítaj kategórie (sieť)
        viewModelScope.launch {
            runCatching { repo.getCategories() } // bezpečne spusti volanie, zachyť výnimky
                .onSuccess { list ->             // ak to vyjde...
                    _ui.update {                 // zmeň stav:
                        it.copy(                 // - vypni loading
                            loading = false,     // - pridaj načítané kategórie
                            categories = list
                        )
                    }
                }
                .onFailure { e ->                // ak to padne, nastav chybu
                    _ui.update {
                        it.copy(
                            loading = false,     // loading končí
                            error = e.message ?: "Chyba načítania kategórií" // správa pre UI
                        )
                    }
                }
        }
    }

    // Nastavenie vybratej kategórie – volá sa pri kliknutí v DropdownMenu
    fun setCategory(c: Category?) = _ui.update { it.copy(selectedCategory = c) }

    // Nastavenie počtu otázok – obmedzíme na rozumný rozsah (5..50)
    fun setAmount(a: Int) = _ui.update { it.copy(amount = a.coerceIn(5, 50)) }
}