package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    private UUID id;
    private String name;

    public Author() {
    }

    public Author(UUID id, String name) {
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

    public void setNameIfNotNull(String authorName) {
        if(authorName != null) {
            this.setName(authorName);
        }
    }



    
}
