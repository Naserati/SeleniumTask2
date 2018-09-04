package ru.aplana.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigation extends BasePage {

    public Navigation(WebDriver driver) {
        super(driver);
    }

    public void selectMenuItem(String menuItem) {
        clickField(By.xpath("//ul[@class='lg-menu__list']//span[text()='" + menuItem + "']"));
    }

    public void selectSubMenuItem(String subMenuItem) {
        clickField(By.xpath("//ul[@class='lg-menu__sub-list']//li[@class='lg-menu__sub-item']/a[text()='" + subMenuItem + "']"));
    }
}
