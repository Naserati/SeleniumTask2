package ru.aplana.autotests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelPage extends BasePage{

    public TravelPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//div[div[contains(@class, 'bp-widget ') and .//h3[text()='Страхование путешественников']]]/following-sibling::div//a[text()='Оформить онлайн']")
    WebElement checkoutOnline;

    public void clickIssueOnlineButton(){
        clickFieldAndChangeWindow(checkoutOnline);
    }
}
