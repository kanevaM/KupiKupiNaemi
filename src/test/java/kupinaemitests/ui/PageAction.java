package kupinaemitests.ui;

import kupinaemitests.util.Constants;
import org.apache.commons.lang3.Validate;
import org.fest.assertions.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PageAction {
    public static final Logger LOGGER = LoggerFactory.getLogger(PageAction.class);
    private static WebDriver driver;

    public PageAction(WebDriver driver) {
        this.driver = driver;
    }

    private void trueClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();
    }

    public void typeText(WebElement element, String text) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Typing text:" + text);
        //element.click();
        this.trueClick(element);
        element.clear();
        element.sendKeys(text);
    }

    public void clickButton(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Clicking button:" + element.getText());
        // element.click();
        this.trueClick(element);
    }

    public void gotoPage(String page) {
        LOGGER.info("Navigating to " + Constants.KUPINAEMI_HOME_PAGE + page + " page");
        driver.navigate().to(Constants.KUPINAEMI_HOME_PAGE + page);
    }

    public String getText(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        String text = element.getText().trim();
        LOGGER.info("Extracting text");
        LOGGER.info("Text found is:" + text);
        return text;
    }

    public boolean isVisible(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Element displayed:" + element.isDisplayed());
        return element.isDisplayed();
    }

    public boolean isSelected(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Element selected:" + element.isSelected());
        return element.isSelected();
    }

    public boolean isEnabled(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Element selected:" + element.isEnabled());
        return element.isEnabled();
    }

    public void clear(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        LOGGER.info("Clearing element:" + element.getText());
        element.clear();
    }


    public void checkContains(String actual, String expected, String info) {
        LOGGER.info("Checking: " + info);
        Assertions.assertThat(actual).as(info).contains(expected);
    }

    public void selectByVisibleText(Select select, String text) {
        Validate.notNull(select, "Select should not be null");
        LOGGER.info("Selecting by visible text:" + text);
        select.selectByVisibleText(text);
    }

    public void selectByVisibleText(WebElement selectElement, String text) {
        //selectElement.click();
        this.trueClick(selectElement);
        Select select = new Select(selectElement);
        Validate.notNull(select, "Select should not be null");
        LOGGER.info("Selecting by visible text:" + text);
        select.selectByVisibleText(text);
    }

    public void checkEquals(String actual, String expected, String info) {
        LOGGER.info("Checking: " + info);
        Assertions.assertThat(actual).as(info).isEqualToIgnoringCase(expected);
    }

    public void printInfo(String info) {
        LOGGER.info(info);
    }


    public String getValueAttribute(WebElement element) {
        Validate.notNull(element, "Element should not be null");
        return element.getAttribute("value");
    }

    public List<String> getAllDropdownOptions(Select select) {
        List<String> options = new ArrayList<>();
        select.getOptions().forEach(option -> options.add(option.getText()));
        LOGGER.info("Options found:" + options.toString());
        return options;
    }

    public List<String> getAllDropdownOptions(WebElement selectElement) {
        Select select = new Select(selectElement);
        List<String> options = new ArrayList<>();
        select.getOptions().forEach(option -> options.add(option.getText()));
        LOGGER.info("Options found:" + options.toString());
        return options;
    }

    public void clickLinkByText(String link) {
        LOGGER.info("Clicking element with link:" + link);
        WebElement hyperlink = driver.findElement(By.linkText(link));
        hyperlink.click();
    }

    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept(); //Handle unexpected alert on page load.
            LOGGER.info("Accepting alert with text:" + alert.getText());
        } catch (Exception e) {
        }
    }

    public void switchFrame(WebElement frameName) {
        try {
            driver.switchTo().frame(frameName);
        } catch (Exception e) {
        }
    }

    public String getAlertText() {
        String alertText = null;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            LOGGER.info("Getting text from alert:" + alertText);
        } catch (Exception e) {
        }
        return alertText;
    }

    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            LOGGER.info("Dismissing alert with text:" + alert.getText());
            alert.dismiss(); //Handle unexpected alert on page load.
        } catch (Exception e) {
        }
    }


}
