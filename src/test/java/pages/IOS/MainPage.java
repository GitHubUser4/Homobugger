package pages.IOS;

import org.openqa.selenium.By;
import pages.IMainPage;
import pages.PageAbstract;
import processor.ErrorProcessor;

/**
 * Created by macbook on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {

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
            this.getDriver().findElement(By.id("registration_name_edit_text")).sendKeys(userName);
            this.getDriver().hideKeyboard();
            this.getDriver().findElement(By.id("registration_email_edit_text")).sendKeys(userEmail);
            this.getDriver().hideKeyboard();
            this.getDriver().findElement(By.id("registration_agreement_checkbox")).click();
            this.getDriver().findElement(By.id("registration_next_button")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    @Override
    public void changePassword(String password, String passwordConformation) throws InterruptedException {

    }

    @Override
    public void loginUser(String userName, String userPassword) throws InterruptedException {

    }


}
