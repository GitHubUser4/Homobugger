package pages;

import java.net.MalformedURLException;

/**
 * Created by administrator on 24.10.16.
 */
public interface IMainPage {
    public void clickOpenAccount(boolean isReal) throws MalformedURLException, InterruptedException;
    public void fillRegData(String userName, String userEmail)throws MalformedURLException, InterruptedException;
    public void changePassword(String password, String passwordConformation) throws MalformedURLException, InterruptedException;
}
