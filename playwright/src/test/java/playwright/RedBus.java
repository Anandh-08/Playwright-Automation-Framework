package playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RedBus extends BaseTest {

	public static void main(String[] args) {
		
		FirstTest test = new FirstTest();
		test.launchBrowser(false, false, false);

		test.SetUrl("https://www.redbus.in/");
		
		test.page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("From")).fill("coimbatore");
		test.page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Coimbatore")).first().click();
		
		
		test.page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("To")).fill("Chennai");
		test.page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Chennai")).first().click();
		
		//select Date
		
		Utility date = new Utility(test.page);
		date.redBusDateSelector("Someother", "May 2026", "18");
		
		test.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search buses")).click();
		
		String innerText = test.page.locator("//div[@class='title___d9d9bd']/following-sibling::p[1]").innerText();
		String dateText = test.page.locator("//span[text()='Date of journey']/following-sibling::div").innerText();
		
		System.out.println(innerText +" for the date :"+ dateText);
	
		
	}

}
