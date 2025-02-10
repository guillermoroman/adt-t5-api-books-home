package com.example.adt_t5_api_1b.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "year_published")
    private int publishedYear;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false) // Clave foránea en la tabla de libros
    private Author author;

    public Book() {
    }

    public Book(String title, int publishedYear, Author author) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearPublished=" + publishedYear +
                ", author=" + author +
                '}';
    }
}