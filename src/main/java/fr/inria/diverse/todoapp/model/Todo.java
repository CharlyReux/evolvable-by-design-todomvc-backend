package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String title;
    private boolean completed;

    public Todo() {
    }

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    



}
