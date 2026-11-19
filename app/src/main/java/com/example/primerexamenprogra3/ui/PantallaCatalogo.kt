package com.example.primerexamenprogra3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.primerexamenprogra3.modelo.Libreria
import com.example.primerexamenprogra3.modelo.Libro

// Pantalla 1: catálogo de libros.
// Recibe la librería con los datos y dos "avisos" (funciones) para informar a MainActivity
// cuando el usuario toca una categoría o un libro.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(
    libreria: Libreria,
    categoriaIdSeleccionada: Int?,          // null significa "Todas"
    onCategoriaClick: (Int?) -> Unit,
    onLibroClick: (Libro) -> Unit
) {
    // Si hay una categoría elegida, nos quedamos solo con los libros de esa categoría.
    val libros = libreria.obtenerLibros().filter { libro ->
        categoriaIdSeleccionada == null || libro.categoria.id == categoriaIdSeleccionada
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(libreria.nombre) }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // Fila de categorías (se puede deslizar hacia los lados)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = categoriaIdSeleccionada == null,
                        onClick = { onCategoriaClick(null) },
                        label = { Text("Todas") }
                    )
                }
                items(libreria.obtenerCategorias()) { categoria ->
                    FilterChip(
                        selected = categoriaIdSeleccionada == categoria.id,
                        onClick = { onCategoriaClick(categoria.id) },
                        label = { Text(categoria.nombre) }
                    )
                }
            }

            // Lista de libros (solo dibuja los que se ven en pantalla)
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(libros) { libro ->
                    TarjetaLibro(libro = libro, onClick = { onLibroClick(libro) })
                }
            }
        }
    }
}

// Tarjeta de un libro dentro de la lista. Es "private": solo se usa en este archivo.
@Composable
private fun TarjetaLibro(libro: Libro, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = libro.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = libro.autor.nombre,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = libro.categoria.nombre,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = libro.precioFormateado(),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
