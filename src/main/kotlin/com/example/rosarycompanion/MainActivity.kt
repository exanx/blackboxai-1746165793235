package com.example.rosarycompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rosarycompanion.ui.theme.RosaryCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RosaryCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RosaryApp()
                }
            }
        }
    }
}

@Composable
fun RosaryApp() {
    var selectedMystery by remember { mutableStateOf("Sorrowful") }
    var currentDecade by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rosary Companion", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        MysterySelector(selectedMystery) { selectedMystery = it }
        Spacer(modifier = Modifier.height(16.dp))
        DecadeTracker(currentDecade) { currentDecade = it }
        Spacer(modifier = Modifier.height(16.dp))
        PrayerGuidance(selectedMystery, currentDecade)
    }
}

@Composable
fun MysterySelector(selectedMystery: String, onMysterySelected: (String) -> Unit) {
    val mysteries = listOf("Joyful", "Sorrowful", "Glorious", "Luminous")
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        mysteries.forEach { mystery ->
            Button(
                onClick = { onMysterySelected(mystery) },
                colors = if (mystery == selectedMystery) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                else ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(mystery)
            }
        }
    }
}

@Composable
fun DecadeTracker(currentDecade: Int, onDecadeChanged: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (i in 1..13) {
            val isSelected = i == currentDecade
            Button(
                onClick = { onDecadeChanged(i) },
                colors = if (isSelected) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                else ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.size(40.dp)
            ) {
                Text("$i")
            }
        }
    }
}

@Composable
fun PrayerGuidance(mystery: String, decade: Int) {
    Text("Mystery: $mystery")
    Text("Decade: $decade")
    // Placeholder for prayer text or audio controls
}
