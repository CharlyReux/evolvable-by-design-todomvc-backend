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
    private LocalDateTime dueDate;

    public Infos() {
    }

    public Infos(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
