package processor;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.IMainPage;
import pages.ITradePage;

/**
 * Created by Popov S. on 14.10.16.
 */
public class Tests {
    private static final Logger log = Logger.getLogger(Tests.class);
    private String userName;
    private ErrorProcessor errorProcessor = new ErrorProcessor();
    private Boolean isRealAccount;
    private IMainPage mainPage;
    private ITradePage tradePage;
    public Tests() throws InterruptedException {

    }

    @BeforeTest
    private void createPages() throws InterruptedException {
        mainPage = (IMainPage) this.getPage("MainPage");
        tradePage = (ITradePage) this.getPage("TradePage");
    }

    @Test(description = "регистрация нового счета")
    @Parameters({"is_real"})
    public void regAccount(String is_real) throws InterruptedException {
        log.info("Run registration test, account type is_real - " + is_real);
            isRealAccount = Boolean.getBoolean(is_real);
            this.mainPage.clickOpenAccount(isRealAccount);
            this.mainPage.fillRegData(DataCreator.generateUserName(), DataCreator.generateUserEmail());
            this.mainPage.changePassword("123Qwerty", "123Qwerty");
            this.tradePage.verifyElementsOnPage();

    }

    @Test(description = "вход пользователя в кабинет")
    @Parameters({"user_name"})
    public void loginUser(String user_name) throws InterruptedException {
        log.info("Run login test for user - " + user_name);
            userName = user_name;
            this.mainPage.loginUser(userName, "Qwerty123");
            this.tradePage.verifyElementsOnPage();
        }


    public Object getPage(String name) throws InterruptedException {
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
