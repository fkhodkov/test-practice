package ru.hh.school.fkhodkov.todomvc.dao;

import org.springframework.stereotype.Component;

@Component
public class TodoNoDBDAOFactory extends TodoDAOFactory {

  public static final TodoDAO todoDAO = new TodoNoDBDAO();

  @Override
  public TodoDAO getTodoDAO() {
    return todoDAO;
  }
}
