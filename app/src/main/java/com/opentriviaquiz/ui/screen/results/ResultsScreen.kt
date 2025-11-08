package com.opentriviaquiz.ui.screen.results // balík obrazovky výsledkov

import androidx.compose.foundation.layout.* // layouty
import androidx.compose.material3.* // Material3 komponenty
import androidx.compose.runtime.Composable // Compose anotácia
import androidx.compose.ui.Modifier // Modifier
import androidx.compose.ui.unit.dp // dp jednotky
import androidx.navigation.NavController // navigácia
import com.opentriviaquiz.ui.nav.Routes // konstanta pre routy

@OptIn(ExperimentalMaterial3Api::class) // kvôli TopAppBar
@Composable
fun ResultsScreen(navController: NavController) { // obrazovka s výsledkami
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Výsledky") }) // názov sekcie
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding) // padding kvôli AppBaru
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Results Screen", // placeholder – neskôr vypočítaš skóre a zobrazíš
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(Routes.HOME) } // návrat na úvodnú obrazovku
            ) {
                Text("Späť na úvod")
            }
        }
    }
}