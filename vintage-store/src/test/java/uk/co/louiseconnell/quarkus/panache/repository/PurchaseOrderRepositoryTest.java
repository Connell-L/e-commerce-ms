package uk.co.louiseconnell.quarkus.panache.repository;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import uk.co.louiseconnell.quarkus.jdbc.Artist;
import uk.co.louiseconnell.quarkus.jpa.Customer;
import uk.co.louiseconnell.quarkus.panache.model.Book;
import uk.co.louiseconnell.quarkus.panache.model.Language;
import uk.co.louiseconnell.quarkus.panache.model.OrderLine;
import uk.co.louiseconnell.quarkus.panache.model.Publisher;
import uk.co.louiseconnell.quarkus.panache.model.PurchaseOrder;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

  @Inject
  CustomerRepository customerRepository;

  @Test
  @TestTransaction
  public void testShouldCreateAndFindPurchaseOrder() {

    // Creates an Artist
    Artist artist = new Artist("artist name", "artist bio");

    // Creates a Publisher
    Publisher publisher = new Publisher("publisher name");

    // Creates a Book
    Book book = new Book();
    book.title = "book title";
    book.nbOfPages = 500;
    book.language = Language.ENGLISH;
    book.price = new BigDecimal(10);
    book.isbn = "123-456-789";

    // Sets the relationships
    book.publisher = publisher;
    book.artist = artist;

    // Persists the book
    Book.persist(book);

    // Creates a Customer
    Customer customer = new Customer(
        "Customer first name",
        "Customer last name",
        "Customer email");

    // Persists the customer
    customerRepository.persist(customer);

    // Creates an order line
    OrderLine orderLine = new OrderLine();
    orderLine.item = book;
    orderLine.quantity = 2;

    // Persists the order line
    OrderLine.persist(orderLine);

    // Creates a purchase order
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.date = LocalDate.now();
    purchaseOrder.customer = customer;
    purchaseOrder.addOrderLine(orderLine);

    // Persists the purchase order
    PurchaseOrder.persist(purchaseOrder);
  }
}
