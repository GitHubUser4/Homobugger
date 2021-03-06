package pages.IOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import pages.IMainPage;
import pages.PageAbstract;
import processor.ErrorProcessor;


/**
 * Created by macbook on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {
    private static final Logger log = Logger.getLogger(MainPage.class);
    private final String XPATHMAINPAGE = "//UIAApplication[1]/UIAWindow[1]/";
    private ErrorProcessor errorProcessor = new ErrorProcessor();

    public void clickOpenAccount(boolean isReal) throws InterruptedException {
        log.info("Open account button click");
        try {
            if (isReal) this.getDriver().findElement(By.id("Open Real account")).click();
            else
                this.getDriver().findElement(By.id("Demo account with $1,000")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    public void fillRegData(String userName, String userEmail) throws InterruptedException {
        log.info("Fill registration data (username - " + userName + ", useremail - " + userEmail + ")");
        try {
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIATextField[1]")).sendKeys(userName);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIATextField[2]")).sendKeys(userEmail);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[6]")).click();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[7]")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Override
    public void changePassword(String password, String passwordConformation) throws InterruptedException {
        log.info("Change registration password to - " + passwordConformation);
        try {
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[1]")).sendKeys(password);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[2]")).sendKeys(passwordConformation);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[1]")).click();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[2]")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Override
    public void loginUser(String userName, String userPassword) throws InterruptedException {
        log.info("Try to login user with name - " + userName + ", password - " + userPassword);
        try {
            this.getDriver().findElement(By.xpath("//UIAApplication[1]/UIAWindow[3]")).click();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIATextField[1]")).sendKeys(userName);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[1]")).sendKeys(userPassword);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[7]")).click();

        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    private void hideIosKeyboard() throws InterruptedException {
        try {
            this.getDriver().findElement(By.xpath("//*[contains(@name, 'Hide keyboard')]")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }


    }
}
