package playwright;

import com.microsoft.playwright.Locator;

public class ForLoop extends BaseTest {

	public static void main(String[] args) {
		
		ForLoop test = new ForLoop();
		test.launchBrowser(false, true, true);

		test.SetUrl("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
		
		//test.page.getByRole(AriaRole.LINK,
		     //   new Page.GetByRoleOptions().setName("Shop by Category"));
		
		test.page.locator("a.icon-left.both.text-reset").click();
		
		
		Locator list = test.page.locator("ul.nav bar-nav.vertical span.title");
		
		int count = list.count();
		
		for (int i = 0; i < count; i++) {
		
			String name = list.nth(i).innerText().trim();
			System.out.println(name);
		}

		test.tearDownWithTrace("290126TC");
		//test.tearDown();

	}

}
