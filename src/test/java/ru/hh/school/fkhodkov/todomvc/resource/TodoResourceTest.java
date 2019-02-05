package ru.hh.school.fkhodkov.todomvc.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.fkhodkov.todomvc.TodoTestConfig;
import ru.hh.school.fkhodkov.todomvc.dao.TodoDAO;
import ru.hh.school.fkhodkov.todomvc.dao.TodoNoDBDAOFactory;
import ru.hh.school.fkhodkov.todomvc.dto.TodoCollectionDTO;
import ru.hh.school.fkhodkov.todomvc.dto.TodoItemDTO;
import ru.hh.school.fkhodkov.todomvc.model.TodoItem;
import ru.hh.school.fkhodkov.todomvc.model.TodoStatus;

import java.util.function.Predicate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ContextConfiguration(classes = TodoTestConfig.class)
public class TodoResourceTest extends NabTestBase {

  private static final int numberOfTodos = 5;
  private static final TodoDAO todoDAO = new TodoNoDBDAOFactory().getTodoDAO();

  @Test
  public void getTodosWhenThereAreNone() {
    todoDAO.reset();
    Response response = target("/todo").request().get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(0, response.readEntity(TodoCollectionDTO.class).getItems().size());
  }

  @Test
  public void getTodosWhenThereAreSome() {
    todoDAO.populate(numberOfTodos);
    Response response = target("/todo").request().get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(numberOfTodos, response.readEntity(TodoCollectionDTO.class).getItems().size());
  }

  @Test
  public void insertTodo() {
    todoDAO.reset();
    TodoItemDTO todoItem = new TodoItemDTO();
    todoItem.setText("TODO Item");
    Response response = target("/todo").request()
      .post(Entity.entity(todoItem, MediaType.APPLICATION_JSON_TYPE));
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(1, todoDAO.count());
  }

  @Test
  public void completeTodoOK() {
    todoDAO.populate(numberOfTodos);
    int completed = numberOfTodos / 2;
    TodoItemDTO request = new TodoItemDTO();
    request.setStatus(TodoStatus.COMPLETED);
    Response response = target("/todo/" + completed).request()
      .put(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(1, todoDAO.countCompleted());
    assertEquals(numberOfTodos - 1, todoDAO.countActive());
    assertTrue(todoDAO.getAllItems().stream().filter(TodoItem::isCompleted)
               .findAny().orElse(null).getTodoId() == completed);
  }

  @Test
  public void completeTodoNotFound() {
    todoDAO.populate(numberOfTodos);
    int completed = numberOfTodos + 1;
    TodoItemDTO request = new TodoItemDTO();
    request.setStatus(TodoStatus.COMPLETED);
    Response response = target("/todo/" + completed).request()
      .put(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    assertEquals(0, todoDAO.countCompleted());
    assertEquals(numberOfTodos, todoDAO.countActive());
  }

  @Test
  public void deleteExistingTodo() {
    todoDAO.populate(numberOfTodos);
    int deleted = numberOfTodos / 2;
    Response response = target("/todo/" + deleted).request().delete();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(numberOfTodos - 1, todoDAO.count());
    assertTrue(todoDAO.getAllItems().stream().noneMatch(item -> item.getTodoId() == deleted));
  }

  @Test
  public void deleteNonExistingTodo() {
    todoDAO.populate(numberOfTodos);
    Response response = target("/todo/" + (numberOfTodos+1)).request().delete();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    assertEquals(numberOfTodos, todoDAO.count());
  }

  @Test
  public void deleteCompleted() {
    todoDAO.populate(numberOfTodos);
    Predicate<? super TodoItem> isOddId = item -> item.getTodoId() % 2 == 1;
    todoDAO.getAllItems().stream().filter(isOddId).forEach(TodoItem::complete);
    Response response = target("/todo/completed").request().delete();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertEquals(numberOfTodos / 2, todoDAO.count());
    assertEquals(0, todoDAO.countCompleted());
    assertTrue(todoDAO.getAllItems().stream().noneMatch(isOddId));
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().registerResources(TodoResource.class).bindToRoot().build();
  }
}
