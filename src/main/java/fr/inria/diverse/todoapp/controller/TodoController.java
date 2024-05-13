package fr.inria.diverse.todoapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import fr.inria.diverse.todoapp.model.TodoCollection;
import fr.inria.diverse.todoapp.model.TodoCreationRequest;
import fr.inria.diverse.todoapp.model.TodoUpdateRequest;
import fr.inria.diverse.todoapp.repository.AuthorRepository;
import fr.inria.diverse.todoapp.repository.TagRepository;
import fr.inria.diverse.todoapp.repository.TodoRepository;
import jakarta.transaction.Transactional;
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

    @GetMapping("/todo")
    ResponseEntity<Semantic<TodoCollection>> getAllTodos(@RequestParam(required = false) String status) {
        if (status == null || status.equals("all")) {

            return ResponseEntity.ok(Semantic.of(new TodoCollection(todoRepository.findAll()))
                    .withLinks(List.of("createTodo", "deleteMany")));
        }
        Boolean statusBool = status.equals("completed") ? true : false;
        return ResponseEntity.ok(Semantic.of(new TodoCollection(todoRepository.findAllByCompleted(statusBool)))
                .withLinks(List.of("createTodo", "deleteMany")));
    }

    @GetMapping("/todo/{id}")
    ResponseEntity<Semantic<Todo>> getTodoById(@PathVariable UUID id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Semantic<Todo> semanticTodo = Semantic.of(todo.get())
                .withLinks(List.of("update", "delete", "listAll"));
        return ResponseEntity.ok(semanticTodo);
    }

    // todo - Add the semantic links to the response
    @DeleteMapping("/todo/{id}")
    @Transactional
    ResponseEntity<Void> deleteTodoById(@PathVariable UUID id) {
        todoRepository.deleteById(id);
        tagRepository.deleteById(id);
        authorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // todo - Add the semantic links to the response
    @DeleteMapping("/todos")
    @Transactional
    ResponseEntity<Void> deleteTodosByStatus(@RequestParam String status) {
        if (status == null || status.equals("all"))
            todoRepository.deleteAll();
        Boolean statusBool = status.equals("completed") ? true : false;
        List<Todo> todos = todoRepository.findAllByCompleted(statusBool);
        for (Todo todo : todos) {
            tagRepository.deleteById(todo.getId());
            authorRepository.deleteById(todo.getId());
        }
        todoRepository.deleteByCompleted(statusBool);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todo/{id}")
    ResponseEntity<Semantic<Todo>> updateTodo(@Valid @RequestBody TodoUpdateRequest todoUpdateRequest,
            @PathVariable UUID id) {
        // saving todo
        Todo todo = todoRepository.findById(id).get();
        todo.setTitleIfNotNull(todoUpdateRequest.getTodoName());
        todo.setCompletedIfNotNull(todoUpdateRequest.getCompleted());
        todoRepository.save(todo);
        // saving author
        Author author = authorRepository.findById(id).get();
        author.setNameIfNotNull(todoUpdateRequest.getAuthorName());
        authorRepository.save(author);
        // saving tag
        Tag tag = tagRepository.findById(id).get();
        tag.setNameIfNotNull(todoUpdateRequest.getTagName());
        tagRepository.save(tag);
        Semantic<Todo> semanticTodo = Semantic.of(todo)
                .withLinks(List.of("update", "delete", "listAll", "getAuthor", "getTag"));
        return ResponseEntity.ok(semanticTodo);
    }

    @PostMapping(value = "/todo", consumes = "application/json", produces = "application/json")
    ResponseEntity<Semantic<Todo>> createTodo(@Valid @RequestBody TodoCreationRequest todoCreationRequest) {
        // saving todo
        Todo todo = new Todo(todoCreationRequest.getTodoTitle(), null, false);
        todoRepository.save(todo);
        // saving author
        Author author = new Author(todo.getId(), todoCreationRequest.getAuthorName());
        authorRepository.save(author);
        // saving tag
        Tag tag = new Tag(todo.getId(), todoCreationRequest.getTag());
        tagRepository.save(tag);
        Semantic<Todo> semanticTodo = Semantic.of(todo)
                .withLinks(List.of("update", "delete", "listAll", "getAuthor", "getTag"));
        return ResponseEntity.status(HttpStatus.CREATED).body(semanticTodo);
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
