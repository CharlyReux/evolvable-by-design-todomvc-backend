package fr.inria.diverse.todoapp.serializers;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fr.inria.diverse.todoapp.model.Semantic;

public class SemanticSerializer<T> extends JsonSerializer<Semantic<T>> {

    public SemanticSerializer() {
    }

    @Override
    public void serialize(Semantic<T> semanticObject, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("_links");
        gen.writeStartArray();
        for (String link : semanticObject.getLinks()) {
            gen.writeString(link);
        }
        gen.writeEndArray();
        for (Field field : semanticObject.getValue().getClass().getDeclaredFields()) {
            field.setAccessible(true);
            gen.writeFieldName(field.getName());
            try {
                gen.writeObject(field.get(semanticObject.getValue()));
            } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
                System.out.println("Error while serializing semantic object for field " + field.getName());
                e.printStackTrace();
            }
            field.setAccessible(false);

        }

        gen.writeEndObject();
    }
}