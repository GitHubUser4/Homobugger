package processor;


import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by s.popov on 25-10-16.
 */
public class ErrorProcessor extends Exception {
    private static final Logger log = Logger.getLogger(ErrorProcessor.class);
    private Driver driver;

    public ErrorProcessor() {
        super();
    }

    public void processError(Exception e) throws InterruptedException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log.error(sw.toString());
        driver = new Driver();
        this.driver.closeAppiumServer();
    }

}
