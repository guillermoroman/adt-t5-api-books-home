package com.example.adt_t5_api_1b.controllers;

import com.example.adt_t5_api_1b.model.Book;
import com.example.adt_t5_api_1b.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // **POST - Crear un libro**
    @PostMapping("")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        // Comprobaciones
        if (book.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        // Guardado
        this.service.save(book);

        // Respuesta
        return ResponseEntity.ok(book);
    }

    //  **GET - Obtener todos los libros**
    @GetMapping("")
    public ResponseEntity<List<Book>> findAll() {
        // Obtener libros
        List<Book> books = this.service.findAll();

        // Comprobación de lista no vacía
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Respuesta
        return ResponseEntity.ok(books);
    }

    //  **GET - Obtener un libro por ID**
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  **GET - Obtener libros por año de publicación**
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> findAllByYear(@PathVariable int year) {
        // Obtener libros
        List<Book> books = this.service.findAllByYearPublished(year);

        // Comprobación de lista no vacía
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Respuesta
        return ResponseEntity.ok(books);
    }

    //  **DELETE - Borrar un libro por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id) {
        // Borrar libro
        this.service.deleteById(id);

        // Respuesta sin contenido
        return ResponseEntity.noContent().build();
    }

    //  **PUT - Actualizar un libro por ID**
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        // Comprobar si el libro existe
        if (!this.service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            // Establecer el ID
            book.setId(id);

            // Sobrescribir el libro existente
            this.service.save(book);

            return ResponseEntity.ok(book);
        }
    }
}