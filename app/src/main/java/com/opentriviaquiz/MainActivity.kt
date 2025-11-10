package com.opentriviaquiz // názov balíka – musí sa zhodovať s cestou a Manifestom

import android.os.Bundle // umožňuje použiť Bundle (na ukladanie stavu)
import androidx.activity.ComponentActivity // základná trieda pre Compose Activity
import androidx.activity.compose.setContent // funkcia, ktorá vkladá Compose obsah do Activity
import androidx.compose.material3.MaterialTheme // obsahuje farby, typografiu, tvary Material3
import androidx.compose.material3.Surface // komponent pre jednotné pozadie
import androidx.navigation.compose.rememberNavController // uchová navigačný stav medzi obrazovkami
import com.opentriviaquiz.ui.nav.AppNav // import navigačnej funkcie (náš NavHost)
import com.opentriviaquiz.ui.theme.OpenTriviaQuizTheme // import hlavnej Material3 témy
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen // Splash API pre Android 12+

// Hlavná vstupná aktivita aplikácie – spúšťa sa ako prvá pri otvorení appky
class MainActivity : ComponentActivity() {

    // lifecycle metóda, ktorá sa volá pri vytváraní Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        // Inicializácia SplashScreenu musí byť úplne na začiatku
        installSplashScreen()

        // Zavolanie pôvodnej implementácie z ComponentActivity
        super.onCreate(savedInstanceState)

        // Vloženie Compose UI do aktivity
        setContent {
            // Použijeme našu tému OpenTriviaQuizTheme (farby, typografia, povrchy)
            OpenTriviaQuizTheme {
                // Surface slúži ako "plátno" s pozadím podľa farieb témy
                Surface(
                    color = MaterialTheme.colorScheme.background // pozadie z aktuálnej témy
                ) {
                    // Vytvorenie navigačného kontroléra pre Compose Navigation
                    val navController = rememberNavController()

                    // Zavolanie AppNav – naša funkcia s definíciou obrazoviek (rout)
                    AppNav(navController)
                }
            }
        }
    }
}

