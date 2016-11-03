package pages.Android;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import pages.IMainPage;
import pages.PageAbstract;
import processor.ErrorProcessor;

/**
 * Created by Popov S. on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {
    private static final Logger log = Logger.getLogger(MainPage.class);
    private ErrorProcessor errorProcessor = new ErrorProcessor();

    public void clickOpenAccount(boolean isReal) throws InterruptedException {
        log.info("Open account button click");
        try {
            if (isReal) this.getDriver().findElement(By.id("start_open_real_button")).click();
            else
                this.getDriver().findElement(By.id("start_open_demo_button")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }

    public void fillRegData(String userName, String userEmail) throws InterruptedException {
        log.info("Fill registration data (username - " + userName + ", useremail - " + userEmail + ")");
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

    public void changePassword(String password, String passwordConformation) throws InterruptedException {
        log.info("Change registration password to - " + passwordConformation);
        try {
        this.getDriver().findElement(By.id("new_password_edit_text")).sendKeys(password);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("new_password_confirm_edit_text")).sendKeys(passwordConformation);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("change_pass_next_button")).click();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }

    }

    public void loginUser(String userName, String userPassword) throws InterruptedException {
        log.info("Try to login user with name - " + userName + ", password - " + userPassword);
        try {
            this.getDriver().findElement(By.id("start_sign_in_button")).click();
            this.getDriver().findElement(By.id("login_email_edit_text")).sendKeys(userName);
            this.getDriver().hideKeyboard();
            this.getDriver().findElement(By.id("login_pass_edit_text")).sendKeys(userPassword);
            this.getDriver().hideKeyboard();
            this.getDriver().findElement(By.id("login_next_button")).click();

        } catch (Exception e) {
            errorProcessor.processError(e);
        }

    }




}
