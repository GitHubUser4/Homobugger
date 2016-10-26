package pages.Android;

import org.openqa.selenium.By;
import pages.ITradePage;
import pages.PageAbstract;
import processor.ErrorProcessor;

/**
 * Created by administrator on 26.10.16.
 */
public class TradePage extends PageAbstract implements ITradePage {
    private ErrorProcessor errorProcessor = new ErrorProcessor();

    @Override
    public void verifyElementsOnPage() throws InterruptedException {
        try {
            this.getDriver().findElement(By.id("bar_create_real_account_button")).isDisplayed();
            this.getDriver().findElement(By.id("bar_balance_text_view")).isDisplayed();
            this.getDriver().findElement(By.id("bar_menu_button")).isDisplayed();
            this.getDriver().findElement(By.id("main_history_link_image_view")).isDisplayed();
            this.getDriver().findElement(By.id("bet_button_put")).isDisplayed();
            this.getDriver().findElement(By.id("bet_button_call")).isDisplayed();
            this.getDriver().findElement(By.id("main_filled_circle_view")).isDisplayed();
            this.getDriver().findElement(By.id("bet_amount_selected_value_text_view")).isDisplayed();
        } catch (Exception e) {
            errorProcessor.processError(e);
        }
    }
}
