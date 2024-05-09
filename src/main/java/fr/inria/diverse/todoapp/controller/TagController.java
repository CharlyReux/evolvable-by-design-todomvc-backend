package fr.inria.diverse.todoapp.controller;

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


       @GetMapping("/todo/{id}/tag")
    ResponseEntity<Tag> getTagByTodoId(@RequestParam String id) {
        return ResponseEntity.of(tagRepository.findById(id));
    }
}
