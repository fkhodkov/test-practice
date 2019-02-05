package ru.hh.school.fkhodkov.todomvc.dto;

import ru.hh.school.fkhodkov.todomvc.model.TodoItem;

import java.util.ArrayList;
import java.util.Collection;

public class TodoCollectionDTO {
  private Collection<TodoItemDTO> items;

  public TodoCollectionDTO() {
  }

  public TodoCollectionDTO(Collection<TodoItem> items) {
    this.items = new ArrayList<TodoItemDTO>();
    items.stream().forEach(item -> this.items.add(new TodoItemDTO(item)));
  }

  /**
   * @return the items
   */
  public Collection<TodoItemDTO> getItems() {
    return items;
  }

  /**
   * @param items the items to set
   */
  public void setItems(Collection<TodoItemDTO> items) {
    this.items = items;
  }
}
