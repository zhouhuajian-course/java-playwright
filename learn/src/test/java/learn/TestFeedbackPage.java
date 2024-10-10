package learn;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.*;

import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

@UsePlaywright
public class TestFeedbackPage {

  @Test
  void testPageBasic(Playwright playwright, Browser browser, BrowserContext context, Page page) {
    page.navigate("http://localhost/feedback");
    assertThat(page.getByText("（接收处理结果）")).isVisible();
    assertThat(page.getByRole(AriaRole.HEADING)).containsText("用户反馈");
    assertThat(page.locator("input[name=\"email\"]")).hasValue("user@email.com");
    // playwright browser 共用
    System.out.println(playwright);
    System.out.println(browser);
    // context page
    System.out.println(context);
    System.out.println(page);

    assertThat(page).hasTitle("用户反馈");
    assertThat(page.locator("input[name=\"email\"]")).hasAttribute("type", "email");
  }

  @Test
  void testFeedbackFail(Playwright playwright, Browser browser, BrowserContext context, Page page) {
    page.navigate("http://localhost/feedback");
    page.locator("textarea[name=\"content\"]").fill("123");
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("提交")).click();
    assertThat(page.locator("#message")).containsText("请输入10个字以上的反馈内容！");

    System.out.println(playwright);
    System.out.println(browser);
    System.out.println(context);
    System.out.println(page);
  }

  @Test
  void testFeedbackSuccess(Page page) {
    page.navigate("http://localhost/feedback");
    page.locator("textarea[name=\"content\"]").fill("111111111111111111111111111");
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("提交")).click();
    assertThat(page.locator("#message")).containsText("反馈成功！处理结果，我们将通过邮件发送给您！");
  }

}