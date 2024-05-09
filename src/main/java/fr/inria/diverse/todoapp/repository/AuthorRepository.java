package fr.inria.diverse.todoapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inria.diverse.todoapp.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID>{
    
}
