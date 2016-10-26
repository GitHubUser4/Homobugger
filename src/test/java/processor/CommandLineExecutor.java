package processor;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Popov S. on 14.10.16.
 */
public class CommandLineExecutor {
    private static final Logger log = Logger.getLogger(CommandLineExecutor.class);
    private String commandLineString;
    private ErrorProcessor errorProcessor = new ErrorProcessor();

    void executeCommand(String command, String startMessage) throws InterruptedException {
        log.info("Run Appium server - " + command);
        try {
            Process process;
            process = Runtime.getRuntime().exec(command);
            if (!startMessage.equals("")) {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(process.getInputStream()));
                commandLineString = reader.readLine();
                while (!commandLineString.contains(startMessage)) {
                    log.debug("Terminal: " + commandLineString);
                    commandLineString = reader.readLine();
                }
            }
        } catch (Exception e) {
            errorProcessor.processError(e);
        }

    }
}
