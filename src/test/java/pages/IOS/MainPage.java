package pages.IOS;

import org.openqa.selenium.By;
import pages.IMainPage;
import pages.PageAbstract;

import java.net.MalformedURLException;

/**
 * Created by macbook on 14.10.16.
 */
public class MainPage extends PageAbstract implements IMainPage {

    public void clickOpenAccount(boolean isReal) throws MalformedURLException, InterruptedException {
        //if (isReal) this.getDriver().findElement(By.id("Open Real account")).click(); else
        //this.getDriver().findElement(By.id("Demo account with $1,000")).click();
        Thread.sleep(10000);
    }

    public void fillRegData(String userName, String userEmail) throws MalformedURLException, InterruptedException {
        this.getDriver().findElement(By.id("registration_name_edit_text")).sendKeys(userName);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("registration_email_edit_text")).sendKeys(userEmail);
        this.getDriver().hideKeyboard();
        this.getDriver().findElement(By.id("registration_agreement_checkbox")).click();
        this.getDriver().findElement(By.id("registration_next_button")).click();
    }

    @Override
    public void changePassword(String password, String passwordConformation) throws MalformedURLException, InterruptedException {

    }


}
