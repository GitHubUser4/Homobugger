package pages.IOS;

import org.openqa.selenium.By;
import pages.IMainPage;
import pages.PageAbstract;
import processor.ErrorProcessor;


/**
 * Created by macbook on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {

    private final String XPATHMAINPAGE = "//UIAApplication[1]/UIAWindow[1]/";
    private ErrorProcessor errorProcessor = new ErrorProcessor();

    public void clickOpenAccount(boolean isReal) throws InterruptedException {

        try {
            if (isReal) this.getDriver().findElement(By.id("Open Real account")).click();
            else
                this.getDriver().findElement(By.id("Demo account with $1,000")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    public void fillRegData(String userName, String userEmail) throws InterruptedException {
        try {
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIATextField[1]")).sendKeys(userName);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIATextField[2]")).sendKeys(userEmail);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[6]")).click();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[7]")).click();
            Thread.sleep(20000);
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Override
    public void changePassword(String password, String passwordConformation) throws InterruptedException {
        try {
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[1]")).sendKeys(password);
            hideIosKeyboard();
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIASecureTextField[2]")).sendKeys(passwordConformation);
            hideIosKeyboard();
            Thread.sleep(5000);
            this.getDriver().findElement(By.xpath(XPATHMAINPAGE + "UIAButton[2]")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Override
    public void loginUser(String userName, String userPassword) throws InterruptedException {
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
