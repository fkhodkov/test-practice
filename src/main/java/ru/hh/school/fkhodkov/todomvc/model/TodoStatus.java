package ru.hh.school.fkhodkov.todomvc.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TodoStatus {
  ACTIVE,
  COMPLETED;

  @JsonCreator
  public static TodoStatus fromString(String status) {
    return valueOf(status.toUpperCase());
  }
}
