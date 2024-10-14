package learn;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Screenshot {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(true));

      BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Path.of("learn")).setRecordVideoSize(1280, 720));
      // 12秒
      Page page = context.newPage();
      Page page2 = context.newPage();

      page.navigate("http://localhost/house");
      page2.navigate("http://localhost/house");
      page.waitForTimeout(3000);

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个屋顶")).click();
      page2.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个大门")).click();
      page.waitForTimeout(3000);

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个墙壁")).click();
      page2.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个墙壁")).click();
      page.waitForTimeout(3000);

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个大门")).click();
      page2.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("画个屋顶")).click();
      page.waitForTimeout(3000);

      page.screenshot(new Page.ScreenshotOptions().setPath(Path.of("learn/page-screenshot.png")));
      page.locator("#canvas").screenshot(new Locator.ScreenshotOptions().setPath(Path.of("learn/element-screenshot.png")));

      System.out.println(page.video().path());
      System.out.println(page2.video().path());

      context.close();
    }
  }
}