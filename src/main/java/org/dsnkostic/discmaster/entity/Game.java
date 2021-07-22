package org.dsnkostic.discmaster.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
  @Id
  @GeneratedValue
  private UUID uuid;

  @Column(unique = true, name="short_url", length = 16)
  private String shortUrl;

  @Column(nullable = false, name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  // private Set<Picture> array
  // private Set<Tag> tags;
  // private Set<Recommendation> recommendations;

  public Game() {
  }

  public Game(String shortUrl, String title, String description) {
    this.shortUrl = shortUrl;
    this.title = title;
    this.description = description;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Game{" +
        "id=" + uuid +
        ", shortUrl='" + shortUrl + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
