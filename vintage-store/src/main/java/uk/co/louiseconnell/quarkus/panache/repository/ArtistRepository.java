package uk.co.louiseconnell.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uk.co.louiseconnell.quarkus.jdbc.Artist;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {
}
