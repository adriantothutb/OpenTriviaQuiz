package com.opentriviaquiz.ui.screen.quiz // balík obrazovky kvízu

import androidx.compose.foundation.layout.* // layouty
import androidx.compose.material3.* // Material3 komponenty
import androidx.compose.runtime.Composable // Compose anotácia
import androidx.compose.ui.Modifier // Modifier pre styling/layout
import androidx.compose.ui.unit.dp // jednotky dp
import androidx.navigation.NavController // kvôli navigácii
import com.opentriviaquiz.ui.nav.Routes // naše routy

@OptIn(ExperimentalMaterial3Api::class) // kvôli TopAppBar
@Composable
fun QuizScreen(navController: NavController) { // obrazovka kvízu
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Kvíz") }) // horná lišta s názvom sekcie
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding) // zohľadní výšku AppBaru
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quiz Screen", // placeholder – neskôr sem príde otázka + odpovede
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(Routes.RESULTS) } // po „dokončení“ kvízu prejdeme na výsledky
            ) {
                Text("Zobraziť výsledky")
            }
        }
    }
}
