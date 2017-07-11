package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.model.Zoo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */   
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReturnExec {
    private final String message;
    private final TypeReturn typeReturn;
    private Zoo zoo;
}