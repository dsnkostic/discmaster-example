package org.dsnkostic.discmaster.repository;

import java.util.Optional;
import org.dsnkostic.discmaster.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
  Optional<Game> findOptionalByShortUrl(String shortUrl);
}
