package uk.co.louiseconnell.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import uk.co.louiseconnell.quarkus.jdbc.Artist;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestTransaction
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  @Test
  public void shouldCreateAndFindAnArtist() throws SQLException {
    Artist artist = new Artist("name", "bio");

    repository.persist(artist);
    assertNotNull(artist.getId());

    artist = repository.findById(artist.getId());
    assertEquals("name", artist.getName());

  }

  @Test
  public void shouldUpdateAnArtist() throws SQLException {

    long count = repository.count();
    int listAll = repository.listAll().size();
    assertEquals(count, listAll);

    // Create an artist
    Artist artist = new Artist("name", "bio");
    repository.persist(artist);

    // Check the artist has been created
    assertNotNull(artist.getId());
    assertEquals(count + 1, repository.count());

    // Update the artist
    artist.setName("new name");
    artist.setBio("new bio");
    repository.persist(artist);

    // Retrieve the updated artist
    Artist updatedArtist = repository.findById(artist.getId());

    assertAll(
        () -> assertNotNull(artist.getId()),
        () -> assertEquals("new name", artist.getName()),
        () -> assertEquals("new bio", artist.getBio())
    );
  }
}

