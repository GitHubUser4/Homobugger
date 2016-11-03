package processor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
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
    private static String pathToApp;
    private static AppiumDriver driver;
    private static boolean simulatorAlive = false;
    private static boolean appiumServerAlive = false;
    private String command;
    private CommandLineExecutor commandLineExecutor = new CommandLineExecutor();

    @BeforeTest(groups = {"myGroup"}) //перед каждым тестом в Suite
    public static AppiumDriver getDriver() throws InterruptedException, MalformedURLException {
        if (driver == null) {
            log.info("***************************************************");
            log.info("Try to create driver for - " + platformName);
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
                    capabilities.setCapability("autoAcceptAlerts", "true");
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                }
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            simulatorAlive = true;
        }
        return driver;
    }

    @BeforeSuite //перед каждыйм набором тестов
    @Parameters({"platform_name", "platform_version", "avd_name", "path_to_app"})
    public void runAppiumServer(String platform_name, String platform_version, String avd_name, String path_to_app) throws InterruptedException {
        if (!appiumServerAlive) {
            log.info("----------------------------------------------------");
            log.info("New test suite started");
            log.info("Create Appium server startup string");
            platformName = platform_name;
            platformVersion = platform_version;
            avdName = avd_name;
            pathToApp = path_to_app;
            if (platformName.equals("Android"))
                command = "appium -a 127.0.0.1 -p 4723 --automation-name Appium --platform-name " + platformName +
                        " --platform-version " + platformVersion + " --app " + pathToApp +
                        " --avd " + avdName + " --device-name \"" + avdName + "\" --full-reset";
            else
                command = "appium -a 127.0.0.1 -p 4723";
            if (debugMode) command = command + " --log /tmp/appium_new.log --log-level debug";
            this.commandLineExecutor.executeCommand(command, "Appium REST http interface listener started");
            appiumServerAlive = true;
        }
    }

    @AfterSuite
    public void closeAppiumServer() throws InterruptedException {
        if (appiumServerAlive) {
            log.info("Stop appium server");
            command = "killall node";
            this.commandLineExecutor.executeCommand(command, "");
            this.driver = null;
            appiumServerAlive = false;
            log.info("----------------------------------------------------");
            Thread.sleep(10000);
        }
    }

    @BeforeTest(groups = {"myGroup"})
    private void clearOldData() throws InterruptedException {
        closeSimulator();
    }

    @AfterTest(groups = {"myGroup"}) //после каждого теста в Suite
    public void closeSimulator() throws InterruptedException {
        if (simulatorAlive) {
            log.info("Turning off simulator for - " + platformName);
            if (platformName.equals("Android")) command = "adb emu kill";
            else {
                driver.quit();
                command = "killall -9 Simulator";
            }
            this.commandLineExecutor.executeCommand(command, "");
            simulatorAlive = false;
            this.driver = null;
            log.info("***************************************************");
            Thread.sleep(10000);
        }
    }

}



