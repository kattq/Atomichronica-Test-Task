package base.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    protected static Logger log = LogManager.getLogger();

    public static WebDriver getNewDriver() throws IOException {
        System.setProperty("log4j.configurationFile", "/src/test/resources/log4j2.xml");
        log.debug("Test started with Browser ::: Chrome\n");
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties"));
        String driverLocation;
        DesiredCapabilities capabilities;
        WebDriver driver;
        driverLocation = prop.getProperty("chrome.driver.location.windows");
        System.setProperty("webdriver.chrome.driver", driverLocation);

        capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.addArguments("--disable-bundled-ppapi-flash");
        options.addArguments("-incognito");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--lang=en_US.UTF-8");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }
}

