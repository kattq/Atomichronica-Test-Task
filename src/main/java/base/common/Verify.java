package base.common;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Verify {

    protected static Logger log = LogManager.getRootLogger();

    public static boolean verifyTrue(Boolean condition, String message) {
        try {
            Assert.assertTrue(condition);
            log.info(message);
            return true;
        } catch (AssertionError e) {
            log.error(message + "\n" + e.getMessage());
            BaseTest.hasFails = true;
            return false;
        }
    }

    public static boolean verifyEquals(String actual, String expected) {
        try {
            Assert.assertEquals(actual.trim(), expected);
            log.info(String.format("Messages are equal.\n Actual is: '%s'\n Expected is: '%s'", actual, expected));
            return true;
        } catch (AssertionError e) {
            log.error("Messages are not equal.\n" + e.getMessage());
            BaseTest.hasFails = true;
            return false;
        }
    }
}
