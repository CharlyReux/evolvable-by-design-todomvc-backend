package fr.inria.diverse.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    ResponseEntity<Author> getAuthorByTodoId(@RequestParam String id) {
        return ResponseEntity.of(authorRepository.findById(id));
    }

}
