package ru.hh.school.fkhodkov.todomvc.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.HashSet;
import java.util.List;

public class TodoItemDTOTest {
  @Test
  public void equalsTest() {
    TodoItemDTO item1 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItemDTO item2 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    assertTrue(item1.equals(item2));
  }

  @Test
  public void equalsSameTest() {
    TodoItemDTO item1 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    assertTrue(item1.equals(item1));
  }


  @Test
  public void notEqualsIdTest() {
    TodoItemDTO item1 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItemDTO item2 = new TodoItemDTO(20, "Hello, TODO world", TodoStatus.ACTIVE);
    assertFalse(item1.equals(item2));
  }

  @Test
  public void notEqualsTextTest() {
    TodoItemDTO item1 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItemDTO item2 = new TodoItemDTO(10, "Hello, TODO world!", TodoStatus.ACTIVE);
    assertFalse(item1.equals(item2));
  }

  @Test
  public void notEqualsStatusTest() {
    TodoItemDTO item1 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItemDTO item2 = new TodoItemDTO(10, "Hello, TODO world", TodoStatus.COMPLETED);
    assertFalse(item1.equals(item2));
  }


  @Test
  public void hashTest() {
    TodoItemDTO item1 = new TodoItemDTO(1, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItemDTO item2 = new TodoItemDTO(2, "Hello, TODO world", TodoStatus.COMPLETED);
    TodoItemDTO item3 = new TodoItemDTO(3, "Hello, TODO world!", TodoStatus.ACTIVE);
    TodoItemDTO item4 = new TodoItemDTO(4, "Hello, TODO world!", TodoStatus.COMPLETED);
    HashSet<TodoItemDTO> items = new HashSet<>();
    items.addAll(List.of(item1, item2, item3));
    assertTrue(items.contains(item1));
    assertFalse(items.contains(item4));
  }

}
