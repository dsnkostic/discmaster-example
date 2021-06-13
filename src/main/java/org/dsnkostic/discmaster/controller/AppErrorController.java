package org.dsnkostic.discmaster.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

  private final Logger logger = LoggerFactory.getLogger(AppErrorController.class);

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    Object reason = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

    logger.warn("ERROR found: status: {}, reason: {}", status, reason);
    model.addAttribute("status", status.toString());
    model.addAttribute("reason", reason.toString());
    return "error";
  }
}
