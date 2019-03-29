package ru.hh.school.fkhodkov.todomvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.school.fkhodkov.todomvc.dao.TodoNoDbDaoFactory;
import ru.hh.school.fkhodkov.todomvc.service.TodoService;

import java.util.function.Function;

@Configuration
@Import(NabTestConfig.class)
public class TodoTestConfig {

  @Bean
  Function<String, String> serverPortAwareBean(String jettyBaseUrl) {
    return path -> jettyBaseUrl + path;
  }

  @Bean
  TodoService todoService() {
    return new TodoService(new TodoNoDbDaoFactory());
  }
}
