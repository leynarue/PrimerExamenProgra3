package com.example.primerexamenprogra3.modelo

// Crea una librería con libros de prueba.
// Las pantallas usarán esta librería, así los datos salen de objetos reales del modelo.
fun crearLibreriaDeEjemplo(): Libreria {
    // Categorías: se crean una sola vez y se reutilizan en varios libros.
    val novela = Categoria(1, "Novela", "Historias de ficción de larga extensión")
    val infantil = Categoria(2, "Infantil", "Lecturas para niños y para toda la familia")
    val ciencia = Categoria(3, "Ciencia", "Divulgación científica")
    val clasicos = Categoria(4, "Clásicos", "Obras fundamentales de la literatura")

    // Autores
    val garciaMarquez = Autor(1, "Gabriel García Márquez", "Colombia")
    val saintExupery = Autor(2, "Antoine de Saint-Exupéry", "Francia")
    val hawking = Autor(3, "Stephen Hawking", "Reino Unido")
    val cervantes = Autor(4, "Miguel de Cervantes", "España")
    val orwell = Autor(5, "George Orwell", "Reino Unido")

    val libreria = Libreria("Librería Central")

    libreria.agregarLibro(
        Libro(
            1, "Cien años de soledad", 95.0,
            "La historia de la familia Buendía a lo largo de siete generaciones en el pueblo de Macondo.",
            12, garciaMarquez, novela
        )
    )
    libreria.agregarLibro(
        Libro(
            2, "El principito", 60.0,
            "Un piloto conoce en el desierto a un pequeño príncipe que viaja de planeta en planeta.",
            20, saintExupery, infantil
        )
    )
    libreria.agregarLibro(
        Libro(
            3, "Breve historia del tiempo", 110.0,
            "Una explicación sencilla del universo, los agujeros negros y el origen del tiempo.",
            5, hawking, ciencia
        )
    )
    libreria.agregarLibro(
        Libro(
            4, "Don Quijote de la Mancha", 130.0,
            "Las aventuras de un hidalgo que enloquece leyendo libros de caballerías.",
            8, cervantes, clasicos
        )
    )
    libreria.agregarLibro(
        Libro(
            5, "1984", 85.0,
            "Una sociedad vigilada por el Gran Hermano, donde incluso el pensamiento está controlado.",
            0, orwell, novela // stock 0: sirve para probar el estado "No disponible"
        )
    )

    return libreria
}
