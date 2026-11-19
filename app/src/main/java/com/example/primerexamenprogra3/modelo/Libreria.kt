package com.example.primerexamenprogra3.modelo

// Representa la librería. Guarda la lista de libros que se venden.
class Libreria(val nombre: String) {

    // La lista es privada: desde fuera nadie puede agregar o quitar libros directamente.
    // Para eso se usan los métodos de esta clase (encapsulamiento).
    private val libros: MutableList<Libro> = mutableListOf()

    // Agrega un libro al catálogo.
    fun agregarLibro(libro: Libro) {
        libros.add(libro)
    }

    // Devuelve una copia de la lista, así no se puede modificar la original desde fuera.
    fun obtenerLibros(): List<Libro> {
        return libros.toList()
    }

    // Busca un libro por su id. Devuelve null si no existe.
    fun buscarPorId(id: Int): Libro? {
        return libros.find { it.id == id }
    }

    // Devuelve las categorías que tienen libros, sin repetir ninguna.
    fun obtenerCategorias(): List<Categoria> {
        return libros.map { it.categoria }.distinct()
    }
}
