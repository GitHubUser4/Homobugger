package processor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
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
    private static final Logger log = Logger.getLogger(Driver.class);
    private static final ErrorProcessor errorProcessor = new ErrorProcessor();
    private static boolean debugMode = false;
    private static String platformName;
    private static String platformVersion;
    private static String avdName;
    private static String pathToApp = "/Users/administrator/Downloads/hotoption.app";
    //static String pathToApp = "/Users/administrator/Downloads/app-dev-debug.apk";
    private static AppiumDriver driver;
    private String command;
    private CommandLineExecutor commandLineExecutor = new CommandLineExecutor();

    public static AppiumDriver getDriver() throws InterruptedException {

        if (driver == null) {
            log.info("Try to create driver for - " + platformName);
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("language", "en");
                if (platformName.equals("Android")) {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

                } else if (platformName.equals("iOS")) {
                    capabilities.setCapability("platformVersion", platformVersion);
                    capabilities.setCapability("platformName", platformName);
                    capabilities.setCapability("app", pathToApp);
                    capabilities.setCapability("udid", "");
                    capabilities.setCapability("deviceName", avdName);
                    capabilities.setCapability("fullReset", "true");
                    capabilities.setCapability("newCommandTimeout", 7200);
                    capabilities.setCapability("automationName", "Appium");
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                }
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            } catch (Exception e) {
                errorProcessor.processError(e);
            }
        }
        return driver;
    }


    @BeforeSuite //перед каждыйм набором тестов
    @Parameters({"platform_name", "platform_version", "avd_name"})
    public void runAppiumServer(String platform_name, String platform_version, String avd_name) throws InterruptedException {
        log.info("----------------------------------------------------");
        log.info("New test suite started");
        log.info("Create Appium server startup string");
        platformName = platform_name;
        platformVersion = platform_version;
        avdName = avd_name;
        if (platformName.equals("Android"))
            command = "appium -a 127.0.0.1 -p 4723 --automation-name Appium --platform-name " + platformName +
                    " --platform-version " + platformVersion + " --app " + pathToApp +
                    " --avd " + avdName + " --device-name \"" + avdName + "\" --full-reset";
        else
            command = "appium -a 127.0.0.1 -p 4723";
        if (debugMode) command = command + " --log /tmp/appium_new.log --log-level debug";
        this.commandLineExecutor.executeCommand(command, "Appium REST http interface listener started");
    }

    @AfterSuite
    public void closeAppiumServer() throws InterruptedException {
        log.info("Stop appium server");
        command = "killall node";
        this.commandLineExecutor.executeCommand(command, "");
        if (platformName.equals("Android")) command = "adb emu kill"; else
        command = "killall Simulator";
        this.commandLineExecutor.executeCommand(command, "");
        log.info("***************************************************");
    }


    @BeforeTest //перед каждым тестом в Suite
    public void beforeTest() {}

    @AfterTest //после каждого теста в Suite
    public void afterTest() {}

}



