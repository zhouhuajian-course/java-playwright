package learn;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Codegen {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      // 启动浏览器 slow motion 慢动作 先操作，再暂停5秒
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
      // 创建上下文 隔离
      // context1 page1 page2 page3 cookie1
      // context2 page4 page5 page6 cookie2
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      // 浏览登录 元素定位 元素操作
      page.navigate("http://localhost/login");
      page.locator("input[name=\"username\"]").fill("zhouhuajian");
      page.locator("input[name=\"password\"]").fill("zhouhuajian");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("登录")).click();
      // 断言
      assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("zhouhuajian 欢迎回来！");
    }
  }
}