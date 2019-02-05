package ru.hh.school.fkhodkov.todomvc.dto;

import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.Objects;

public class TodoItemDTO {
  private TodoStatus status;
  private String text;
  private Integer todoId;

  public TodoItemDTO(TodoItem item) {
    this.status = item.getStatus();
    this.text = item.getText();
    this.todoId = item.getTodoId();
  }

  public TodoItemDTO() {}

  public TodoItemDTO(Integer todoId, String text, TodoStatus status) {
    this.status = status;
    this.text = text;
    this.todoId = todoId;
  }

  public TodoItem todoItem() {
    return new TodoItem(todoId, text, status);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    TodoItemDTO dto = (TodoItemDTO) other;
    return todoId.equals(dto.todoId) &&
      text.equals(dto.text) &&
      status.equals(dto.status);
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
