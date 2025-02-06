package com.example.adt_t5_api_1b.controllers;

import com.example.adt_t5_api_1b.model.Author;
import com.example.adt_t5_api_1b.services.AuthorService;
import com.example.adt_t5_api_1b.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService service;
    private ImageService imageService;


    public AuthorController(AuthorService service, ImageService imageService) {
        this.service = service;
        this.imageService = imageService;
    }

    // POST - Crear un autor con imagen
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Author> create(
            @RequestPart("author") String authorJson,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        try {
            // Convertir el JSON recibido en un objeto Author
            ObjectMapper objectMapper = new ObjectMapper();
            Author author = objectMapper.readValue(authorJson, Author.class);

            if (author.getId() != null) {
                return ResponseEntity.badRequest().build();
            }

            // Guardar imagen si se proporciona
            if (image != null && !image.isEmpty()) {
                String imagePath = imageService.saveImage(image);
                author.setImagePath(imagePath);
            }

            // Guardar autor en la base de datos
            this.service.save(author);
            return ResponseEntity.ok(author);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Author>>findAll(){
        // Obtener autores
        List<Author> authors = this.service.findAll();

        //Comprobación de lista no vacía
        if (authors.isEmpty())
            return ResponseEntity.notFound().build();

        // Respuesta
        return ResponseEntity.ok(authors);
    }

    // GET localhost/api/authors/1
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){

        return this.service.findById(id).
                map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());

    }

    // GET localhost/api/authors/year/1920
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Author>> findAllByYear(@PathVariable int year){
        // Obtener autores
        List<Author> authors = this.service.findAllByBirthYear(year);

        // Comprobación de lista no vacía
        if (authors.isEmpty())
            return ResponseEntity.notFound().build();

        // Respuesta
        return ResponseEntity.ok(authors);
    }

    // DELETE lolcalhost/api/authors/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable Long id){
        // Borrar autor
        this.service.deleteById(id);

        // Respuesta sin contenido
        return ResponseEntity.noContent().build();
    }

    // PUT localhost/api/authors/1
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author){
        // Comprobar si el autor existe
        if (!this.service.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            // Establecer el id
            author.setId(id);

            // Sobrescribir el autor existente
            this.service.save(author);

            return ResponseEntity.ok(author);
        }
    }

}
