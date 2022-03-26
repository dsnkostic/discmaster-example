package org.dsnkostic.discmaster.testutils.dbutils;

import java.util.List;
import java.util.UUID;
import org.dsnkostic.discmaster.entity.Game;
import org.hamcrest.Matcher;

public interface DBDataSet {
  List<Game> getGames();
  List<UUID> getGameUUIDS();
  List<Matcher<Game>> getGameMatchers();
}
