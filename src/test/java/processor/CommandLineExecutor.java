package processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by macbook on 14.10.16.
 */
public class CommandLineExecutor {

    public void executeCommand(String command, String startMessage) {
        Process process;
        try {
            process = Runtime.getRuntime().exec("appium -a 127.0.0.1 --automation-name Appium --platform-name Android --platform-version 5.0.1 --app /Users/macbook/Downloads/app-dev-debug.apk --avd Android_5.x --device-name Android_5.x --full-reset");
            //String[] strings = new String[] {"/bin/bash", "-c", command};
            //process = new ProcessBuilder("appium", "-a 127.0.0.1","-p 4725").start();
            if (!startMessage.equals("")) {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (!reader.readLine().contains(startMessage)) {
                }
            }

            } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
