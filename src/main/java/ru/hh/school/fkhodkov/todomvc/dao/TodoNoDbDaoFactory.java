package ru.hh.school.fkhodkov.todomvc.dao;

import org.springframework.stereotype.Component;

@Component
public class TodoNoDbDaoFactory implements TodoDaoFactory {

  private static final TodoDao todoDao = new TodoNoDbDao();

  @Override
  public TodoDao getTodoDao() {
    return todoDao;
  }
}
