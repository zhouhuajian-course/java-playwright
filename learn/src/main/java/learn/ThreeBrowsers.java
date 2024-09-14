package learn;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Arrays;
import java.util.List;

public class ThreeBrowsers {
  public static void main(String[] args) {
    try(Playwright playwright = Playwright.create()) {

      List<BrowserType> browserTypes = Arrays.asList(
        playwright.chromium(),
        playwright.webkit(),
        playwright.firefox()
      );

      BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
      for (BrowserType browserType : browserTypes) {
        System.out.println(browserType.name() + " " + browserType.executablePath());
        // 启动浏览器
        try (Browser browser = browserType.launch(launchOptions)) {
          Page page = browser.newPage();
          // 等3秒
          page.waitForTimeout(3000);
          page.navigate("http://localhost/");
          // 再等3秒
          page.waitForTimeout(3000);
          String title = page.title();
          System.out.println(title);
        }
      }

    }
  }
}
