package org.dsnkostic.discmaster.repository;

import java.util.Optional;
import java.util.UUID;
import org.dsnkostic.discmaster.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, UUID> {
  Optional<Game> findOptionalByShortUrl(String shortUrl);
}
