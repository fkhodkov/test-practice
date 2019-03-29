package ru.hh.school.fkhodkov.todomvc.dto;

import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

public class TodoItemDto {
  private TodoStatus status;
  private String text;
  private Integer todoId;

  public TodoItemDto(TodoItem item) {
    this.status = item.getStatus();
    this.text = item.getText();
    this.todoId = item.getTodoId();
  }

  public TodoItemDto() {}

  public TodoItem todoItem() {
    return new TodoItem(todoId, text, status);
  }

  /**
   * @return the status
   */
  public TodoStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(TodoStatus status) {
    this.status = status;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the todoId
   */
  public Integer getTodoId() {
    return todoId;
  }

  /**
   * @param todoId the todoId to set
   */
  public void setTodoId(Integer todoId) {
    this.todoId = todoId;
  }
}
