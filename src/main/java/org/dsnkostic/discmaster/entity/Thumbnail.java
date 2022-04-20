package org.dsnkostic.discmaster.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thumbnail")
public class Thumbnail extends ImageBase {

  @OneToOne
  @JoinColumn(name = "game_id")
  private Game game;

  protected Thumbnail() {
  }

  public Thumbnail(Game game, String filename) {
    super(filename);
    this.game = game;
  }

  public Game getGame() {
    return game;
  }

  @Override
  public String toString() {
    return "Image{" +
        "uuid=" + uuid +
        ", filename='" + filename + '\'' +
        ", game=" + game +
        '}';
  }
}
