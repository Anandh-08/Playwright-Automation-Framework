package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RoughtClass {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext newContext = browser.newContext();
		Page page = newContext.newPage();
		
		page.navigate("https://www.saucedemo.com/");
		//page.waitForLoadState(LoadState.LOAD);
		//page.waitForURL("**/demo.com");
		
		page.locator("text=Login").click();
		
		Locator locator = page.locator(".error-message-container.error");
		System.out.println(locator.innerText());
		
		page.locator("svg.svg-inline--fa.fa-times.fa-w-11").click();
		
		page.getByPlaceholder("Username").fill("standard_user");
		page.getByPlaceholder("Password").fill("secret_sauce");
		
		page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Login")).click();
		
		assertThat(page.getByText("Swag Labs")).isVisible();
		
		page.locator(".product_sort_container").selectOption("hilo");
		
		String selectedText =
			    page.locator(".product_sort_container option:checked").innerText();

			System.out.println(selectedText); // Price (high to low)
			
			
		//loop
		Locator options = page.locator(".product_sort_container option");

		for (int i = 0; i < options.count(); i++) {
			    System.out.println(
			        "DD value " + (i + 1) + ": " + options.nth(i).innerText()
			    );
			}
		
		//System.out.println(page.title());
		
		playwright.close();

	}

}
