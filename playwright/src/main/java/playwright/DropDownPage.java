package playwright;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DropDownPage {

    private Page page;
    private Locator firstNumber;
    private Locator secondNumber;
    private Locator operationDD;
    private Locator calcButton;
    private Locator ansField;

    public DropDownPage(Page page) {
        this.page = page;
        this.firstNumber = page.locator("#number1Field");
        this.secondNumber = page.locator("#number2Field");
        this.operationDD = page.locator("#selectOperationDropdown");
        this.calcButton = page.locator("#calculateButton");
        this.ansField = page.locator("#numberAnswerField");
    }

    public String basicCalculator(String a, String b, String operation) {
        firstNumber.fill(a);
        secondNumber.fill(b);
        operationDD.selectOption(operation);
        calcButton.click();
        ansField.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        return ansField.inputValue();
    }
    
    public List<String> getDropdownValues() {
    	
    	return operationDD.allInnerTexts();
    	
    }
}
