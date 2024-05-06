package fr.inria.diverse.todoapp.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = Semantic.SemanticSerializer.class)
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

    class SemanticSerializer extends JsonSerializer<Semantic<T>> {

        @Override
        public void serialize(Semantic<T> semanticObject, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeFieldName("_links");
            for (String link : semanticObject.links) {
                gen.writeStartArray();
                gen.writeString(link);
                gen.writeEndArray();
            }
            for (Field field : semanticObject.value.getClass().getDeclaredFields()) {
                gen.writeFieldName(field.getName());
                try {
                    gen.writeObject(field.get(semanticObject.value));
                } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }
            } 

            gen.writeEndObject();
        }
    }

}
