package fr.inria.diverse.todoapp.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inria.diverse.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, UUID>{
}

