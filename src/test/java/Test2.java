import org.junit.Test;
import ru.aplana.autotests.hooks.Hooks;


public class Test2 extends Hooks {


    @Test
    public void test1(){
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

        navigation.selectMenuItem("Страхование");
        navigation.selectSubMenuItem("Путешествия и покупки");

        travelPage.clickIssueOnlineButton();

        insurancePageChoice.chooseMinimalPackage();
        insurancePageChoice.clickCheckoutButton();

        insurancePageRegistration.fillInsuredFields(insuredSurname, insuredName, insuredBirthDate);
        insurancePageRegistration.fillInsurantFields(birthDate, name, surname, middlename);
        insurancePageRegistration.fillPassportFields(serie, number, issueDate, issuePlace);
        insurancePageRegistration.checkInsuredFields(insuredSurname, insuredName, insuredBirthDate);
        insurancePageRegistration.checkInsurantFields(birthDate, name, surname, middlename);
        insurancePageRegistration.checkPassportFields(serie, number, issueDate, issuePlace);
        insurancePageRegistration.clickContinueButton();
        insurancePageRegistration.checkErrorMessage();

    }

}

