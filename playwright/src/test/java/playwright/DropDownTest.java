package playwright;

import java.util.List;

import org.testng.annotations.Test;

public class DropDownTest extends BaseTest {
	

	@Test
	public void CalculatorTest() {
		
		launchBrowser(false, false, false);
		SetUrl("https://testsheepnz.github.io/BasicCalculator.html");
		
		DropDownPage DDP = new DropDownPage(page);
	
		String Addition = DDP.basicCalculator("55", "55", "Add");
		System.out.println("Addition result is: "+ Addition);
		
		String Subtraction = DDP.basicCalculator("66", "22", "Subtract");
		System.out.println("Subtraction result is: "+ Subtraction);
		
		String Multiplication = DDP.basicCalculator("2", "8", "Multiply");
		System.out.println("Multiplication result is: "+ Multiplication);
		
		tearDown();
		
	}
	
	@Test
	public void GetDropDownValues() {
		
		launchBrowser(false, false, false);
		SetUrl("https://testsheepnz.github.io/BasicCalculator.html");
		
		DropDownPage DDP = new DropDownPage(page);
		
		List<String> dropdownValues = DDP.getDropdownValues();
		
		//for (String string : dropdownValues) {
		//	System.out.println(string);
		//}
		
		for (int i = 0; i < dropdownValues.size(); i++) {
			System.out.println(dropdownValues.get(i));
		}
		
		tearDown();
		
	}
		

}
