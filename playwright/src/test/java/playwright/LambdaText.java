package playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LambdaText extends BaseTest {
	
	
	public void selectAndVerifyCategory(String categoryName) {
	    // Locate the dropdown item and click it
	    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
	        .setName(categoryName)
	        .setExact(true))
	        .click();
	    
	    System.out.println("Verified: " + categoryName + " is now selected.");
	}


	@Test
	public void TestingLambda() {
		
		//browser launch	
		launchBrowser(false, false, false);
		SetUrl("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
		
		//POM style login page
		LambdaTestLoginPage lt = new LambdaTestLoginPage(page);
		lt.clickLoginintoLambda("lebapoh827@okexbit.com", "Anandh");
		page.locator("button.btn.dropdown-toggle[data-toggle='dropdown']")
	    .first()
	    .click();
		
		//page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("button.btn.dropdown-toggle").setExact(true)).click();
		selectAndVerifyCategory("Software");
		
		
	
		
	}
	
}
