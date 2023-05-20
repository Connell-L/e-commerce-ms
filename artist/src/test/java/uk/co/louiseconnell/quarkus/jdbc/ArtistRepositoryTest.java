package uk.co.louiseconnell.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
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
    // Create an artist
    Artist artist = new Artist("name", "bio");
    repository.persist(artist);
    assertNotNull(artist.getId());

    // Update the artist
    artist.setName("new name");
    artist.setBio("new bio");
    repository.persist(artist);

    // Retrieve the updated artist
    Artist updatedArtist = repository.findById(artist.getId());
    assertEquals("new name", updatedArtist.getName());
    assertEquals("new bio", updatedArtist.getBio());
  }
}
