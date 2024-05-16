package fr.inria.diverse.todoapp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Infos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Id
    @Column(name = "infosId")
    private Long id;
    private String dueDate;
    private boolean completed;

    public Infos() {
    }

    public Infos(String dueDate, boolean completed) {
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletedIfNotNull(Boolean completed) {
        if (completed != null) {
            this.setCompleted(completed);
        }
    }
}
