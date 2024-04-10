package com.github.antoinecheron.todomvc.restapi.models;

import lombok.Value;

@Value
public class TodoUpdateRequest {

  private final String name;
  private final boolean completed;

}
