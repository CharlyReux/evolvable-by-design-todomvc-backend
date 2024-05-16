package fr.inria.diverse.todoapp.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class TodoCreationRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is a mandatory field")
    private String todoTitle;
    @NotBlank(message = "Author is a mandatory field")
    private String authorName;
    @NotBlank(message = "Tag is a mandatory field")
    private String tagName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;

    public TodoCreationRequest() {
    }

    public TodoCreationRequest( String todoName, String authorName, String tagName, LocalDateTime dueDate) {
        this.todoTitle = todoName;
        this.authorName = authorName;
        this.tagName = tagName;
        this.dueDate = dueDate;
    }


    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoName) {
        this.todoTitle = todoName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    

}
