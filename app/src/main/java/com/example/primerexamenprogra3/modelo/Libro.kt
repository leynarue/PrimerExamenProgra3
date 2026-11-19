package com.example.primerexamenprogra3.modelo

import java.util.Locale

// Es el objeto principal del catálogo.
// Un libro NO guarda el nombre del autor como texto: guarda el objeto Autor completo.
// Lo mismo pasa con la categoría. Así las clases quedan relacionadas entre sí.
class Libro(
    val id: Int,
    val titulo: String,
    val precio: Double,
    val descripcion: String,
    val stock: Int,
    val autor: Autor,
    val categoria: Categoria
) {
    // Un libro está disponible si queda al menos una unidad en stock.
    fun estaDisponible(): Boolean {
        return stock > 0
    }

    // Devuelve el precio como texto con dos decimales, por ejemplo: "Bs. 85.00"
    fun precioFormateado(): String {
        return String.format(Locale.US, "Bs. %.2f", precio)
    }
}
