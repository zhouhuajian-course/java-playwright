package learn;

import com.microsoft.playwright.*;

public class EvalJavaScript {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      Page page = browser.newPage();
      page.navigate("http://localhost/agreement");

      String title = (String) page.evaluate("document.title");
      String title2 = (String) page.evaluate("() => document.title");
      String title3 = (String) page.evaluate("(prop) => document[prop]", "title");
      String cookie = (String) page.evaluate("(prop) => document[prop]", "cookie");

      JSHandle btnJsHandle = page.evaluateHandle("document.querySelector(\"#accept-btn\")");
      // 可点 不可点 可点
      page.evaluate("() => { document.querySelector(\"#accept-btn\").disabled = false; }");
      page.evaluate("(btn) => { btn.disabled = true; }", btnJsHandle);
      page.evalOnSelector("#accept-btn", "btn => { btn.disabled = false; }");
      page.evalOnSelectorAll("button", "buttons => { buttons[0].innerText='Accept'; buttons[1].innerText='Reject'; }");

      System.out.println(title);
      System.out.println(title2);
      System.out.println(title3);
      System.out.println(cookie);
    }
  }
}
