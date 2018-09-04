package ru.aplana.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsurancePage_2_Registration extends BasePage {
    public InsurancePage_2_Registration(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='insured0_surname']")
    WebElement insuredSurname;

    @FindBy(xpath = "//input[@name='insured0_name']")
    WebElement insuredName;

    @FindBy(xpath = "//input[@name='insured0_birthDate']")
    WebElement insuredBirthDate;

    @FindBy(xpath = "//input[@name='birthDate']")
    WebElement birthDate;

    @FindBy(xpath = "//input[@name='name']")
    WebElement name;

    @FindBy(xpath = "//input[@name='surname']")
    WebElement surname;

    @FindBy(xpath = "//input[@name='middlename']")
    WebElement middlename;

    @FindBy(xpath = "//input[@placeholder='Серия']")
    WebElement serie;

    @FindBy(xpath = "//input[@placeholder='Номер']")
    WebElement number;

    @FindBy(xpath = "//input[@name='issueDate']")
    WebElement issueDate;

    @FindBy(xpath = "//textarea[@placeholder='Кем выдан']")
    WebElement issuePlace;

    @FindBy(xpath = "//span[text()='Продолжить']")
    WebElement continueButton;


    public void fillInsuredFields(String insuredSurnameValue, String insuredNameValue, String insuredBirthDateValue){
        sendKeys(insuredSurname, insuredSurnameValue);
        sendKeys(insuredName, insuredNameValue);
        sendKeys(insuredBirthDate, insuredBirthDateValue);
    }

    public void fillInsurantFields(String birthDateValue, String nameValue, String surnameValue, String middlenameValue){
        sendKeys(birthDate, birthDateValue);
        birthDate.sendKeys(Keys.TAB);
        sendKeys(name, nameValue);
        sendKeys(surname, surnameValue);
        sendKeys(middlename, middlenameValue);
    }

    public void fillPassportFields(String serieValue, String numberValue, String issueDateValue, String issuePlaceValue){
        sendKeys(serie, serieValue);
        sendKeys(number, numberValue);
        sendKeys(issueDate, issueDateValue);
        sendKeys(issuePlace, issuePlaceValue);
    }

    public void checkInsuredFields(String expectedInsuredSurnameValue, String expectedInsuredNameValue, String expectedInsuredBirthDateValue){
        compareValue(getText(insuredSurname), expectedInsuredSurnameValue);
        compareValue(getText(insuredName), expectedInsuredNameValue);
        compareValue(getText(insuredBirthDate), expectedInsuredBirthDateValue);
    }

    public void checkInsurantFields(String expectedBirthDateValue, String expectedNameValue, String expectedSurnameValue, String expectedMiddlenameValue){
        compareValue(getText(birthDate), expectedBirthDateValue);
        compareValue(getText(name), expectedNameValue);
        compareValue(getText(surname), expectedSurnameValue);
        compareValue(getText(middlename), expectedMiddlenameValue);
    }

    public void checkPassportFields(String expectedSerieValue, String expectedNumberValue, String expectedIssueDateValue, String expectedIssuePlaceValue){
        compareValue(getText(serie), expectedSerieValue);
        compareValue(getText(number), expectedNumberValue);
        compareValue(getText(issueDate), expectedIssueDateValue);
        compareValue(getText(issuePlace), expectedIssuePlaceValue);
    }

    public void clickContinueButton(){
        clickField(continueButton);
    }

    public void checkErrorMessage() {
        waitFieldIsDisplayed(By.xpath("//div[text()='Заполнены не все обязательные поля']"));
    }
}