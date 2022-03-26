package org.dsnkostic.discmaster;

public interface Constants {

  interface GameConstants {
    String GAME_SHORT_URL_1 = "ace";
    String GAME_SHORT_URL_2 = "ktime";
    String GAME_SHORT_URL_3 = "swarrior";
    String GAME_SHORT_URL_4 = "___";

    String GAME_TITLE_1 = "Alpha Centauri";
    String GAME_TITLE_2 = "Killing Time";
    String GAME_TITLE_3 = "Shadow Warrior";
    String GAME_TITLE_4 = "";

    String GAME_DESCRIPTION_1 = "4X game by the Civilization creator";
    String GAME_DESCRIPTION_2 = "Interesting game, but not so good";
    String GAME_DESCRIPTION_3 = "Shadow is my place";
    String GAME_DESCRIPTION_4 = "";
  }

  interface GameTagConstants {
    String GAMETAG_KEY_GENRE = "genre";
    String GAMETAG_KEY_ARTISTIC = "artistic";
    String GAMETAG_KEY_EXTRA = "extra";

    String GAMETAG_VALUE_4X = "4X";
    String GAMETAG_VALUE_FPS = "fps";
    String GAMETAG_VALUE_SCIFI = "sci-fi";
    String GAMETAG_VALUE_INTERWAR = "interwar";
    String GAMETAG_VALUE_CONTEMPORARY = "contemporary";
    String GAMETAG_VALUE_BESTSELLER = "bestseller";
    String GAMETAG_VALUE_GAMEOFTHEYEAR = "game_of_the_year";
    String GAMETAG_VALUE_REVOLUTIONARY = "revolutionary";
  }

  interface ModelConstants {
    String MODEL_VIEW_NAME_GAME_LIST = "game-list";
    String MODEL_VITE_NAME_GAME_DETAILS = "game-details";
    String MODEL_ATTRIBUTE_GAMES = "games";
    String MODEL_ATTRIBUTE_GAME = "game";
  }

}
