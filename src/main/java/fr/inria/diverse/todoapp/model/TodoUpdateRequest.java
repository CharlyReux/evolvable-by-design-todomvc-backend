package fr.inria.diverse.todoapp.model;

public class TodoUpdateRequest {

    private String todoName;
    private String authorName;
    private String tagName;
    private boolean completed;

    public TodoUpdateRequest() {
    }

    public TodoUpdateRequest(String todoName, String authorName, String tagName) {

        this.todoName = todoName;
        this.authorName = authorName;
        this.tagName = tagName;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
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

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
