package ru.hh.school.fkhodkov.todomvc.dao;

import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.Collection;

public interface TodoDao {
  int count();
  int countActive();
  int countCompleted();

  void reset();
  void populate(int numberOfTodos);

  Collection<TodoItem> getAllItems();
  Collection<TodoItem> getActiveItems();
  Collection<TodoItem> getCompletedItems();

  void addItem(TodoItem todoItem);

  void changeStatus(Integer todoId, TodoStatus status) throws TodoNotFoundException;
  void deleteItem(Integer todoId) throws TodoNotFoundException;
  void deleteCompleted();
}
