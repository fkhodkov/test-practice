package ru.hh.school.fkhodkov.todomvc.dao;

import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.Collection;

public interface TodoDAO {
  public int count();
  public int countActive();
  public int countCompleted();

  public void reset();
  public void populate(int numberOfTodos);

  public Collection<TodoItem> getAllItems();
  public Collection<TodoItem> getActiveItems();
  public Collection<TodoItem> getCompletedItems();

  public void addItem(TodoItem todoItem);

  public void changeStatus(Integer todoId, TodoStatus status) throws TodoNotFoundException;
  public void deleteItem(Integer todoId) throws TodoNotFoundException;
  public void deleteCompleted();
}
