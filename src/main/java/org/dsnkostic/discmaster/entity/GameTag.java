package org.dsnkostic.discmaster.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_tags")
public class GameTag {
  @Id
  @GeneratedValue
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @Column(name = "key")
  private String key;

  @Column(name = "value")
  private String value;

  protected GameTag() {
  }

  public GameTag(Game game, String key, String value) {
    this.game = game;
    this.key = key;
    this.value = value;
  }

  public UUID getUuid() {
    return uuid;
  }

  public Game getGame() {
    return game;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "GameTag{" +
        "uuid=" + uuid +
        ", game=" + game +
        ", key='" + key + '\'' +
        ", value='" + value + '\'' +
        '}';
  }
}
