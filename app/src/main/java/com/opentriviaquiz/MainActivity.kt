package com.opentriviaquiz // názov balíka aplikácie (musí sedieť s Manifestom a adresárovou štruktúrou)

import android.os.Bundle // potrebné na získanie Bundle v onCreate
import androidx.activity.ComponentActivity // základná Activity pre Compose
import androidx.activity.compose.setContent // funkcia, ktorá vloží Compose UI do Activity
import androidx.compose.material3.MaterialTheme // Material3 téma – farby, typografia
import androidx.compose.material3.Surface // obal, ktorý použijeme ako „plátno“ s pozadím
import androidx.navigation.compose.rememberNavController // vytvorí a drží NavController pre Compose
import com.opentriviaquiz.ui.nav.AppNav // naša NavHost funkcia s definíciou rout
import com.opentriviaquiz.ui.theme.OpenTriviaQuizTheme // naša app téma (generovaná Android Studiom)

class MainActivity : ComponentActivity() { // hlavná vstupná aktivita – spúšťa sa pri otvorení appky
    override fun onCreate(savedInstanceState: Bundle?) { // lifecycle callback – tu konfigurujeme UI
        super.onCreate(savedInstanceState) // zavoláme rodičovskú implementáciu
        setContent { // od tohto bodu kreslíme UI deklaratívne cez Compose
            OpenTriviaQuizTheme { // zabalíme UI do našej Material3 témy (farby, typography, shapes)
                Surface( // Surface poskytuje pozadie a zjednotený štýl pre plochu
                    color = MaterialTheme.colorScheme.background // použijeme farbu pozadia z témy
                ) {
                    val navController = rememberNavController() // vytvoríme NavController (uchová stav cez recompozície)
                    AppNav(navController) // vložíme náš navigačný graf – ten rozhodne, ktorý screen sa kreslí
                }
            }
        }
    }
}

