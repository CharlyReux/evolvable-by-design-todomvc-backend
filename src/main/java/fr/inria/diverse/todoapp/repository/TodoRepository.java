package fr.inria.diverse.todoapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inria.diverse.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, String>{

    List<Todo> findAllByStatus(boolean status);
    List<Todo> deleteByStatus(boolean status);

}

