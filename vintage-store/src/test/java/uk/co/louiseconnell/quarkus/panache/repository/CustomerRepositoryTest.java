package uk.co.louiseconnell.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import uk.co.louiseconnell.quarkus.jpa.Customer;

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

    // Check that the number of customers is the same as the number of customers in the list
    long count = repository.count();
    int listAll = repository.listAll().size();
    assertEquals(count, listAll);

    // Create a customer
    Customer customer = new Customer("first name", "last name", "email" );

    repository.persist(customer);
    assertNotNull(customer.getId());

    customer = repository.findById((customer.getId()));
    assertEquals("first name", customer.getFirstName());
    assertEquals("last name", customer.getLastName());
    assertEquals("email", customer.getEmail());
  }

}
