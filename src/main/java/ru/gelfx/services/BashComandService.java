package ru.gelfx.services;

import javafx.application.Platform;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by aer on 25.11.16.
 */
@Service
public class BashComandService {

    public static void runSafe(final Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable");
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        }
        else {
            Platform.runLater(runnable);
        }
    }

    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        ProcessBuilder bash;
        try {
            bash = new ProcessBuilder("/bin/bash", "-c", command);

            bash.redirectErrorStream();
            Process p  = bash.start();
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));
            p.waitFor();


            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

            String errLine = "";
            while ((errLine = stdError.readLine()) != null) {
                output.append(errLine + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return output.toString();

    }
}
