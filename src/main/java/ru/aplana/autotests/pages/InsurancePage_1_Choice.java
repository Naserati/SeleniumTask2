package ru.aplana.autotests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsurancePage_1_Choice extends BasePage {
    public InsurancePage_1_Choice(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='Минимальная']")
    WebElement minimalPackage;

    @FindBy(xpath = "//span[text()='Оформить']")
    WebElement сheckoutButton;

    public void chooseMinimalPackage(){
        clickField(minimalPackage);
        if (!driver.findElement(By.xpath("//div[text()='Минимальная']/parent::*[1]")).getAttribute("class").equals("b-form-prog-box b-form-active-box"))
            Assert.fail("Не выбран пакет \"Минимальный\"");
    }

    public void clickCheckoutButton(){
        clickField(сheckoutButton);
    }



}