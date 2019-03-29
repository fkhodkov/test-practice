package ru.hh.school.fkhodkov.todomvc.exceptions;

public class TodoNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public TodoNotFoundException(String message) {
    super(message);
  }
}
