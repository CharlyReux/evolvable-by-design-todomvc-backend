package fr.inria.diverse.todoapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inria.diverse.todoapp.model.Details;

public interface DetailsRepository extends JpaRepository<Details, UUID>{
    
}
