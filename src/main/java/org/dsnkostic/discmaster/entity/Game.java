package org.dsnkostic.discmaster.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private final List<GameTag> gameTags = new ArrayList<>();

  // private Set<Picture> array
  // private Set<Recommendation> recommendations;

  protected Game() {
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

  public List<GameTag> getGameTags() {
    return gameTags;
  }

  @Override
  public String toString() {
    return "Game{" +
        "uuid=" + uuid +
        ", shortUrl='" + shortUrl + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public static class GameBuilder {
    private final Game game;

    public GameBuilder(String shortUrl, String title, String description) {
      this.game = new Game(shortUrl, title, description);
    }

    public GameBuilder withGameTag(String key, String value) {
      this.game.getGameTags().add(new GameTag(this.game, key, value));
      return this;
    }

    public Game build() {
      return this.game;
    }

    public static GameBuilder gameBuilder(String shortUrl, String title, String description) {
      return new GameBuilder(shortUrl, title, description);
    }
  }
}
