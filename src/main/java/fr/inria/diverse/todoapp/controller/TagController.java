package fr.inria.diverse.todoapp.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.inria.diverse.todoapp.model.Tag;
import fr.inria.diverse.todoapp.repository.TagRepository;

@RestController
@RequestMapping("/rest")
public class TagController {
        
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


       @GetMapping("/todo/tag")
    ResponseEntity<Tag> getTagByTodoId(@RequestParam UUID id) {
        return ResponseEntity.of(tagRepository.findById(id));
    }
}
