package processor;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by macbook on 14.10.16.
 */
public class Driver {
    String platformName;
    String platformVersion;
    String avdName;
    String pathToApp = "/Users/macbook/Downloads/app-dev-debug.apk";
    private static AndroidDriver driver;
    String command;
    CommandLineExecutor commandLineExecutor = new CommandLineExecutor();

    @BeforeSuite //перед каждыйм набором тестов
    @Parameters({"platform_name", "platform_version", "avd_name"})
    public void runAppiumServer(String platform_name, String platform_version, String avd_name) {
        //log.info("----------------------------------------------------");
        //log.info("Запускается новый набор тестов");
        //log.info("Устанавиваем параметры подключения к серверу Appium");
        platformName = platform_name;
        platformVersion = platform_version;
        avdName = avd_name;
        command = "appium -a 127.0.0.1 -p 4725 --automation-name Appium --platform-name " + platformName +
                " --platform-version " + platformVersion + " --app " + pathToApp + " --avd " + avdName +
                " --device-name " + avdName + " --full-reset --orientation LANDSCAPE";
        commandLineExecutor.executeCommand(command, "Appium REST http interface listener started");
    }

    public static AndroidDriver getDriver() throws MalformedURLException, InterruptedException {
        if(driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("language", "en");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            //driver.rotate(ScreenOrientation.LANDSCAPE);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        return driver;
    }



    @AfterSuite
    public void closeAppiumServer() {
        command = "killall node";
        commandLineExecutor.executeCommand(command, "");
    }


    @BeforeTest //перед каждым тестом в Suite
    public void beforeTest() {}

    @AfterTest //после каждого теста в Suite
    public void afterTest() {}

}



