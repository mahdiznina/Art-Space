package com.example.calculate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text // Assurez-vous d'importer le composable Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview // Import pour les aperçus
import com.example.calculate.ui.theme.TipTimeTheme // Assurez-vous d'importer le bon thème

// Modèle de données pour une œuvre d'art
data class Artwork(val imageResId: Int, val title: String, val artist: String, val year: String)

class MainActivity : ComponentActivity() {
    private val artworks = listOf(
        Artwork(R.drawable.artwork1, "Titre 1", "Artiste 1", "2020"),
        Artwork(R.drawable.artwork2, "Titre 2", "Artiste 2", "2021"),
        Artwork(R.drawable.artwork3, "Titre 3", "Artiste 3", "2022")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme { // Changez ceci pour votre thème personnalisé si nécessaire
                // Surface pour le fond
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GalleryScreen(artworks)
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(artworks: List<Artwork>) {
    var currentIndex by remember { mutableStateOf(0) }

    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Affichage de l'œuvre d'art
        Image(
            painter = painterResource(id = currentArtwork.imageResId),
            contentDescription = currentArtwork.title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Permet de prendre de l'espace vertical
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Informations sur l'œuvre
        Text(
            text = currentArtwork.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Par ${currentArtwork.artist}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${currentArtwork.year}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons de navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
                }
            ) {
                Text("Précédent")
            }
            Button(
                onClick = {
                    currentIndex = (currentIndex + 1) % artworks.size
                }
            ) {
                Text("Suivant")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGalleryScreen() {
    TipTimeTheme {
        GalleryScreen(listOf(
            Artwork(R.drawable.artwork1, "Titre 1", "Artiste 1", "2020"),
            Artwork(R.drawable.artwork2, "Titre 2", "Artiste 2", "2021"),
            Artwork(R.drawable.artwork3, "Titre 3", "Artiste 3", "2022")
        ))
    }
}
