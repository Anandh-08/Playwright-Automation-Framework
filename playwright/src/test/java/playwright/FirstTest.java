package playwright;

public class FirstTest extends BaseTest {

	public static void main(String[] args) {

		FirstTest test = new FirstTest();
		test.launchBrowser(false, false, true);

		test.SetUrl("https://www.saucedemo.com/");
		
		test.page.fill("id=user-name", "standard_user");
		test.page.fill("id=password", "secret_sauce");
		test.page.locator("id=login-button").click();
		
		test.page.locator("//button[text()='Open Menu']").click();
		test.page.locator("id=react-burger-cross-btn").click();
		
		String title = test.page.locator(".app_logo").innerText();
		System.out.println(title);

		
		test.tearDownWithTrace("FirstTest1");
		// test.page.close();
		// test.browser.close();

		// test.tearDown();

	}

}
