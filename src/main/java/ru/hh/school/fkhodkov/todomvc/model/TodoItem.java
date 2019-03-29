package ru.hh.school.fkhodkov.todomvc.model;

import java.util.Objects;

public class TodoItem {
  private Integer todoId;

  private TodoStatus status;

  private String text;

  public boolean isActive() {
    return status == TodoStatus.ACTIVE;
  }

  public boolean isCompleted() {
    return status == TodoStatus.COMPLETED;
  }

  public void complete() {
    setStatus(TodoStatus.COMPLETED);
  }

  public TodoItem() {
    status = TodoStatus.ACTIVE;
  }

  public TodoItem(Integer todoId, String text, TodoStatus status) {
    this.todoId = todoId;
    this.text = text;
    this.status = status;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    TodoItem todoItem = (TodoItem) other;
    return todoId.equals(todoItem.todoId) &&
      status.equals(todoItem.status) &&
      text.equals(todoItem.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(todoId, status, text);
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
