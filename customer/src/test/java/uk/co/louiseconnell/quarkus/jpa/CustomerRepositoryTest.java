package uk.co.louiseconnell.quarkus.jpa;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    Customer customer = new Customer("first name", "last name", "email" );

    repository.persist(customer);
    assertNotNull(customer.getId());

    customer = repository.findById((customer.getId()));
    assertEquals("first name", customer.getFirstName());
    assertEquals("last name", customer.getLastName());
    assertEquals("email", customer.getEmail());
  }

}
