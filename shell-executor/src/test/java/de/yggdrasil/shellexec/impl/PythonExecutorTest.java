package de.yggdrasil.shellexec.impl;

import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.model.ProcessOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Paths;

public class PythonExecutorTest {

    static ShellExecutor pythonExecutor;

    @BeforeAll
    public static void setUp() {
        pythonExecutor = new PythonExecutor();
        String scriptPath = Paths.get("").toAbsolutePath().toString()
                + "/src/test/resources/";
        ReflectionTestUtils.setField(pythonExecutor, "pythonCommand", "python",
                String.class);
        ReflectionTestUtils.setField(pythonExecutor, "scriptDirectory", scriptPath,
                String.class);
    }

    @Test
    public void testRunPythonScript() {
        ProcessOutput output = pythonExecutor.runCommand("test.py");
        assert output.getReturnCode() == 0;
        assert output.getOutputs().size() == 1;
    }

    @Test
    public void testRunAsyncScript() {
        pythonExecutor.runCommandAsync("test.py");
    }
}
