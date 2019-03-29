package ru.hh.school.fkhodkov.todomvc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ru.hh.school.fkhodkov.todomvc.dao.TodoNoDbDaoFactory;
import ru.hh.school.fkhodkov.todomvc.dto.TodoItemDto;
import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceTest {
  private TodoService todoService = new TodoService(new TodoNoDbDaoFactory());
  private int numberOfTodos = 5;
  private int numberOfActive = (numberOfTodos+1) / 2;
  private int numberOfCompleted = numberOfTodos / 2;

  private int getTotalNum() {
    return todoService.getTodoItems().getItems().size();
  }

  private int getActiveNum() {
    return todoService.getActiveTodoItems().getItems().size();
  }

  private int getCompletedNum() {
    return todoService.getCompletedTodoItems().getItems().size();
  }

  @Before
  public void prepare() throws TodoNotFoundException {
    todoService.reset();
    todoService.populate(numberOfTodos);
    List<TodoItemDto> items = todoService.getTodoItems().getItems().stream()
      .collect(Collectors.toList());
    for (int i = 1; i < numberOfTodos; i += 2) {
      todoService.changeTodoStatus(items.get(i).getTodoId(), TodoStatus.COMPLETED);
    }
  }

  @Test
  public void getTodoItemsTest() {
    assertEquals(numberOfTodos, getTotalNum());
  }

  @Test
  public void getActiveTodoItemsTest() {
    assertEquals(numberOfActive, getActiveNum());
  }

  @Test
  public void getCompletedTodoItemsTest() {
    assertEquals(numberOfCompleted, getCompletedNum());
  }

  @Test
  public void addTodoItemNullTest() {
    TodoItemDto itemDTO = new TodoItemDto();
    todoService.addTodoItem(itemDTO);
    assertEquals(numberOfTodos + 1, getTotalNum());
    assertEquals(1 + numberOfActive, getActiveNum());
  }

  @Test
  public void addTodoItemNotNullTest() {
    TodoItemDto itemDTO = new TodoItemDto();
    itemDTO.setStatus(TodoStatus.COMPLETED);
    todoService.addTodoItem(itemDTO);
    assertEquals(numberOfTodos + 1, getTotalNum());
    assertEquals(1 + numberOfCompleted, getCompletedNum());
  }

  @Test
  public void changeTodoStatusSameStatusTest() {
    int changeId = todoService.getActiveTodoItems().getItems().stream().findAny().get().getTodoId();
    try {
      todoService.changeTodoStatus(changeId, TodoStatus.ACTIVE);
      assertEquals(numberOfActive, getActiveNum());
      assertEquals(numberOfCompleted, getCompletedNum());
    } catch (TodoNotFoundException e) {
      fail("Should not throw this");
    }
  }

  @Test
  public void changeTodoStatusDifferentStatusTest() {
    int changeId = todoService.getActiveTodoItems().getItems().stream().findAny().get().getTodoId();
    try {
      todoService.changeTodoStatus(changeId, TodoStatus.COMPLETED);
      assertEquals(numberOfActive-1, getActiveNum());
      assertEquals(numberOfCompleted+1, getCompletedNum());
    } catch (TodoNotFoundException e) {
      fail("Should not throw this");
    }
  }

  @Test(expected=TodoNotFoundException.class)
  public void changeTodoStatusWrongIdTest() throws TodoNotFoundException {
    int changeId = todoService.getTodoItems().getItems().stream()
      .mapToInt(TodoItemDto::getTodoId)
      .max().getAsInt() + 1;
    todoService.changeTodoStatus(changeId, TodoStatus.COMPLETED);
  }

  @Test
  public void deleteTodoTest() {
    int changeId = todoService.getTodoItems().getItems().stream()
      .mapToInt(TodoItemDto::getTodoId)
      .findAny().getAsInt();
    try {
      todoService.deleteTodo(changeId);
      assertEquals(numberOfTodos - 1, getTotalNum());
    } catch (TodoNotFoundException e) {
      fail("Should not throw this");
    }
  }

  @Test(expected=TodoNotFoundException.class)
  public void deleteTodoWrongIdTest() throws TodoNotFoundException {
    int changeId = todoService.getTodoItems().getItems().stream()
      .mapToInt(TodoItemDto::getTodoId)
      .max().getAsInt() + 1;
    todoService.deleteTodo(changeId);
  }

  @Test
  public void deleteCompletedTodoTest() {
    todoService.deleteCompletedTodo();
    assertEquals(0, getCompletedNum());
    assertEquals(numberOfActive, getActiveNum());
  }

}
