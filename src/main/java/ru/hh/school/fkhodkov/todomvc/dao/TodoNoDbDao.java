package ru.hh.school.fkhodkov.todomvc.dao;

import org.springframework.stereotype.Component;

import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Component
public class TodoNoDbDao implements TodoDao {
  private static final AtomicInteger todoIdGenerator = new AtomicInteger();
  private static final Map<Integer, TodoItem> items = new HashMap<Integer, TodoItem>();

  @Override
  public int count() {
    return items.size();
  }

  private int count(Predicate<TodoItem> predicate) {
    return (int) items.values().stream().filter(predicate).count();
  }

  @Override
  public int countActive() {
    return count(TodoItem::isActive);
  }

  @Override
  public int countCompleted() {
    return count(TodoItem::isCompleted);
  }

  @Override
  public void reset() {
    items.clear();
    todoIdGenerator.set(0);
  }

  @Override
  public void populate(int numberOfTodos) {
    reset();
    for (int i = 0; i < numberOfTodos; i++) {
      TodoItem todoItem = new TodoItem();
      todoItem.setText("TODO #" + i);
      addItem(todoItem);
    }
  }

  @Override
  public Collection<TodoItem> getAllItems() {
    return items.values();
  }

  private Collection<TodoItem> getMatching(Predicate<TodoItem> predicate) {
    Collection<TodoItem> result = new ArrayList<TodoItem>();
    getAllItems().stream().filter(predicate).forEach(result::add);
    return result;
  }

  @Override
  public Collection<TodoItem> getActiveItems() {
    return getMatching(TodoItem::isActive);
  }

  @Override
  public Collection<TodoItem> getCompletedItems() {
    return getMatching(TodoItem::isCompleted);
  }

  @Override
  public void addItem(TodoItem todoItem) {
    if (todoItem.getTodoId() == null) {
      todoItem.setTodoId(todoIdGenerator.incrementAndGet());
    }
    int todoId = todoItem.getTodoId();
    items.put(todoId, todoItem);
  }

  @Override
  public void changeStatus(Integer todoId, TodoStatus status) throws TodoNotFoundException {
    if (items.containsKey(todoId)) {
      items.get(todoId).setStatus(status);
    } else {
      throw new TodoNotFoundException("TODO #" + todoId + " not found");
    }
  }

  @Override
  public void deleteItem(Integer todoId) throws TodoNotFoundException {
    if (items.containsKey(todoId)) {
      items.remove(todoId);
    } else {
      throw new TodoNotFoundException("TODO #" + todoId + " not found");
    }
  }

  private void deleteMatching(Predicate<TodoItem> predicate) {
    Iterator<Integer> keys = items.keySet().iterator();
    keys.forEachRemaining(todoId -> {
      if (predicate.test(items.get(todoId))) {
        keys.remove();
      }
    });
  }

  @Override
  public void deleteCompleted() {
    deleteMatching(TodoItem::isCompleted);
  }
}
