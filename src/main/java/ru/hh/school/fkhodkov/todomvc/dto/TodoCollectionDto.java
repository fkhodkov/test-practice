package ru.hh.school.fkhodkov.todomvc.dto;

import ru.hh.school.fkhodkov.todomvc.model.TodoItem;

import java.util.Collection;
import java.util.stream.Collectors;

public class TodoCollectionDto {
  private Collection<TodoItemDto> items;

  public TodoCollectionDto() {
  }

  public TodoCollectionDto(Collection<TodoItem> items) {
    this.items = items.stream().map(TodoItemDto::new)
      .collect(Collectors.toList());
  }

  /**
   * @return the items
   */
  public Collection<TodoItemDto> getItems() {
    return items;
  }

  /**
   * @param items the items to set
   */
  public void setItems(Collection<TodoItemDto> items) {
    this.items = items;
  }
}
