package pages.IOS;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import pages.ITradePage;
import pages.PageAbstract;
import processor.ErrorProcessor;

/**
 * Created by administrator on 01.11.16.
 */
public class TradePage extends PageAbstract implements ITradePage {
    private static final Logger log = Logger.getLogger(pages.Android.TradePage.class);
    private final String XPATHTRADEPAGE = "//UIAApplication[1]/UIAWindow[1]/";
    ErrorProcessor errorProcessor = new ErrorProcessor();

    public void verifyElementsOnPage() throws InterruptedException {
        log.info("Try to verify elements on trade page");
        try {
            this.getDriver().findElement(By.id("menu button")).isDisplayed();
            this.getDriver().findElement(By.id("DEPOSIT")).isDisplayed();
            this.getDriver().findElement(By.xpath(XPATHTRADEPAGE + "UIAStaticText[2]")).isDisplayed();
            this.getDriver().findElement(By.xpath(XPATHTRADEPAGE + "UIAImage[3]")).isDisplayed();
            this.getDriver().findElement(By.id("button call")).isDisplayed();
            this.getDriver().findElement(By.id("button put")).isDisplayed();
            this.getDriver().findElement(By.xpath(XPATHTRADEPAGE + "UIAButton[6]")).isDisplayed();
            this.getDriver().findElement(By.xpath(XPATHTRADEPAGE + "UIAButton[8]")).isDisplayed();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }
}
