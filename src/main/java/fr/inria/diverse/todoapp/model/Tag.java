package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    private UUID todoId;
    private String tagName;

    public Tag() {
    }

    public Tag(UUID id, String name) {
        this.todoId = id;
        this.tagName = name;
    }
    public UUID getTodoId() {
        return todoId;
    }
    public void setTodoId(UUID id) {
        this.todoId = id;
    }
    public String getTagName() {
        return tagName;
    }
    public void setTagName(String name) {
        this.tagName = name;
    }

    public void setNameIfNotNull(String tagName) {
        if(tagName != null) {
            this.setTagName(tagName);
        }
    }


    
}
