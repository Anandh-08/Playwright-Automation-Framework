package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TablesTest {
	
	
	public int FindColumnIdx(Page page, String ColumnName) {

	    Locator columnLoc = page.locator("//table[@id='table1']//th[@class='header']");
	    int colCount = columnLoc.count();

	    for (int i = 0; i < colCount; i++) {

	        String text = columnLoc.nth(i).textContent();

	        if (text != null && text.contains(ColumnName)) {
	            return i;
	        }
	    }

	    // Column not found
	    return -1;
	}
	
	public int FindRowIdxInTable(Page page, String 	RowValue) {
		
		Locator rowLoc = page.locator("table#table1 tbody tr");
		int rowCount = rowLoc.count();
		
		for (int i = 0; i < rowCount; i++) {
			
			 Locator cells = rowLoc.nth(i).locator("td");
			 int cellCount = cells.count();
			 
			 for (int j = 0; j < cellCount; j++) {
		            String cellText = cells.nth(j).textContent();

		            if (cellText != null && cellText.contains(RowValue)) {
		                return i;
		            }
		        }
			
		}
		
		return -1;
		
	}
	
	
	

	public static void main(String[] args) {
		
			Playwright playwright = Playwright.create();
			Browser launch = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			BrowserContext newContext = launch.newContext();
			Page page = newContext.newPage();
			
			page.navigate("https://the-internet.herokuapp.com/tables");
			
			TablesTest utils = new TablesTest();
			int ColIdx = utils.FindColumnIdx(page, "Last Name");
			System.out.println(ColIdx);
			
			int rowIdx = utils.FindRowIdxInTable(page, "John");
			System.out.println(rowIdx);
			
			
			
			
			/*List<String> Header = page.locator("//table[@id='table1']//th[@class='header']").allInnerTexts();
			System.out.println(String.join(" | ", Header));
			
			//Printing specified row that contains mentioned name value
			List<String> all = page.locator("//table[@id='table1']//tbody/tr").allInnerTexts();
			
			for (String RowValues : all) {
				
				if (RowValues.contains("Conway")) {
					String[] split = RowValues.split("\\t");
					System.out.println(String.join(" | ", split));
				}
				
			}*/
			
			// Printing Column vise values from Header to row bottom
			/*List<String> HeadtoRow = page.locator(
					"//table[@id='table1']//th[2] | //table[@id='table1']//tbody/tr/td[2]"
					).allInnerTexts();
			
			for (String HToR : HeadtoRow) {
				
				System.out.println(HToR);
				
			}*/
			
		
			//Printing all row vlaues
			/*Locator rows = page.locator("table#table1 tbody tr");

			for (int i = 0; i < rows.count(); i++) {
			    Locator cells = rows.nth(i).locator("td");

			    for (int j = 0; j < cells.count(); j++) {
			        System.out.print(cells.nth(j).textContent() + " | ");
			    }
			    System.out.println();
			}*/
	

			/*List<String> WholeTable = page.locator("#table1").allInnerTexts();
			
			for (String tabledatas : WholeTable) {
				
				System.out.println(tabledatas);
				
			}*/

	}

}
