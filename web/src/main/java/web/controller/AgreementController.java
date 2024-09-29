package web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgreementController {

  @GetMapping("/agreement")
  public String agreement(HttpServletResponse response) {
    response.addCookie(new Cookie("username", "zhouhuajian"));
    return "agreement";
  }

}
