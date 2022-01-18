package de.yggdrasil.shellexec.model;

import lombok.Data;

import java.util.List;

@Data
public class ProcessOutput {
     private int returnCode;
     private List<String> outputs;
}
