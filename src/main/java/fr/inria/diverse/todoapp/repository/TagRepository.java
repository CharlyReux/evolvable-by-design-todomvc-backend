package fr.inria.diverse.todoapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inria.diverse.todoapp.model.Tag;

public interface TagRepository extends JpaRepository<Tag, UUID> {

}
