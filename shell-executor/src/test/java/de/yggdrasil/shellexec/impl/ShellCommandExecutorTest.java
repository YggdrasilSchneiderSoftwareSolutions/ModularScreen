package de.yggdrasil.shellexec.impl;

import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.model.ProcessOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ShellCommandExecutorTest {

    static ShellExecutor shellExecutor;

    @BeforeAll
    public static void setUp() {
        shellExecutor = new ShellCommandExecutor();
    }

    @Test
    public void testRunSyncListDir() {
        ProcessOutput output = shellExecutor.runCommand("ls -l");
        assert output.getReturnCode() == 0;
        assert output.getOutputs().size() > 0;
    }

    @Test
    public void testRunAsyncListDir() {
        Exception exception = null;
        try {
            shellExecutor.runCommandAsync("ls -l");
        } catch (Exception e) {
            exception = e;
        }
        assert exception == null;
    }
}
