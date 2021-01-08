package org.dsnkostic.discmaster.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class GameListController {

  @GetMapping
  public String index(Model model) {
    model.addAttribute("serverTime", DateTimeFormatter
        .ofPattern("yyyy-MMMM-dd HH:mm").format(LocalDateTime.now()));
    return "game-list";
  }

}
