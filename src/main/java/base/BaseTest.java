package base;

import base.common.TestData;
import base.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

    public class BaseTest {
        protected WebDriver driver;
        public static boolean hasFails;

        @Parameters({"browser"})
        @BeforeMethod(alwaysRun = true)
        public void setUp() throws IOException {
            hasFails = false;
            driver = DriverFactory.getNewDriver();
            driver.get(TestData.URL_HOME_PAGE);

        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {
            System.out.println("Test finished...");
            if (driver != null) {
                driver.quit();
            }
        }

        protected final void throwIfVerificationFailed() throws Exception {
            if (hasFails) {
                throw new Exception("Test has uncritical errors");
            }
        }
}
