package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class Amazon {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		
		page.navigate("https://www.amazon.in/");
		
		page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Account & Lists")).hover();
		page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Your Account")).click();
		
		String innerText = page.locator(".a-row.a-spacing-base").innerText();
		
		System.out.println(innerText);
		
		page.locator("//h2[normalize-space(text())='Your Orders']").click();
		
		page.waitForSelector("h1.a-size-medium-plus.a-spacing-small");
		
		String innerText1 = page.locator("h1.a-size-medium-plus.a-spacing-small").innerText();
		
		System.out.println(innerText1);
		
		page.locator("#ap_email").fill("lebapoh827@okexbit.com");
		page.locator("input#continue").click();
		
		page.waitForLoadState(LoadState.LOAD);
		
		Locator problemMsg = page.locator(".a-box-inner.a-alert-container").first();
		
		if (problemMsg.isVisible()) {
			
			String header = page.locator(".a-alert-heading").first().innerText();
			String content = page.locator(".a-alert-content").first().innerText();
			
			System.out.println(
				    "System gave below Error while login into account\n"
				    + header + "\n"
				    + content
				);
			
		}
	}

}
