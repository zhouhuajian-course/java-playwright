package learn;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class LocateElement {
  public static void main(String[] args) {
    try(Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
      Page page = browser.newPage();
      page.navigate("http://localhost/signup");
      // 定位 <input name="username" placeholder="请输入账号" type="text" />
      Locator locator = page.getByPlaceholder("请输入账号");
      Locator locator1 = page.locator("input[name=\"username\"]");
      Locator locator2 = page.locator("//input[@name=\"username\"]");

      System.out.println(locator.getAttribute("name"));
      System.out.println(locator1.getAttribute("placeholder"));
      System.out.println(locator2.isVisible());

      // username 请输入账号 true

      // 定位 <button class="btn" type="submit" id="signup-btn" disabled>注册</button>
      Locator locator3 = page.getByText("注册", new Page.GetByTextOptions().setExact(true));
      Locator locator4 = page.locator("#signup-btn");
      Locator locator5 = page.locator(".btn").first();
      Locator locator6 = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("注册"));
      Locator locator7 = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setDisabled(true));

      System.out.println(locator3.getAttribute("id"));
      System.out.println(locator4.getAttribute("class"));
      System.out.println(locator5.innerText());
      System.out.println(locator6.isDisabled());
      System.out.println(locator7.isEnabled());

      // signup-btn btn 注册 true false

      // 定位 <p>这是<strong>协议</strong>内容1</p>
      //     <p>这是<strong>协议</strong>内容2</p>
      Locator locator8 = page.locator("//div").nth(0).getByRole(AriaRole.PARAGRAPH);
      System.out.println(locator8.count());
      List<Locator> locatorList = locator8.all();
      for (Locator loc : locatorList) {
        System.out.println(loc.innerHTML());
      }

      // 2 这是<strong>协议</strong>内容1 这是<strong>协议</strong>内容2
    }
  }
}
