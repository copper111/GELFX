package ru.gelfx.services;

import javafx.application.Platform;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by aer on 25.11.16.
 */
@Service
public class BashComandService {

    public void openTerminal() throws IOException {
        String command= "/usr/bin/xterm";
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(command);
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
