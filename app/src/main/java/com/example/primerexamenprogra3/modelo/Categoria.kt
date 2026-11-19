package com.example.primerexamenprogra3.modelo

// Representa un tipo de libro (Novela, Ciencia, Infantil, etc.).
// Varios libros pueden pertenecer a la misma categoría.
class Categoria(
    val id: Int,
    val nombre: String,
    val descripcion: String
) {
    // Al mostrar una categoría como texto, solo nos interesa su nombre.
    override fun toString(): String {
        return nombre
    }
}
