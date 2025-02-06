package com.example.adt_t5_api_1b.services;

import com.example.adt_t5_api_1b.model.Author;
import com.example.adt_t5_api_1b.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {


    private AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository){
        this.repository = repository;
    }



    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Author> findAllByBirthYear(Integer year) {
        return repository.findAllByBirthYear(year);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Author save(Author author) {
        this.repository.save(author);
        return author;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
