package com.example.adt_t5_api_1b.repositories;

import com.example.adt_t5_api_1b.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Optional<Author> findByName(String name);

    List<Author> findAllByBirthYear(Integer year);
}
