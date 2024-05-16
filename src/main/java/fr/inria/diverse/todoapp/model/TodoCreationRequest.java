package fr.inria.diverse.todoapp.model;

import java.time.LocalDateTime;

import javax.print.DocFlavor.STRING;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoCreationRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is a mandatory field")
    private String todoTitle;
    @NotBlank(message = "Author is a mandatory field")
    private String authorName;
    @NotBlank(message = "Tag is a mandatory field")
    private String tagName;
    @NotNull(message = "Due date is a mandatory field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String dueDate;

    public TodoCreationRequest() {
    }

    public TodoCreationRequest( String todoName, String authorName, String tagName, String dueDate) {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    

}
