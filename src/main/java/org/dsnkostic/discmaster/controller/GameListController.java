package org.dsnkostic.discmaster.controller;

import org.dsnkostic.discmaster.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller("/")
public class GameListController {

  private final GameRepository gameRepository;

  @Autowired
  public GameListController(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("games", gameRepository.findAll());
    return "game-list";
  }

  @GetMapping("/game/{id}")
  public String getGame(@PathVariable("id") long id, Model model) {
    var game = gameRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with " + id + " could not be found"));
    model.addAttribute("game", game);
    return "game-details";
  }
}
