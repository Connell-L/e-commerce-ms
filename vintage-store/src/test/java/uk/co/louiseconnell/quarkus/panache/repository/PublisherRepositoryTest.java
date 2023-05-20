package uk.co.louiseconnell.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import uk.co.louiseconnell.quarkus.panache.model.Publisher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPublisher() {

    // Check that the number of publishers is the same as the number of publishers in the list
    long count = Publisher.count();
    int listAll = Publisher.listAll().size();
    assertEquals(count, listAll);

    // Create a publisher
    Publisher publisher = new Publisher("name");

    Publisher.persist(publisher);
    assertNotNull(publisher.id);

    // Check that the publisher has been added
    assertEquals(count + 1, Publisher.count());

    // Retrieve the publisher
    publisher = Publisher.findById(publisher.id);
    assertEquals("name", publisher.name);

    // Delete the publisher
    Publisher.deleteById(publisher.id);
    assertEquals(count, Publisher.count());
  }
}
