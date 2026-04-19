package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ConextTest {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context1 = browser.newContext();
		//BrowserContext context2 = browser.newContext();
		Page newPage1 = context1.newPage();
		//Page newPage2 = context2.newPage();
		
		newPage1.navigate("https://letcode.in/edit");
		//newPage2.navigate("https://www.apple.com/");
	
		if (newPage1.locator("id=noEdit").isEnabled()) {
			
			newPage1.fill("id=noEdit", "Editable");
		} else {
			
			String description = newPage1.locator("id=noEdit").getAttribute("placeholder").toString();
			System.out.println("Field '" +description+ "' was not enabled to enter text values.");
		}
		
		//context1.close();
        //context2.close();
        //browser.close();
        //playwright.close();
	}

}
