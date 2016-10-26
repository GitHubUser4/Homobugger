package pages;

/**
 * Created by administrator on 24.10.16.
 */
public interface IMainPage {
    public void clickOpenAccount(boolean isReal) throws InterruptedException;

    public void fillRegData(String userName, String userEmail) throws InterruptedException;

    public void changePassword(String password, String passwordConformation) throws InterruptedException;

    public void loginUser(String userName, String userPassword) throws InterruptedException;
}
