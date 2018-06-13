package Test;

import base.*;
import base.common.TestData;
import base.common.Verify;
import base.page.GmailAccountPage;
import base.page.GmailLoginPage;
import org.testng.annotations.Test;

public class GmailTest extends BaseTest {

    @Test
    public void GmailControl() throws Exception {
        GmailLoginPage gmailLoginPage = new GmailLoginPage(driver);
        GmailAccountPage gmailAccountPage = gmailLoginPage.logIn(TestData.TEST_USER_LOGIN, TestData.TEST_USER_PASSWORD);
        gmailAccountPage.waitForPageOpened();
        gmailAccountPage.clickNewEmail();
        gmailAccountPage.sendNewEmail(TestData.TEST_USER_LOGIN, TestData.TEST_SUBJECT, TestData.TEST_MESSAGE);
        Verify.verifyEquals(gmailAccountPage.getInfoMessageText(), TestData.SUCCESSFUL_EMAIL_MESSAGE);
        gmailAccountPage.navigateToInbox();
        gmailAccountPage.selectMessageFirstRow();
        Verify.verifyEquals(gmailAccountPage.getMessageBodyText(), TestData.TEST_MESSAGE);
        gmailAccountPage.navigateToInbox();
        gmailAccountPage.selectAllMessages();
        gmailAccountPage.clickDelete();
        Verify.verifyEquals(gmailAccountPage.getPrimaryTabText(), TestData.EMPTY_INBOX_MESSAGE);

        throwIfVerificationFailed();
    }
}
