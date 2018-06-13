package base.page;

import base.BasePage;
import base.page.GmailAccountPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage extends BasePage {
    private static Logger log = LogManager.getRootLogger();

    public GmailLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#identifierId")
    private WebElement emailField;

    @FindBy(css = "#identifierNext")
    private WebElement loginNextButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(css = "#passwordNext")
    private WebElement passwordNextButton;


    private void enterEmail(String email) throws Exception {
        log.info(String.format("Setting email '%s' to Email field", email));
        write(emailField, email);
    }

    private void clickLoginNext() throws Exception {
        log.info("Clicking [Next] button");
        click(loginNextButton);
    }

    private void enterPassword(String password) throws Exception {
        log.info(String.format("Setting password '%s' to Password field", password));
        write(passwordField, password);
    }

    private void clickPasswordNext() throws Exception {
        log.info("Clicking [Next] button");
        click(passwordNextButton);
    }

    public GmailAccountPage logIn(String email, String password) throws Exception {
        enterEmail(email);
        clickLoginNext();
        enterPassword(password);
        clickPasswordNext();
        return new GmailAccountPage(driver);
    }
}
