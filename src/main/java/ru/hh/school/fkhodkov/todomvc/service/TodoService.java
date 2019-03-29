package ru.hh.school.fkhodkov.todomvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ru.hh.school.fkhodkov.todomvc.dao.TodoDao;
import ru.hh.school.fkhodkov.todomvc.dao.TodoDaoFactory;
import ru.hh.school.fkhodkov.todomvc.dto.TodoCollectionDto;
import ru.hh.school.fkhodkov.todomvc.dto.TodoItemDto;
import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

@Service
@Component
public class TodoService {
  private final TodoDao todoDao;

  @Autowired
  public TodoService(TodoDaoFactory todoDaoFactory) {
    todoDao = todoDaoFactory.getTodoDao();
  }

  public void reset() {
    todoDao.reset();
  }

  public void populate(int numberOfTodos) {
    todoDao.populate(numberOfTodos);
  }

  public TodoCollectionDto getTodoItems() {
    return new TodoCollectionDto(todoDao.getAllItems());
  }

  public TodoCollectionDto getActiveTodoItems() {
    return new TodoCollectionDto(todoDao.getActiveItems());
  }

  public TodoCollectionDto getCompletedTodoItems() {
    return new TodoCollectionDto(todoDao.getCompletedItems());
  }

  public void addTodoItem(TodoItemDto itemDTO) {
    if (itemDTO.getStatus() == null) {
      itemDTO.setStatus(TodoStatus.ACTIVE);
    }
    todoDao.addItem(itemDTO.todoItem());
  }

  public void changeTodoStatus(Integer todoId, TodoStatus status) throws TodoNotFoundException {
    todoDao.changeStatus(todoId, status);
  }

  public void deleteCompletedTodo() {
    todoDao.deleteCompleted();
  }

  public void deleteTodo(Integer todoId) throws TodoNotFoundException {
    todoDao.deleteItem(todoId);
  }
}
