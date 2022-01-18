package de.yggdrasil.shellexec.impl;

import de.yggdrasil.shellexec.ExecutorType;
import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.ShellExecutorType;
import de.yggdrasil.shellexec.model.ProcessOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@ShellExecutorType(ExecutorType.PYTHON)
public class PythonExecutor implements ShellExecutor {

    @Value("${shell-executor.python-executor.python-command}")
    private String pythonCommand;

    @Override
    public ProcessOutput runCommand(String cmd) {
        String pythonScriptCommand = pythonCommand + " " + cmd;

        int returnCode = -1;
        List<String> outputLines = new ArrayList<>();
        ProcessOutput processOutput = new ProcessOutput();

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows()) {
            processBuilder.command("cmd.exe", "/c", pythonScriptCommand);
        } else {
            processBuilder.command("sh", "-c", pythonScriptCommand);
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
        String pythonScriptCommand = pythonCommand + " " + cmd;

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows()) {
            processBuilder.command("cmd.exe", "/c", pythonScriptCommand);
        } else {
            processBuilder.command("sh", "-c", pythonScriptCommand);
        }

        processBuilder.redirectErrorStream(true);

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
