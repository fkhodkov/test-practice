package ru.hh.school.fkhodkov.todomvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.fkhodkov.todomvc.resource.TodoResource;

@Configuration
@Import(TodoResource.class)
public class TodoJerseyConfig {
}
