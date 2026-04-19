package playwright;

import com.microsoft.playwright.*;

public class HandlingAlerts {

    public static void main(String[] args) {
        // Use try-with-resources to ensure auto-closing of resources
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // ====== 1. SIMPLE ALERT (Accept) ======
            // onceDialog triggers only for the next dialog, then detaches itself
            page.onceDialog(dialog -> {
                System.out.println("Alert says: " + dialog.message());
                dialog.accept();
            });
            page.locator("button:has-text('Click for JS Alert')").click();
            System.out.println("Result 1: " + page.locator("#result").innerText());

            // ====== 2. CONFIRM (Dismiss) ======
            page.onceDialog(dialog -> {
                System.out.println("Confirm says: " + dialog.message());
                dialog.dismiss();
            });
            page.locator("button:has-text('Click for JS Confirm')").click();
            System.out.println("Result 2: " + page.locator("#result").innerText());

            // ====== 3. CONFIRM (Accept) ======
            page.onceDialog(dialog -> {
                System.out.println("Confirm says: " + dialog.message());
                dialog.accept();
            });
            page.locator("button:has-text('Click for JS Confirm')").click();
            System.out.println("Result 3: " + page.locator("#result").innerText());
            
            // ====== 4. Enter data in prompt ======
            page.onceDialog(dialog -> {
                System.out.println("Confirm says: " + dialog.message());
                dialog.accept("Anandh");
            });
            page.locator("button:has-text('Click for JS Prompt')").click();
            System.out.println("Result 4: " + page.locator("#result").innerText());
            
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}