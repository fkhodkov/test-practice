package ru.hh.school.fkhodkov.todomvc.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class TodoItemTest {
  @Test
  public void equalsTest() {
    TodoItem item1 = new TodoItem(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItem item2 = new TodoItem(10, "Hello, TODO world", TodoStatus.ACTIVE);

    assertTrue(item1.equals(item2));
  }

  @Test
  public void notEqualIdTest() {
    TodoItem item1 = new TodoItem(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItem item2 = new TodoItem(20, "Hello, TODO world", TodoStatus.ACTIVE);

    assertFalse(item1.equals(item2));
  }

  @Test
  public void notEqualTextTest() {
    TodoItem item1 = new TodoItem(10, "Hello, TODO world!", TodoStatus.ACTIVE);
    TodoItem item2 = new TodoItem(10, "Hello, TODO world.", TodoStatus.ACTIVE);

    assertFalse(item1.equals(item2));
  }

  @Test
  public void notEqualStatusTest() {
    TodoItem item1 = new TodoItem(10, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItem item2 = new TodoItem(10, "Hello, TODO world", TodoStatus.COMPLETED);

    assertFalse(item1.equals(item2));
  }

  @Test
  public void hashTest() {
    TodoItem item1 = new TodoItem(1, "Hello, TODO world", TodoStatus.ACTIVE);
    TodoItem item2 = new TodoItem(2, "Hello, TODO world", TodoStatus.COMPLETED);
    TodoItem item3 = new TodoItem(3, "Hello, TODO world!", TodoStatus.ACTIVE);
    TodoItem item4 = new TodoItem(4, "Hello, TODO world!", TodoStatus.COMPLETED);

    HashSet<TodoItem> items = new HashSet<>();
    items.addAll(List.of(item1, item2, item3));
    assertTrue(items.contains(item1));
    assertFalse(items.contains(item4));
  }

  // @Test
  // public void stringConstructorTest() {
  //   TodoItem todoItem = new TodoItem("Hello, TODO world");
  //   assertEquals(todoItem.getStatus(), TodoStatus.ACTIVE);
  // }
}
