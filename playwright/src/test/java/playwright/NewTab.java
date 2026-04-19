package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class NewTab {

	public static void main(String[] args) {
		
			Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://the-internet.herokuapp.com/frames");
            /*Page popup = page.waitForPopup(() -> { page.click("//a[text()='Click Here']"); });
    
            popup.waitForLoadState();
            System.out.println(popup.title()); 
            page.bringToFront();
            System.out.println(page.title());
            popup.close(); */
            
            
            
            /*page.locator("#dropdown").waitFor();
            page.locator("#dropdown").selectOption("2");
            System.out.println(page.locator("option[disabled='disabled']").innerText());*/
            
            
            //page.locator("a:has-text('Nested Frames')").click();
            page.locator("text=Nested Frames").click();
            System.out.println(page.url());
    
            
           // playwright.close();
            
	}

}
