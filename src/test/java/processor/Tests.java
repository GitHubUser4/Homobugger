package processor;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.IMainPage;

import java.net.MalformedURLException;

/**
 * Created by macbook on 14.10.16.
 */
public class Tests {

    String userName;
    Boolean isRealAccount;


    @Test(description = "регистрация демо счета")
    @Parameters({"is_real"})
    public void regAccount(String is_real) throws MalformedURLException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        isRealAccount = Boolean.getBoolean(is_real);
        IMainPage page = (IMainPage) this.getPage("MainPage");
        page.clickOpenAccount(isRealAccount);
        page.fillRegData("Vasya", "pupko@mailinator.com");
        page.changePassword("Qwerty123", "Qwerty123");
    }

    @Test(description = "вход в кабинет")
    public void regRa() {

    }


    private Object getPage(String name) throws MalformedURLException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String type;
        if (Driver.getDriver() instanceof AndroidDriver) {
            type = "Android";
        } else {
            type = "IOS";
        }

        Object obj = Class.forName("pages." + type + "." + name).newInstance();

        return obj;
    }
}
