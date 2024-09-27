package learn;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstScript {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      Page page = browser.newPage();
      page.navigate("http://localhost/");
      String title = page.title();
      String content = page.content();
      System.out.println(title);
      System.out.println(content);
    }
  }
}
