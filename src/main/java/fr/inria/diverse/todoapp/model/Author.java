package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    private UUID todoId;
    private String authorName;

    public Author() {
    }

    public Author(UUID id, String name) {
        this.todoId = id;
        this.authorName = name;
    }
    public UUID getTodoId() {
        return todoId;
    }
    public void setTodoId(UUID id) {
        this.todoId = id;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public void setNameIfNotNull(String authorName) {
        if(authorName != null) {
            this.setAuthorName(authorName);
        }
    }



    
}
