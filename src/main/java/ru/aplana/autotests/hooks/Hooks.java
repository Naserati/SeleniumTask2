package ru.aplana.autotests.hooks;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.autotests.pages.InsurancePage_1_Choice;
import ru.aplana.autotests.pages.InsurancePage_2_Registration;
import ru.aplana.autotests.pages.Navigation;
import ru.aplana.autotests.pages.TravelPage;
import ru.aplana.autotests.util.TestProperties;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {


    protected Navigation navigation;
    protected TravelPage travelPage;
    protected InsurancePage_1_Choice insurancePageChoice;
    protected InsurancePage_2_Registration insurancePageRegistration;

    protected  WebDriver driver;
    protected String baseURL;
    public Properties properties = TestProperties.getInstance().getProperties();

    private static String oldWindowHandle;

    public WebDriver getDriver() {
        return driver;
    }

    public Hooks() {
        PageFactory.initElements(getDriver(), this);
    }

    @Before
    public void startScenario() {
        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        baseURL = properties.getProperty("app.url");
        System.out.println(baseURL);

        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("implicityWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("pageLoad")), TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get(baseURL + "/");

        oldWindowHandle = driver.getWindowHandle();

        navigation = new Navigation(driver);
        travelPage = new TravelPage(driver);
        insurancePageChoice = new InsurancePage_1_Choice(driver);
        insurancePageRegistration = new InsurancePage_2_Registration(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public static String getOldWindowHandle() {
        return oldWindowHandle;
    }

    public static void setOldWindowHandle(String currentWindowHandle) {
      oldWindowHandle = currentWindowHandle;
    }
}
