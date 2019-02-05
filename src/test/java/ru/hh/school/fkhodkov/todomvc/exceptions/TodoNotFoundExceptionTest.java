package ru.hh.school.fkhodkov.todomvc.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;

public class TodoNotFoundExceptionTest {

  @Test(expected = TodoNotFoundException.class)
  public void constructorTest1() throws TodoNotFoundException {
    throw new TodoNotFoundException();
  }

  @Test
  public void constructorTest2() {
    String message = "Hello, TODO World";
    try {
      throw new TodoNotFoundException(message);
    } catch (TodoNotFoundException e) {
      assertEquals(message, e.getMessage());
    }
  }

  @Test
  public void constructorTest3() {
    try {
      throw new TodoNotFoundException(new IOException());
    } catch (TodoNotFoundException e) {
      assertEquals(IOException.class, e.getCause().getClass());
    }
  }

  @Test
  public void constructorTest4() {
    String message = "Hello, TODO World";
    try {
      throw new TodoNotFoundException(message, new IOException());
    } catch (TodoNotFoundException e) {
      assertEquals(IOException.class, e.getCause().getClass());
      assertEquals(message, e.getMessage());
    }
  }
}
