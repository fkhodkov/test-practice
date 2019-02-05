package ru.hh.school.fkhodkov.todomvc.exceptions;

public class TodoNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public TodoNotFoundException() {
    super();
  }

  public TodoNotFoundException(String message) {
    super(message);
  }

  public TodoNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public TodoNotFoundException(Throwable cause){
    super(cause);
  }
}
