package processor;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by s.popov on 14.08.2015.
 */
public class DataCreator {

    private static final Logger log = Logger.getLogger(DataCreator.class);
    static ErrorProcessor errorProcessor = new ErrorProcessor();
    private static String name;

    public static String generateUserName() {
        log.debug("Generate username");
        name = getRandomString();
        return name + " " + getRandomString();
    }

    private static String getRandomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = (int) (5 + (Math.random() * (5)));
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            if (i == 0) c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String generateUserEmail() throws InterruptedException {
        log.debug("Generate email");
        try {
            return name.toLowerCase() + "@mailinator.com";
        } catch (Exception e) {
            errorProcessor.processError(e);
            return null;
        }
    }

    public static String generateUserPhone() {
        log.debug("Generate phone");
        return Long.toString((long) (1000000000 + (Math.random() * (8999999999l))));
    }
}
