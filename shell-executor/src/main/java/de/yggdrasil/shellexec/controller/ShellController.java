package de.yggdrasil.shellexec.controller;

import de.yggdrasil.shellexec.ExecutorType;
import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.ShellExecutorType;
import de.yggdrasil.shellexec.model.ProcessInput;
import de.yggdrasil.shellexec.model.ProcessOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exec")
public class ShellController {

    @Autowired
    @ShellExecutorType(ExecutorType.PYTHON)
    private ShellExecutor pythonShellExec;

    @Autowired
    @ShellExecutorType(ExecutorType.DEFAULT)
    private ShellExecutor shellExecutor;

    @PostMapping(value = "/python/async", consumes = "application/json")
    public void runPythonScript(@RequestBody ProcessInput processInput) {
        pythonShellExec.runCommandAsync(processInput.getCommand());
    }

    @PostMapping(value = "/command/async", consumes = "application/json")
    public void runCommand(@RequestBody ProcessInput processInput) {
        shellExecutor.runCommandAsync(processInput.getCommand());
    }

    @PostMapping(value = "/python", consumes = "application/json",
            produces = "application/json")
    public ProcessOutput runPythonScriptAsync(@RequestBody ProcessInput processInput) {
        return pythonShellExec.runCommand(processInput.getCommand());
    }

    @PostMapping(value = "/command", consumes = "application/json",
            produces = "application/json")
    public ProcessOutput runCommandAsync(@RequestBody ProcessInput processInput) {
        return shellExecutor.runCommand(processInput.getCommand());
    }
}
