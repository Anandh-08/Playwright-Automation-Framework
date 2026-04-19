package playwright;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing.StartOptions;
import com.microsoft.playwright.Tracing.StopOptions;
import com.microsoft.playwright.options.WaitUntilState;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    public void launchBrowser(boolean headless, boolean isRecordVideo, boolean isTrace) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        
        if (isRecordVideo) {
          context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
		} else {
			context = browser.newContext();
		}
        
        if (isTrace) {
        	context.tracing().start(new StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        }
        
        page = context.newPage();
    }
    
    
    public void tearDownWithTrace(String testName) {
        context.tracing().stop(
            new StopOptions()
                .setPath(Paths.get("Trace/" + testName + ".zip"))
        );

        context.close();
        browser.close();
        playwright.close();
    }

    public void tearDown() {
        page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    
    public void SetUrl(String Url) {
    	page.navigate(Url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
	}
}