package processor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
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
    static String platformName;
    static String platformVersion;
    static String avdName;
    static String pathToApp = "/Users/administrator/Downloads/app-dev-debug.apk";
    private static AppiumDriver driver;
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
        command = "appium -a 127.0.0.1";

        /*command = "appium -a 127.0.0.1 --automation-name Appium --platform-name " + platformName +
                " --platform-version " + platformVersion + " --app " + pathToApp + " --avd " + avdName +
                " --device-name " + avdName + " --full-reset";
*/        commandLineExecutor.executeCommand(command, "Appium REST http interface listener started");
    }

    public static AppiumDriver getDriver() throws MalformedURLException, InterruptedException {

        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("language", "en");
            if (platformName.equals("Android")) {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            } else if (platformName.equals("iOS")) {
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            }
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



