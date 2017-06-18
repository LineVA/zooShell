package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ReturnExec {
    @Getter
    private final String message;
    @Getter
    private final TypeReturn typeReturn;
    @Getter
    private Zoo zoo;

    public ReturnExec(String message, TypeReturn typeReturn, Zoo zoo) {
        this.message = message;
        this.typeReturn = typeReturn;
        this.zoo = zoo;
    }
}