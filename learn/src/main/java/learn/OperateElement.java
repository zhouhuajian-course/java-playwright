package learn;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class OperateElement {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false).setSlowMo(3000));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      // 访问
      page.navigate("http://localhost/profile");
      page.locator("input[name=\"name\"]").fill("周华健");
      page.locator("input[name=\"avatar\"]").setInputFiles(Paths.get("learn/src/main/resources/avatar.svg"));
      page.getByLabel("男").check();
      page.locator("input[name=\"birthday\"]").fill("2024-09-25");
      page.locator("textarea[name=\"intro\"]").fill("这是简介");
      page.getByRole(AriaRole.COMBOBOX).selectOption("汉族");
      page.getByLabel("普通话").check();
      page.getByLabel("粤语").check();
      page.getByLabel("英语").check();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("提交")).click();
    }
  }
}