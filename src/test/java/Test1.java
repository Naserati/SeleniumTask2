import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Test1 {

    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @Test
    public void test1() throws InterruptedException {
        String insuredSurname = "Testov";
        String insuredName = "Test";
        String insuredBirthDate = "10.12.2000";
        String name = "Орнамент";
        String surname = "Транквила";
        String middlename = "Шалаповна";
        String birthDate = "20.08.2000";
        String serie = "6502";
        String number = "687898";
        String issueDate = "20.01.2009";
        String issuePlace = "ОУФМС Москвы";
        waitFieldisDisplayed(By.xpath("//span[text()='Страхование']"));
        //Страхование
        click(By.xpath("//span[text()='Страхование']"));
        //Путешествия и покупки
        click(By.xpath("//li[@class='lg-menu__sub-item']/a[text()='Путешествия и покупки']"));
        //Страхование путешественников
        waitFieldisDisplayed(By.xpath("//h3[text()='Страхование путешественников']"));
        //Запоминаем текущий handle окна
        String mainWinHandle = driver.getWindowHandle();
        //Запоминаем handle всех открытых в текущий момент окно(вкладок)
        Set<String> winSet = driver.getWindowHandles();
        //Оформить онлайн
        click(By.xpath("//div[div[contains(@class, 'bp-widget ') and .//h3[text()='Страхование путешественников']]]/following-sibling::div//a[text()='Оформить онлайн']"));
        //Переключение на новое окно(вкладку)
        String newWindow = wait.until(thereIsWindowOtherThan(winSet));
        driver.switchTo().window(newWindow);
        waitFieldisDisplayed(By.xpath("//span[text()='Выбор полиса']"));
        //Выбор минимального пакета
        click(By.xpath("//div[text()='Минимальная']"));
        if (!driver.findElement(By.xpath("//div[text()='Минимальная']/parent::*[1]")).getAttribute("class").equals("b-form-prog-box b-form-active-box")) Assert.fail("Не выбран пакет \"Минимальный\"");
        //Оформить
        click(By.xpath("//span[text()='Оформить']"));
        //Заполняем застрахованных
        sendKeys(By.xpath("//input[@name='insured0_surname']"), insuredSurname);
        sendKeys(By.xpath("//input[@name='insured0_name']"), insuredName);
        sendKeys(By.xpath("//input[@name='insured0_birthDate']"), insuredBirthDate);
        //Заполняем страхователя
        sendKeys(By.xpath("//input[@name='birthDate']"), birthDate);
        sendKeys(By.xpath("//input[@name='surname']"), name);
        sendKeys(By.xpath("//input[@name='name']"), surname);
        sendKeys(By.xpath("//input[@name='middlename']"), middlename);
        click(By.xpath("//input[@name='female']/parent::span[1]"));
        //Заполняем паспортные данные
        sendKeys(By.xpath("//input[@placeholder='Серия']"), serie);
        sendKeys(By.xpath("//input[@placeholder='Номер']"), number);
        sendKeys(By.xpath("//input[@name='issueDate']"), issueDate);
        sendKeys(By.xpath("//textarea[@placeholder='Кем выдан']"), issuePlace);
        //Проверяем данные
        compareValue(driver.findElement(By.xpath("//input[@name='insured0_surname']")).getAttribute("value"), insuredSurname);
        compareValue(driver.findElement(By.xpath("//input[@name='insured0_name']")).getAttribute("value"), insuredName);
        compareValue(driver.findElement(By.xpath("//input[@name='insured0_birthDate']")).getAttribute("value"), insuredBirthDate);
        compareValue(driver.findElement(By.xpath("//input[@name='surname']")).getAttribute("value"), name);
        compareValue(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), surname);
        compareValue(driver.findElement(By.xpath("//input[@name='middlename']")).getAttribute("value"), middlename);
        compareValue(driver.findElement(By.xpath("//input[@name='birthDate']")).getAttribute("value"), birthDate);
        compareValue(driver.findElement(By.xpath("//input[@placeholder='Серия']")).getAttribute("value"), serie);
        compareValue(driver.findElement(By.xpath("//input[@placeholder='Номер']")).getAttribute("value"), number);
        compareValue(driver.findElement(By.xpath("//input[@name='issueDate']")).getAttribute("value"), issueDate);
        compareValue(driver.findElement(By.xpath("//textarea[@placeholder='Кем выдан']")).getAttribute("value"), issuePlace);
        //Продолжить
        click(By.xpath("//span[text()='Продолжить']"));
        waitFieldisDisplayed(By.xpath("//div[text()='Заполнены не все обязательные поля']"));
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }

    public void compareValue(String currentValue, String expectedValue) {
        if (!currentValue.equals(expectedValue)) {
            Assert.fail("Ошибка сравнения");
        }
    }

    public void waitFieldisDisplayed(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator).isDisplayed());
            return;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.fail("Поле не отображено");
    }

    public boolean isElementPresented(By locator) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void click(By locator) {
        if (!isElementPresented(locator)) {
        }
        waitFieldisDisplayed(locator);
        driver.findElement(locator).click();
    }

    public void sendKeys(By locator, String value) {
        if (!isElementPresented(locator)) {
        }
        waitFieldisDisplayed(locator);
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(value);
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

