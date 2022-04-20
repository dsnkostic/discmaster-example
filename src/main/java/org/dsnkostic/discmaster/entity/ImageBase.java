package org.dsnkostic.discmaster.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ImageBase {
  @Id
  @GeneratedValue
  protected UUID uuid;

  @Column(unique = true, name="filename", nullable = false)
  protected String filename;

  protected ImageBase() {
  }

  protected ImageBase(String fileName) {
    this.filename = fileName;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getFilename() {
    return filename;
  }
}
