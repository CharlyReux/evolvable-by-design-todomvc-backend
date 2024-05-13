package fr.inria.diverse.todoapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Details {
    @Id
    private UUID todoId;
    private String tagName;
    private String authorName;

    public Details() {
    }

    public Details(UUID id, String tagName, String author) {
        this.todoId = id;
        this.tagName = tagName;
        this.authorName = author;
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
        if (tagName != null) {
            this.setTagName(tagName);
        }
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTagNameIfNotNull(String tagName) {
        if (tagName != null) {
            this.setTagName(tagName);
        }
    }

    public void setAuthorNameIfNotNull(String authorName) {
        if (authorName != null) {
            this.setAuthorName(authorName);
        }
    }

}
