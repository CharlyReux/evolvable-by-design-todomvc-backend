package fr.inria.diverse.todoapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.inria.diverse.todoapp.model.Author;
import fr.inria.diverse.todoapp.model.Semantic;
import fr.inria.diverse.todoapp.model.Tag;
import fr.inria.diverse.todoapp.model.Todo;
import fr.inria.diverse.todoapp.model.TodoCreationRequest;
import fr.inria.diverse.todoapp.model.TodoUpdateRequest;
import fr.inria.diverse.todoapp.repository.AuthorRepository;
import fr.inria.diverse.todoapp.repository.TagRepository;
import fr.inria.diverse.todoapp.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/rest")
public class TodoController {

    private final TodoRepository todoRepository;
    private final TagRepository tagRepository;
    private final AuthorRepository authorRepository;

    public TodoController(TodoRepository todoRepository, TagRepository tagRepository,
            AuthorRepository authorRepository) {
        this.todoRepository = todoRepository;
        this.tagRepository = tagRepository;
        this.authorRepository = authorRepository;
    }

    //todo - Add the semantic links to the response
    @GetMapping("/todos")
    ResponseEntity<List<Todo>> getAllTodos(@RequestParam(required = false) String status) {
        if (status == null || status == "all")
            return ResponseEntity.ok(todoRepository.findAll());
        Boolean statusBool = status.equals("completed") ? true : false;
        return ResponseEntity.ok(todoRepository.findAllByCompleted(statusBool));
    }

    @GetMapping("/todo/{id}")
    ResponseEntity<Semantic<Todo>> getTodoById(@RequestParam String id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Semantic<Todo> semanticTodo = Semantic.of(todo.get())
                .withLinks(List.of("updateTodo", "deleteTodoById", "listTodos"));
        return ResponseEntity.ok(semanticTodo);
    }

        //todo - Add the semantic links to the response
    @DeleteMapping("/todo/{id}")
    ResponseEntity<Void> deleteTodoById(@RequestParam String id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //todo - Add the semantic links to the response
    @DeleteMapping("/todos")
    ResponseEntity<Void> deleteTodosByStatus(@RequestParam String status) {
        if (status == null || status == "all")
            todoRepository.deleteAll();
        Boolean statusBool = status.equals("completed") ? true : false;
        todoRepository.deleteByCompleted(statusBool);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todo/{id}")
    ResponseEntity<Semantic<Todo>> updateTodoStatus(@Valid @RequestBody TodoUpdateRequest todoUpdateRequest,
            @RequestParam String id) {
        // saving todo
        Todo todo = todoRepository.findById(id).get();
        todo.setTitle(todoUpdateRequest.getTodoName());
        todo.setCompleted(todoUpdateRequest.getCompleted());
        todoRepository.save(todo);
        // saving author
        Author author = authorRepository.findById(id).get();
        author.setName(todoUpdateRequest.getAuthorName());
        authorRepository.save(author);
        // saving tag
        Tag tag = tagRepository.findById(id).get();
        tag.setName(todoUpdateRequest.getTagName());
        tagRepository.save(tag);
        Semantic<Todo> semanticTodo = Semantic.of(todo).withLinks(List.of("updateTodo", "deleteTodoById", "listTodos"));
        return ResponseEntity.ok(semanticTodo);
    }

    @PostMapping(value="/todo",
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<Semantic<Todo>> updateTodoStatus(@Valid @RequestBody TodoCreationRequest todoCreationRequest) {
        // saving todo
        Todo todo = new Todo(todoCreationRequest.getTodoTitle(), false);
        todoRepository.save(todo);
        // saving author
        Author author = new Author(todo.getId(), todoCreationRequest.getAuthorName());
        authorRepository.save(author);
        // saving tag
        Tag tag = new Tag(todo.getId(), todoCreationRequest.getTag());
        tagRepository.save(tag);
        Semantic<Todo> semanticTodo = Semantic.of(todo).withLinks(List.of("updateTodo", "deleteTodoById", "listTodos"));
        return ResponseEntity.ok(semanticTodo);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
