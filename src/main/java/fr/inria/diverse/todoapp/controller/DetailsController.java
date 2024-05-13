package fr.inria.diverse.todoapp.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.inria.diverse.todoapp.model.Details;
import fr.inria.diverse.todoapp.repository.DetailsRepository;

@RestController
@RequestMapping("/rest")
public class DetailsController {
        
    private final DetailsRepository detailsRepository;

    public DetailsController(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }


       @GetMapping("/todo/{id}/details")
    ResponseEntity<Details> getDetailsByTodoId(@PathVariable UUID id) {
        return ResponseEntity.of(detailsRepository.findById(id));
    }
}
