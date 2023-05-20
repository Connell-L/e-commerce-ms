package uk.co.louiseconnell.quarkus.panache.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import uk.co.louiseconnell.quarkus.jpa.Customer;

@Entity
@Table(name = "t_purchase_order")
public class PurchaseOrder extends PanacheEntity {

  @Column(name = "purchase_order_date", nullable = false)
  public LocalDate date;

  @ManyToOne
  @JoinColumn(name = "customer_fk")
  public Customer customer;

  @OneToMany(mappedBy = "purchaseOrder")
  public List<OrderLine> orderLines = new ArrayList<>();

  @Column(name = "created_date")
  public LocalDate createdDate = LocalDate.now();

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
        orderLine.purchaseOrder = this;
    }
}
