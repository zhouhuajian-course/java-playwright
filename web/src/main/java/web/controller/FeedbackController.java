package web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

  @GetMapping("/feedback")
  public String feedback() {
    return "feedback";
  }

  @PostMapping("/feedback")
  public String feedback(@RequestParam String content, @RequestParam String email, Model model) {
    String type = "info";
    String message = "反馈成功！处理结果，我们将通过邮件发送给您！";
    if (content.length() < 10) {
      type = "error";
      message = "请输入10个字以上的反馈内容！";
    }
    model.addAttribute("type", type);
    model.addAttribute("message", message);
    return "feedback";
  }

}
