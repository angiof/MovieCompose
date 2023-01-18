package com.example.moviecompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviecompose.dto.FakePeliculas
import com.example.moviecompose.dto.Pelicula
import com.example.moviecompose.ui.theme.MovieComposeTheme
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //SimpleLista(pelicula = FakePeliculas.getAllPeliculas())
                    Recy(pelicula = FakePeliculas.getAllPeliculas())
                }
            }
        }
    }
}

@Composable
fun SimpleLista(pelicula: List<Pelicula>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        pelicula.forEach {
            //Text(text = it.nome)
            ListaMoreBella(title = it.nome)
            Text(text = it.descrizione)
        }

    }

}

@Composable
fun ListaMoreBella(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.comom_padding_defoult)),
        style = MaterialTheme.typography.h6
    )

}

@Composable
fun ItemsListClik(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.comom_padding_defoult)),
        style = MaterialTheme.typography.h6
    )

}

@Composable
fun Recy(pelicula: List<Pelicula>) {
    val context = LocalContext.current
    LazyColumn(content = {
        items(pelicula.size) {
            val pelicula = pelicula[it]
            Divider()
            ListItemsRecy(pelicula = pelicula)
        }
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItemsRecy(pelicula: Pelicula, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ListItem(
        modifier = modifier.clickable {
            Toast.makeText(context, pelicula.punteggio, Toast.LENGTH_SHORT).show()
        },
        text = { Text(text = pelicula.nome, style = MaterialTheme.typography.h6) },
        secondaryText = {
            Text(
                text = pelicula.descrizione,
                style = MaterialTheme.typography.subtitle1
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieComposeTheme {
        SimpleLista(pelicula = FakePeliculas.getAllPeliculas())
    }
}