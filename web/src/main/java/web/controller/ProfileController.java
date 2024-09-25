package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
public class ProfileController {

  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

  @PostMapping("/profile")
  public String profile(
    @RequestParam String name,
    @RequestParam MultipartFile avatar,
    @RequestParam String gender,
    @RequestParam String birthday,
    @RequestParam String intro,
    @RequestParam String nationality,
    @RequestParam String[] languages
  ) {
    System.out.println("姓名：" + name);
    System.out.println("头像：" + avatar.getOriginalFilename());
    System.out.println("性别：" + gender);
    System.out.println("生日：" + birthday);
    System.out.println("简介：" + intro);
    System.out.println("民族：" + nationality);
    System.out.println("语言：" + Arrays.toString(languages));
    return "redirect:/profile";
  }

}
