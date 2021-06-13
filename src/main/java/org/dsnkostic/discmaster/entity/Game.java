package org.dsnkostic.discmaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(nullable = false, name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  // private Set<Picture> array
  // private Set<Tag> tags;
  // private Set<Recommendation> recommendations;

  public Game() {
  }

  public Game(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public long getId() {
    return id;
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
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
