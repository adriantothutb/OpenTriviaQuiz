package com.opentriviaquiz.ui.screen.home // balík obrazovky Home

import androidx.compose.foundation.layout.* // základné layout kontajnery: Column/Row/Box/Spacer
import androidx.compose.material3.* // Material3 komponenty: TopAppBar, Button, Text, Scaffold...
import androidx.compose.runtime.Composable // Compose anotácia
import androidx.compose.ui.Modifier // umožňuje reťaziť úpravy (padding, fillMaxSize, atď.)
import androidx.compose.ui.unit.dp // jednotky hustoty (dp) pre rozmery
import androidx.navigation.NavController // kontrolér navigácie pre prechod na iný screen
import com.opentriviaquiz.ui.nav.Routes // import názvov rout

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar v M3 môže byť experimentálny v niektorých verziách
@Composable
fun HomeScreen(navController: NavController) { // hlavná/home obrazovka
    // Scaffold poskytuje základné rozloženie so slotmi (topBar, bottomBar, floatingActionButton...)
    Scaffold(
        topBar = { // horná lišta s názvom aplikácie
            TopAppBar(
                title = { Text("Open Trivia Quiz") } // text v AppBare
            )
        }
    ) { padding -> // „padding“ obsahuje vnútorné okraje, ktoré zohľadňujú výšku AppBaru
        Column(
            modifier = Modifier
                .padding(padding) // aplikujeme vnútorné odsadenie zo Scaffoldu
                .fillMaxSize() // zaplníme celý dostupný priestor
                .padding(16.dp), // vnútorný okraj obrazovky
            verticalArrangement = Arrangement.Center // vertikálne vycentruj obsah
        ) {
            Text(
                text = "Home Screen", // jednoduchý nadpis – zatiaľ placeholder
                style = MaterialTheme.typography.titleLarge // použijeme štýl z témy
            )
            Spacer(Modifier.height(20.dp)) // medzera medzi nadpisom a tlačidlom
            Button(
                onClick = { navController.navigate(Routes.QUIZ) } // po kliknutí prejde na obrazovku kvízu
            ) {
                Text("Začať kvíz") // text tlačidla
            }
        }
    }
}
