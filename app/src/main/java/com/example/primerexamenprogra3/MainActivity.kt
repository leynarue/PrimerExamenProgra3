package com.example.primerexamenprogra3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.primerexamenprogra3.modelo.crearLibreriaDeEjemplo
import com.example.primerexamenprogra3.ui.PantallaCatalogo
import com.example.primerexamenprogra3.ui.PantallaDetalle
import com.example.primerexamenprogra3.ui.theme.PrimerExamenProgra3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerExamenProgra3Theme {
                // Librería con los libros de prueba (objetos reales del modelo)
                val libreria = remember { crearLibreriaDeEjemplo() }

                // Estado de la app. Guardamos solo los ids (números) para que no se pierdan
                // al girar la pantalla. Si libroId es null, se muestra el catálogo.
                var libroId by rememberSaveable { mutableStateOf<Int?>(null) }
                var categoriaId by rememberSaveable { mutableStateOf<Int?>(null) }

                // Buscamos el libro elegido en la librería
                val libroSeleccionado = libroId?.let { libreria.buscarPorId(it) }

                if (libroSeleccionado == null) {
                    PantallaCatalogo(
                        libreria = libreria,
                        categoriaIdSeleccionada = categoriaId,
                        onCategoriaClick = { categoriaId = it },
                        onLibroClick = { libroId = it.id }
                    )
                } else {
                    // El botón "atrás" del teléfono también vuelve al catálogo
                    BackHandler { libroId = null }
                    PantallaDetalle(
                        libro = libroSeleccionado,
                        onVolver = { libroId = null }
                    )
                }
            }
        }
    }
}
