package processor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MainPage;

import java.net.MalformedURLException;

/**
 * Created by macbook on 14.10.16.
 */
public class Tests {
    String userName;
    Boolean isRealAccount;

    @Test(description = "регистрация демо счета")
    @Parameters({"is_real"})
    public void regDemo(String is_real) throws MalformedURLException, InterruptedException {
        isRealAccount = Boolean.getBoolean(is_real);
        MainPage mainPage = new MainPage();
        mainPage.clickOpenAccount(isRealAccount);
    }

    @Test(description = "вход в кабинет")
    public void regRa() {

    }
}
