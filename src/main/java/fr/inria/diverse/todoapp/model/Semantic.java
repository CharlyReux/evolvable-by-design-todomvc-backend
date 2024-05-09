package fr.inria.diverse.todoapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.inria.diverse.todoapp.serializers.SemanticSerializer;

@JsonSerialize(using = SemanticSerializer.class)
public class Semantic<T> {
    

    public static <T> Semantic<T> empty() {
        return new Semantic<>();
    }

    public static <T> Semantic<T> of(T value) {
        return new Semantic<>(value);
    }

    private T value;
    @JsonProperty("_links")
    private List<String> links;

    public Semantic() {
    }

    public Semantic(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Semantic<T> withLinks(List<String> links) {
        this.links = links;
        return this;
    }

    

}
