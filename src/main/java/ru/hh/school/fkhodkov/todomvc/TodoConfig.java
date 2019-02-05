package ru.hh.school.fkhodkov.todomvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.nab.starter.NabProdConfig;

@Configuration
@ComponentScan("ru.hh.school.fkhodkov.todomvc")
@Import(NabProdConfig.class)
public class TodoConfig {
}
