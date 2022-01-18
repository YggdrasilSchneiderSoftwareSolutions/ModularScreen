package de.yggdrasil.shellexec.impl;

import de.yggdrasil.shellexec.ExecutorType;
import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.ShellExecutorType;
import de.yggdrasil.shellexec.model.ProcessOutput;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@ShellExecutorType(ExecutorType.DEFAULT)
public class ShellCommandExecutor implements ShellExecutor {


    @Override
    public ProcessOutput runCommand(String cmd) {
        int returnCode = -1;
        List<String> outputLines = new ArrayList<>();
        ProcessOutput processOutput = new ProcessOutput();

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("sh", "-c", cmd);
        }

        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            // Ausgabe lesen
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    outputLines.add(line);
                }
            }
            returnCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        processOutput.setReturnCode(returnCode);
        processOutput.setOutputs(outputLines);
        return processOutput;
    }

    @Override
    public void runCommandAsync(String cmd) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("sh", "-c", cmd);
        }

        processBuilder.redirectErrorStream(true);

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
