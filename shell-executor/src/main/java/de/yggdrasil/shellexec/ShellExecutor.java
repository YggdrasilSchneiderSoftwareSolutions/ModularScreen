package de.yggdrasil.shellexec;

import de.yggdrasil.shellexec.model.ProcessOutput;

public interface ShellExecutor {
    ProcessOutput runCommand(String cmd);
    void runCommandAsync(String cmd);

    default boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("win");
    }
}
