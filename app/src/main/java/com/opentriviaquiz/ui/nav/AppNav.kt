package com.opentriviaquiz.ui.nav // balík pre navigáciu

import androidx.compose.runtime.Composable // anotácia pre Compose funkcie
import androidx.navigation.NavHostController // kontrolér navigácie
import androidx.navigation.compose.NavHost // kontajner, ktorý zobrazuje „aktívnu“ obrazovku
import androidx.navigation.compose.composable // definícia „routy“ s obsahom danej obrazovky
import com.opentriviaquiz.ui.screen.home.HomeScreen // import našich obrazoviek
import com.opentriviaquiz.ui.screen.quiz.QuizScreen
import com.opentriviaquiz.ui.screen.results.ResultsScreen

object Routes { // centrálne miesto, kde držíme názvy rout – vyhneme sa preklepom
    const val HOME = "home" // úvodná obrazovka
    const val QUIZ = "quiz" // obrazovka kvízu
    const val RESULTS = "results" // obrazovka výsledkov
}

@Composable
fun AppNav(navController: NavHostController) { // composable, ktorý stavia NavHost
    // NavHost drží graf navigácie; startDestination určuje, ktorá obrazovka sa zobrazí ako prvá
    NavHost(navController = navController, startDestination = Routes.HOME) {
        // Každá „composable“ route mapuje názov routy na konkrétny UI obsah (screen)
        composable(Routes.HOME) { // keď sme na "home", vykresli HomeScreen
            HomeScreen(navController) // posunieme navController, aby mohol screen navigovať ďalej
        }
        composable(Routes.QUIZ) { // route pre kvíz
            QuizScreen(navController) // zobraz UI kvízu
        }
        composable(Routes.RESULTS) { // route pre výsledky
            ResultsScreen(navController) // zobraz zhrnutie výsledkov
        }
    }
}


