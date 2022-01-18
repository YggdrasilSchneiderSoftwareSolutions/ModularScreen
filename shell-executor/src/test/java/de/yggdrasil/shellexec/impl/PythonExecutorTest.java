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
        ReflectionTestUtils.setField(pythonExecutor, "pythonCommand", "python",
                String.class);
    }

    @Test
    public void testRunPythonScript() {
        String scriptPath = resolveScriptPath("test.py");
        ProcessOutput output = pythonExecutor.runCommand(scriptPath);
        assert output.getReturnCode() == 0;
        assert output.getOutputs().size() == 1;
    }

    @Test
    public void testRunAsyncScript() {
        String scriptPath = resolveScriptPath("test.py");
        pythonExecutor.runCommandAsync(scriptPath);
    }

    private String resolveScriptPath(String scriptName) {
        return Paths.get("").toAbsolutePath()
                + "/src/test/resources/"
                + scriptName;
    }
}
