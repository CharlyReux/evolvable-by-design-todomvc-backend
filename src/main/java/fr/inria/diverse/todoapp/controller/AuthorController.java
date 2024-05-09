package fr.inria.diverse.todoapp.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.inria.diverse.todoapp.model.Author;
import fr.inria.diverse.todoapp.repository.AuthorRepository;

@RestController
@RequestMapping("/rest")
public class AuthorController {
    
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/todo/{id}/author")
    ResponseEntity<Author> getAuthorByTodoId(@PathVariable UUID id) {
        return ResponseEntity.of(authorRepository.findById(id));
    }

}
