package playwright;

import static org.testng.Assert.assertEquals;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class GetBy {

	public static void main(String[] args) {
			
		Playwright playwright = Playwright.create();
		Browser launch = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext newContext = launch.newContext();
		Page page = newContext.newPage();
		
		
		/*page.navigate("https://demowebshop.tricentis.com/login");
		page.getByAltText("Tricentis Demo Web Shop").click();
		System.out.println(page.url());*/
		
		String Product = "Black & White Diamond Heart";
		
		page.navigate("https://demowebshop.tricentis.com/");
		page.locator("#small-searchterms").fill(Product);
		page.locator(".button-1.search-box-button").click();
		
		Locator productTitles = page.locator(".product-title");
		
		for (String a : productTitles.allInnerTexts()) {
			
			if (a.contains(Product)) {
				System.out.println("Product found successfully");
				
			} else {
				System.out.println("product not found.");
			}
			
		}
		
		if (productTitles.allInnerTexts().contains(Product)) {
			
			page.locator("input[value='Add to cart']").click();
			page.locator(".content").waitFor();
			System.out.println(page.locator(".content").textContent());
			
			page.locator("//a[normalize-space(text())='shopping cart']").click();
			page.locator(".page-title").waitFor();
			
			System.out.println(page.locator(".page-title").innerText());
		}
		

	}

}
		