package fr.inria.diverse.todoapp.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@RestController
@RequestMapping("/rest")
public class SpecificationController {

    @RequestMapping(method = RequestMethod.OPTIONS, produces = "application/json")
    public ResponseEntity<String> getRestApiSemanticDocumentation() throws IOException {
        final var openApiDocumentationUrl = this.getClass().getClassLoader().getResource("static/openapi.yml");

        final var yamlReader = new ObjectMapper(new YAMLFactory());
        final var obj = yamlReader.readValue(openApiDocumentationUrl, Object.class);

        final var jsonWriter = new ObjectMapper();
        return ResponseEntity.ok(jsonWriter.writeValueAsString(obj));

    }
}
