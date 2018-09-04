package ru.aplana.autotests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.autotests.hooks.Hooks;
import java.util.Set;

public class BasePage {

    static WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void compareValue(String currentValue, String expectedValue) {
        if (!currentValue.equals(expectedValue)) {
            Assert.fail("Ошибка сравнения; текущее значение - " + currentValue + ", ожидаемое значение - " + expectedValue);
        }
    }

    public void waitFieldIsDisplayed(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator).isDisplayed());
            return;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.fail("Поле не отображено");
    }

    public void waitFieldIsDisplayed(WebElement element) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.fail("Поле не отображено");
    }

    public boolean isElementPresent(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clickField(By locator) {
        if (!isElementPresent(locator)) {
            Assert.fail("Невозможно нажать на поле");
        }
        waitFieldIsDisplayed(locator);
        driver.findElement(locator).click();
    }

    public void clickField(WebElement element) {
        if (!isElementPresent(element)) {
            Assert.fail("Невозможно нажать на поле");
        }
        element.click();
    }

    public void clickFieldAndChangeWindow(WebElement element) {
        Hooks.setOldWindowHandle(driver.getWindowHandle());
        Set<String> winSetOld = driver.getWindowHandles();
        clickField(element);
        changeWindow(winSetOld);
    }

    public void changeWindow(Set<String> winset) {
        //Переключение на новое окно(вкладку)
        String newWindow = wait.until(thereIsWindowOtherThan(winset));
        driver.switchTo().window(newWindow);
    }


    public void sendKeys(By locator, String value) {
        if (isElementPresent(locator)) {
            waitFieldIsDisplayed(locator);
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value);
        }
        else Assert.fail("Невозможно ввести значение в поле");
    }

    public void sendKeys(WebElement element, String value) {
        if (isElementPresent(element)) {
            waitFieldIsDisplayed(element);
            element.click();
            element.clear();
            element.sendKeys(value);
        }
        else Assert.fail("Невозможно ввести значение в поле");
    }

    public String getText(WebElement element){
        return element.getAttribute("value");
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> winSet) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> curWinSet = driver.getWindowHandles();
                curWinSet.removeAll(winSet);
                return curWinSet.size() > 0 ? curWinSet.iterator().next() : null;
            }
        };
    }


}
