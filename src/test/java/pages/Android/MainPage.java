package pages.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.IMainPage;
import pages.PageAbstract;

import java.net.MalformedURLException;

/**
 * Created by macbook on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {

    public void clickOpenAccount(boolean isReal) throws MalformedURLException, InterruptedException {
        if (isReal) this.getDriver().findElement(By.id("start_open_real_button")).click(); else
        this.getDriver().findElement(By.id("start_open_demo_button")).click();
    }

    public void fillRegData(String userName, String userEmail) throws MalformedURLException, InterruptedException {
        this.getDriver().findElement(By.id("registration_name_edit_text")).sendKeys(userName);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("registration_email_edit_text")).sendKeys(userEmail);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("registration_agreement_checkbox")).click();
        this.getDriver().findElement(By.id("registration_next_button")).click();
    }

    public void changePassword(String password, String passwordConformation) throws MalformedURLException, InterruptedException {
        this.getDriver().findElement(By.id("new_password_edit_text")).sendKeys(password);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("new_password_confirm_edit_text")).sendKeys(passwordConformation);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("change_pass_next_button")).click();

    }



}
