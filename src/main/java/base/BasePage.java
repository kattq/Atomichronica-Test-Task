package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;


public abstract class BasePage {
    protected static Logger log = LogManager.getRootLogger();

    protected WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void click(WebElement element) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(element));
        waitForElementDisplayed(element, 10);
        sleep(500);
        element.click();
        sleep(500);
    }

    public void write(WebElement element, String text) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(element));
        waitForElementDisplayed(element, 10);
        sleep(500);
        element.sendKeys("");
        element.clear();
        element.sendKeys(text);
    }

    public void waitVisibilityOfElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void setWaitTime(long milliseconds) throws InterruptedException {
        log.info(String.format("Waiting for '%s' milliseconds", milliseconds));
        sleep(milliseconds);
    }

    public WebElement getElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = driver.findElement(by);
        return element;
    }

    public boolean isElementPresentAndDisplay(WebElement element) {
        try {
            boolean isPresent = element.isDisplayed();
            return isPresent;
        } catch (NoSuchElementException e) {
            log.info("Element is not present or displayed");
            return false;
        }
    }

    public boolean waitForElementDisplayed(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)) != null;
        } catch (Exception ex) {
            return wait.until(ExpectedConditions.visibilityOf(element)) != null;
        }
    }

    protected boolean isElementPresentAndDisplay(WebElement element, int timeoutInSeconds) throws InterruptedException {
        final long start = new Date().getTime();
        do {
            try {
                if (element.isDisplayed()) {
                    return true;
                }
            } catch (Exception ignore) {
            }
            sleep(100);
        } while (new Date().getTime() - start < timeoutInSeconds * 1000);
        log.info("Element is not presented or displayed");
        return false;
    }

    public WebElement getVisibleElement(WebElement element) throws Exception {
        WebElement expectedElement = null;
        if (element.isDisplayed()) {
            expectedElement = element;
        }
        if (expectedElement == null) {
            throw new Exception("No elements are displayed");
        }
        return expectedElement;
    }
}

