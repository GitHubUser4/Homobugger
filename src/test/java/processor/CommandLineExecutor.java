package processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by macbook on 14.10.16.
 */
public class CommandLineExecutor {
    String value;

    public void executeCommand(String command, String startMessage) {
        Process process;
        try {
            System.out.println(command);
            process = Runtime.getRuntime().exec(command);
            //String[] strings = new String[] {"/bin/bash", "-c", command};
            //process = new ProcessBuilder("appium", "-a 127.0.0.1","-p 4725").start();
            if (!startMessage.equals("")) {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(process.getInputStream()));
                value = reader.readLine();
                while (!value.contains(startMessage)) {
                    System.out.println(value);
                    value = reader.readLine();
                }
            }

            } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
