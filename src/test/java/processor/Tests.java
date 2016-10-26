package processor;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.IMainPage;

import java.net.MalformedURLException;

/**
 * Created by Popov S. on 14.10.16.
 */
public class Tests {
    String userName;
    private ErrorProcessor errorProcessor = new ErrorProcessor();
    private Boolean isRealAccount;


    @Test(description = "регистрация нового счета")
    @Parameters({"is_real"})
    public void regAccount(String is_real) throws InterruptedException {
        try {
            isRealAccount = Boolean.getBoolean(is_real);
            IMainPage page = (IMainPage) this.getPage("MainPage");
            page.clickOpenAccount(isRealAccount);
            page.fillRegData(DataCreator.generateUserName(), DataCreator.generateUserEmail());
            page.changePassword("Qwerty123", "Qwerty123");
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Test(description = "вход в кабинет")
    @Parameters({"user_name"})
    public void loginUser(String user_name) {

    }


    private Object getPage(String name) throws InterruptedException {
        String type;
        try {
            if (Driver.getDriver() instanceof AndroidDriver) {
                type = "Android";
            } else {
                type = "IOS";
            }
            Object obj = Class.forName("pages." + type + "." + name).newInstance();

            return obj;
        } catch (Exception e) {
            errorProcessor.processError(e);
            return null;
        }
    }
}
