package com.opentriviaquiz.ui.screen.home // balík Home obrazovky

import androidx.compose.foundation.layout.*                 // Column/Row/Spacer/padding/layout API
import androidx.compose.material.icons.Icons                // root pre Material ikony
import androidx.compose.material.icons.filled.ExpandLess    // ikona šípky hore (zbalené)
import androidx.compose.material.icons.filled.ExpandMore    // ikona šípky dole (rozbalené)
import androidx.compose.material3.*                         // Material3 komponenty (Scaffold, Button, Text, ...)
import androidx.compose.runtime.*                           // Compose stavové API (remember/by/LaunchedEffect)
import androidx.compose.ui.Modifier                         // Modifier pre layout/styling
import androidx.compose.ui.unit.dp                          // dp jednotky pre rozmery
import androidx.lifecycle.viewmodel.compose.viewModel       // helper na získanie ViewModelu v Compose
import androidx.navigation.NavController                    // typ navigačného kontroléra
import com.opentriviaquiz.ui.nav.Routes                    // naše konštanty rout
import com.opentriviaquiz.domain.model.Category            // model kategórie (pre typy)

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar môže byť experimentálny v niektorých verziách
@Composable
fun HomeScreen(navController: NavController) { // vstupný Composable pre Home obrazovku
    val vm: HomeViewModel = viewModel()          // získa/udrží inštanciu HomeViewModel
    val ui by vm.ui.collectAsState()             // „odoberáme“ StateFlow ako Compose state

    Scaffold(
        topBar = {                               // horná app lišta
            TopAppBar(title = { Text("Open Trivia Quiz") }) // jednoduchý titulok
        }
    ) { padding ->                               // „padding“ zohľadní výšku AppBaru
        Column(
            modifier = Modifier
                .padding(padding)                // vnútorné odsadenie zo Scaffoldu
                .padding(16.dp)                  // základný okraj obsahu
                .fillMaxSize(),                  // vyplň celú obrazovku
            verticalArrangement = Arrangement.spacedBy(16.dp) // zvislé medzery medzi prvkami
        ) {
            when {
                ui.loading -> {                  // stav: načítava sa
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth()) // progress bar
                    Text("Načítavam kategórie…") // info text
                }
                ui.error != null -> {            // stav: chyba pri načítaní
                    Text(
                        text = "Chyba: ${ui.error}", // vypíš text chyby
                        color = MaterialTheme.colorScheme.error // zvýrazni chybovou farbou
                    )
                    Button(
                        onClick = {
                            // jednoduchý „refresh hack“ – pri M3 pridáme normálne obnovenie
                            // (teraz to necháme tak, ide nám o prvé prebehajúce M2)
                        }
                    ) { Text("Skúsiť znova") }
                }
                else -> {                         // stav: všetko OK, máme kategórie
                    // --- Výber kategórie (Pseudo-dropdown s OutlinedTextField + DropdownMenu) ---
                    var expanded by remember { mutableStateOf(false) } // či je menu otvorené
                    OutlinedTextField(
                        value = ui.selectedCategory?.name ?: "Vyber kategóriu", // zobraz názov alebo placeholder
                        onValueChange = {},                  // nič – je readOnly
                        modifier = Modifier.fillMaxWidth(),  // šírka na celý riadok
                        readOnly = true,                     // textfield je iba na klikanie
                        label = { Text("Kategória") },       // popisok nad poľom
                        trailingIcon = {                     // ikonka vpravo – prepína rozbalenie
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, // prepínaj ikony
                                    contentDescription = null // dekoratívna ikona – bez popisu
                                )
                            }
                        },
                        // Kliknutie na celé pole – otvorí menu (voliteľné)
                        enabled = true                       // ponecháme enabled = true
                    )
                    DropdownMenu(
                        expanded = expanded,                 // je menu otvorené?
                        onDismissRequest = { expanded = false } // zatvor, ak klik mimo
                    ) {
                        ui.categories.forEach { cat: Category -> // pre každý item kategórie
                            DropdownMenuItem(
                                text = { Text(cat.name) },        // text položky
                                onClick = {                       // po kliknutí
                                    vm.setCategory(cat)           // ulož vybranú kategóriu do VM
                                    expanded = false              // zatvor menu
                                }
                            )
                        }
                    }

                    // --- Počet otázok (jednoduchý číslicový vstup) ---
                    OutlinedTextField(
                        value = ui.amount.toString(),        // aktuálna hodnota z UI stavu
                        onValueChange = { s ->               // callback pri zmene textu
                            s.toIntOrNull()?.let { vm.setAmount(it) } // ak sa dá previesť na Int, ulož
                        },
                        modifier = Modifier.fillMaxWidth(),  // plná šírka
                        label = { Text("Počet otázok (5–50)") }, // popisok
                        singleLine = true                    // iba jeden riadok
                    )

                    // --- Tlačidlo „Začať kvíz“ ---
                    Button(
                        enabled = ui.selectedCategory != null, // aktívne len ak je vybraná kategória
                        onClick = {
                            // Pre M2 stačí navigovať na Quiz; v M3 odovzdáme parametre
                            navController.navigate(Routes.QUIZ) // prechod na obrazovku kvízu
                        }
                    ) {
                        Text("Začať kvíz") // text tlačidla
                    }
                }
            }
        }
    }
}
