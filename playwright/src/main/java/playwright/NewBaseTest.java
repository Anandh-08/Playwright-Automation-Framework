package playwright;

import java.nio.file.Paths;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

public class NewBaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    /* ================= CORE LAUNCHER ================= */

    private void launchBrowserInternal(boolean headless, Browser.NewContextOptions contextOptions, boolean enableTrace) {

        playwright = Playwright.create();

        browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(headless));

        context = (contextOptions == null)
                ? browser.newContext()
                : browser.newContext(contextOptions);

        if (enableTrace) { context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));}

        page = context.newPage();
    }

    /* ================= PUBLIC LAUNCH METHODS ================= */

    public void launchBrowser() {
        launchBrowserInternal(false, null, false);
    }

    public void launchBrowserHeadless() {
        launchBrowserInternal(true, null, false);
    }

    public void launchBrowserWithVideo() {
        Browser.NewContextOptions options =
            new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("Videos"))
                .setRecordVideoSize(1280, 720);

        launchBrowserInternal(false, options, false);
    }

    public void launchBrowserWithTrace() {
        launchBrowserInternal(false, null, true);
    }

    /* ================= TEARDOWN ================= */

    public void tearDownWithTrace(String testName) {
        if (context != null) {
            context.tracing().stop(
                new Tracing.StopOptions()
                    .setPath(Paths.get("Trace/" + testName + ".zip"))
            );
            context.close();
        }

        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    /* ================= COMMON ACTIONS ================= */

    public void setUrl(String url) {
        page.navigate(
            url,
            new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }
}
