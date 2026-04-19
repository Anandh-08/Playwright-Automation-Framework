package playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LambdaTestLoginPage {
	
	  private Page page;
	  private Locator myAccountDD;
	  private Locator loginDD;
	  private Locator emailId;
	  private Locator password;
	  private Locator login;
	  
	  public LambdaTestLoginPage(Page page) {
	        this.page = page;
	       
	        this.myAccountDD = page.getByRole(AriaRole.BUTTON,
	                new Page.GetByRoleOptions().setName("My account"));
	         
	        this.loginDD = page.getByRole(AriaRole.LINK,
	                new Page.GetByRoleOptions().setName("Login"));
	        
	        this.emailId = page.locator("#input-email");
	        this.password = page.locator("#input-password");
	        this.login = page.locator("input.btn.btn-primary");
	    }
	  
	  
	  public void clickLoginintoLambda(String email, String pass) {
	
		  myAccountDD.hover();
		  loginDD.click();
		  emailId.fill(email);
		  password.fill(pass);
		  login.click();
		  
	  }


}
