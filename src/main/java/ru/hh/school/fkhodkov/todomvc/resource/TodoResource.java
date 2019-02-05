package ru.hh.school.fkhodkov.todomvc.resource;

import org.springframework.beans.factory.annotation.Autowired;

import ru.hh.school.fkhodkov.todomvc.dto.TodoCollectionDTO;
import ru.hh.school.fkhodkov.todomvc.dto.TodoItemDTO;
import ru.hh.school.fkhodkov.todomvc.exceptions.TodoNotFoundException;
import ru.hh.school.fkhodkov.todomvc.service.TodoService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/todo")
public class TodoResource {
  private final TodoService todoService;

  @Autowired
  TodoResource(TodoService todoService) {
    this.todoService = todoService;
  }

  Response todoNotFound(Integer todoId) {
    return Response.status(Response.Status.NOT_FOUND.getStatusCode(),
                           "TODO #" + todoId + " not found")
      .build();
  }

  @GET
  @Produces("application/json")
  public TodoCollectionDTO getTodoItems() {
    return todoService.getTodoItems();
  }

  @GET
  @Path("active")
  @Produces("application/json")
  public TodoCollectionDTO getActiveTodoItems() {
    return todoService.getActiveTodoItems();
  }

  @GET
  @Path("completed")
  @Produces("application/json")
  public TodoCollectionDTO getCompletedTodoItems() {
    return todoService.getCompletedTodoItems();
  }

  @POST
  @Consumes("application/json")
  public Response addTodoItem(TodoItemDTO todoItem) {
    todoService.addTodoItem(todoItem);
    return Response.status(Response.Status.OK).build();
  }

  @PUT
  @Path("{id}")
  public Response changeTodoStatus(@PathParam("id") Integer todoId, TodoItemDTO dto) {
    try {
      todoService.changeTodoStatus(todoId, dto.getStatus());
      return Response.status(Response.Status.OK).build();
    } catch (TodoNotFoundException e) {
      return todoNotFound(todoId);
    }
  }

  @DELETE
  @Path("completed")
  public Response deleteCompletedTodo() {
    todoService.deleteCompletedTodo();
    return Response.status(Response.Status.OK).build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteTodo(@PathParam("id") Integer todoId) {
    try {
      todoService.deleteTodo(todoId);
      return Response.status(Response.Status.OK).build();
    } catch (TodoNotFoundException e) {
      return todoNotFound(todoId);
    }
  }
}
