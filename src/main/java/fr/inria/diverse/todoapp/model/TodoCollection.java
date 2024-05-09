package fr.inria.diverse.todoapp.model;

import java.util.List;
import java.util.stream.Collectors;

public class TodoCollection {
    private List<Semantic<Todo>> todos;

    public TodoCollection() {
    }

    public TodoCollection(List<Todo> todos) {
        this.todos = todos.stream()
                .map(todo -> Semantic.of(todo).withLinks(List.of("update", "delete", "list","getAuthor","getTag")))
                .collect(Collectors.toList());
    }

    public List<Semantic<Todo>> getTodos() {
        return todos;
    }

    public void setTodos(List<Semantic<Todo>> todos) {
        this.todos = todos;
    }

}
