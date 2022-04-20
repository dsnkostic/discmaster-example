package org.dsnkostic.discmaster.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "screenshot")
public class Screenshot extends ImageBase {

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  protected Screenshot() {
  }

  public Screenshot(Game game, String filename) {
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
