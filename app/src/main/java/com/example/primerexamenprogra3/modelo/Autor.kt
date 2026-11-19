package com.example.primerexamenprogra3.modelo

// Representa a la persona que escribió un libro.
// Los atributos son "val": una vez creado el autor, sus datos no cambian.
class Autor(
    val id: Int,
    val nombre: String,
    val nacionalidad: String
) {
    // Permite mostrar el autor como texto, por ejemplo: "Gabriel García Márquez (Colombia)"
    override fun toString(): String {
        return "$nombre ($nacionalidad)"
    }
}
