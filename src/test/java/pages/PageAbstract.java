package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import processor.Driver;

import java.net.MalformedURLException;

/**
 * Created by macbook on 14.10.16.
 */
public abstract class PageAbstract {

    public AppiumDriver getDriver() throws MalformedURLException, InterruptedException {
        return Driver.getDriver();
    }

}
