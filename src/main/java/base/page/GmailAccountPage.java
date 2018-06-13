package base.page;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailAccountPage extends BasePage {
    private static Logger log = LogManager.getRootLogger();

    public GmailAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".T-I.J-J5-Ji.T-I-KE.L3")
    private WebElement newMailButton;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement receiverField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement messageInput;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendMailButton;

    @FindBy(css = ".vh")
    private WebElement infoMessage;

    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#inbox']")
    private WebElement inboxButton;

    @FindBy(css = "tr.zA.zE")
    private WebElement messageFirstRow;

    @FindBy(css = ".ii.gt")
    private WebElement messageBodyText;

    @FindBy(css = ".T-I.J-J5-Ji.T-Pm.T-I-ax7.L3.J-JN-M-I")
    private WebElement allMessagesExpandedSection;

    @FindBy(css = ".ar9.T-I-J3.J-J5-Ji")
    private WebElement deleteButton;

    @FindBy(css = "div.aRv")
    private WebElement primaryTabTextArea;

    public void waitForPageOpened() {
        waitForElementDisplayed(newMailButton, 5);
    }

    public void clickNewEmail() throws Exception {
        log.info("Clicking [New Email] button");
        click(newMailButton);
    }

    private void enterReceiver(String receiverEmail) throws Exception {
        log.info(String.format("Setting receiver email '%s' to Recipients field", receiverEmail));
        write(receiverField, receiverEmail);
        receiverField.sendKeys(Keys.ENTER);
    }

    private void enterSubject(String subject) throws Exception {
        log.info(String.format("Setting subject '%s' to Subject field", subject));
        write(subjectField, subject);
    }

    private void enterMessageBody(String message) throws Exception {
        log.info(String.format("Setting message '%s' to Message Body field", message));
        write(messageInput, message);
    }

    private void clickSendEmail() throws Exception {
        log.info("Clicking [Send] button");
        click(sendMailButton);
    }

    public void sendNewEmail(String to, String subject, String body) throws Exception {
        enterReceiver(to);
        enterSubject(subject);
        enterMessageBody(body);
        clickSendEmail();
    }

    public String getInfoMessageText() {
        waitForElementDisplayed(infoMessage, 2);
        String text = infoMessage.getText();
        log.info("Getting info message text: " + text);
        return text;
    }

    public void selectMessageFirstRow() throws Exception {
        waitForElementDisplayed(messageFirstRow, 2);
        log.info("Clicking message first row");
        click(messageFirstRow);
    }

    public String getMessageBodyText() throws Exception {
        getVisibleElement(messageBodyText);
        String text = messageBodyText.getText();
        log.info("Getting message body text: " + text);
        return text;
    }

    public void navigateToInbox() throws Exception {
        log.info("Clicking [Inbox] button");
        click(inboxButton);
    }

    public void clickDelete() throws Exception {
        log.info("Clicking [Delete] button");
        click(deleteButton);
    }

    public void selectAllMessages() throws Exception {
        log.info("Selecting all messages");
        click(allMessagesExpandedSection);
    }

    public String getPrimaryTabText() {
        String text = primaryTabTextArea.getText();
        log.info("Getting info message text: " + text);
        return text;
    }
}
