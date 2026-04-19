package playwright;

import com.microsoft.playwright.Playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;

public class Frames {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser launch = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		BrowserContext newContext = launch.newContext();
		Page page = newContext.newPage();
		
		/*page.navigate("https://demo.automationtesting.in/Frames.html");
		
		page.locator("//a[normalize-space(text())='Single Iframe']").click();
		FrameLocator frameLocator = page.frameLocator("#singleframe");
		frameLocator.locator("input").fill("Yes");
		
		
		page.locator("//a[normalize-space(text())='Iframe with in an Iframe']").click();
		page.frameLocator("#Multiple iframe").frameLocator("iframe").locator("input").fill("Iframe");
		
		playwright.close();*/
		
		page.navigate("https://www.dezlearn.com/nested-iframes-example/");
		System.out.println(page.locator("h2.elementor-heading-title.elementor-size-default").innerText());
		
		//Parent Ifram
		page.frameLocator("#parent_iframe").locator("#u_5_5").click();
		page.waitForLoadState();
		String innerText = page.frameLocator("#parent_iframe").locator("#processing").innerText();
		System.out.println(innerText);
		
		//Child Frame
		String innerText2 = page.frameLocator("#parent_iframe").frameLocator("#iframe1").locator("html>body>h4").innerText();
		System.out.println(innerText2);
		page.frameLocator("#parent_iframe").frameLocator("#iframe1").locator("#u_5_6").click();
		String innerText3 = page.frameLocator("#parent_iframe").frameLocator("#iframe1").locator("#processing").innerText();
		System.out.println(innerText3);
		
	}

}
