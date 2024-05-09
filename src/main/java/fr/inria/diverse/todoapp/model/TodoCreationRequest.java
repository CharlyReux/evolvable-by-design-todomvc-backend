package fr.inria.diverse.todoapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class TodoCreationRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is a mandatory field")
    private String todoTitle;
    @NotBlank(message = "Author is a mandatory field")
    private String authorName;
    @NotBlank(message = "Tag is a mandatory field")
    private String tag;


    public TodoCreationRequest() {
    }

    public TodoCreationRequest( String todoName, String authorName, String tagName) {
        this.todoTitle = todoName;
        this.authorName = authorName;
        this.tag = tagName;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tagName) {
        this.tag = tagName;
    }

    

}
