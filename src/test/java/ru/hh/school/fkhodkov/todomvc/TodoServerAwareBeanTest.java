package ru.hh.school.fkhodkov.todomvc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.fkhodkov.todomvc.dao.TodoDao;
import ru.hh.school.fkhodkov.todomvc.dao.TodoNoDbDaoFactory;
import ru.hh.school.fkhodkov.todomvc.dto.TodoCollectionDto;
import ru.hh.school.fkhodkov.todomvc.resource.TodoResource;

import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ContextConfiguration(classes = TodoTestConfig.class)
public class TodoServerAwareBeanTest extends NabTestBase {

  @Inject
  private Function<String, String> serverPortAwareBean;

  private final TodoDao todoDAO = new TodoNoDbDaoFactory().getTodoDao();
  private final int numberOfTodos = 5;

  @Test
  public void testBeanWithNabTestContext() {
    todoDAO.populate(numberOfTodos);
    try (Response response = createRequestFromAbsoluteUrl(serverPortAwareBean.apply("/todo")).get()) {
      assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
      TodoCollectionDto result = response.readEntity(TodoCollectionDto.class);
      assertEquals(numberOfTodos, result.getItems().size());
    }
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().registerResources(TodoResource.class).bindToRoot().build();
  }
}
