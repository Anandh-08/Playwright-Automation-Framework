package playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Utility  {
	
	private final Page page;

    public Utility(Page page) {
        this.page = page;
    }

	public void redBusDateSelector(String currentDateType, String monthAndYear, String date) {

	    if (currentDateType.equalsIgnoreCase("TODAY")) {

	        page.getByRole(AriaRole.BUTTON,
	                new Page.GetByRoleOptions().setName("Today"))
	            .click();
	        return;

	    }

	    if (currentDateType.equalsIgnoreCase("TOMORROW")) {

	        page.getByRole(AriaRole.BUTTON,
	                new Page.GetByRoleOptions().setName("Tomorrow"))
	            .click();
	        return;
	    }

	    // If neither TODAY nor TOMORROW, use calendar
	    if (monthAndYear == null || date == null) {
	        throw new IllegalArgumentException(
	                "monthAndYear and date must be provided for custom date selection");
	    }

	    // Open calendar
	    page.getByRole(AriaRole.BUTTON,
	            new Page.GetByRoleOptions().setName("Date of Journey"))
	        .click();

	    while (true) {

	        String displayedMonthYear =
	                page.locator("p[aria-atomic='true']").innerText();

	        if (displayedMonthYear.equalsIgnoreCase(monthAndYear)) {
	            break;
	        }

	        page.locator("(//i[contains(@class,'icon icon-arrow')])[2]").click();
	    }

	    page.getByRole(AriaRole.BUTTON,
	            new Page.GetByRoleOptions().setName(date))
	        .click();
	}

}
