package fr.inria.diverse.todoapp.model;


public class TodoCreationRequest {

    private String todoTitle;
    private String authorName;
    private String tagName;


    public TodoCreationRequest() {
    }

    public TodoCreationRequest( String todoName, String authorName, String tagName) {
        this.todoTitle = todoName;
        this.authorName = authorName;
        this.tagName = tagName;
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

    

}
