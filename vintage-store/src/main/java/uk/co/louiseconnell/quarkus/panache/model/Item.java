package uk.co.louiseconnell.quarkus.panache.model;

import java.math.BigDecimal;
import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uk.co.louiseconnell.quarkus.jdbc.Artist;

@Entity
@Table(name = "t_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {

  @Column(length = 100, nullable = false)
  public String title;
  @Column(length = 3000)
  public String description;
  @Column(nullable = false)
  public BigDecimal price;
  @Column(name = "created_date")
  public Instant createdDate = Instant.now();
  @ManyToOne
  @JoinColumn(name = "artist_fk")
  public Artist artist;
}
