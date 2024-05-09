package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    private UUID id;
    private String name;

    public Tag() {
    }

    public Tag(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    
}
