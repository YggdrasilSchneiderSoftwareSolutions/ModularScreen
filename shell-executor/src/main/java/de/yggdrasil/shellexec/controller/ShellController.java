package de.yggdrasil.shellexec.controller;

import de.yggdrasil.shellexec.ExecutorType;
import de.yggdrasil.shellexec.ShellExecutor;
import de.yggdrasil.shellexec.ShellExecutorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShellController {

    @Autowired
    @ShellExecutorType(ExecutorType.PYTHON)
    private ShellExecutor python3ShellExec;

    @Autowired
    @ShellExecutorType(ExecutorType.DEFAULT)
    private ShellExecutor shellExecutor;

    // TODO
}
