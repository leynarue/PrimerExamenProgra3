package com.example.primerexamenprogra3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.primerexamenprogra3.modelo.Libro

// Pantalla 2: detalle de un libro.
// Recibe el libro que se quiere mostrar y un aviso (onVolver) para regresar al catálogo.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(libro: Libro, onVolver: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del libro") },
                navigationIcon = { TextButton(onClick = onVolver) { Text("Volver") } }
            )
        }
    ) { innerPadding ->
        // verticalScroll permite desplazar la pantalla si el contenido no cabe
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = libro.titulo,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
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

            HorizontalDivider()

            Text("Descripción", style = MaterialTheme.typography.titleMedium)
            Text(libro.descripcion)

            HorizontalDivider()

            // Datos del autor: otro objeto del modelo, relacionado con el libro
            Text("Autor", style = MaterialTheme.typography.titleMedium)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(libro.autor.nombre, fontWeight = FontWeight.Bold)
                    Text("Nacionalidad: ${libro.autor.nacionalidad}")
                }
            }

            HorizontalDivider()

            // Etiqueta de disponibilidad: verde si hay stock, roja si no
            Text("Disponibilidad", style = MaterialTheme.typography.titleMedium)
            val disponible = libro.estaDisponible()
            Surface(
                color = if (disponible) Color(0xFFC8E6C9) else Color(0xFFFFCDD2),
                contentColor = Color(0xFF1B1B1B),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = if (disponible) "Disponible (${libro.stock} en stock)" else "No disponible",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al catálogo")
            }
        }
    }
}
