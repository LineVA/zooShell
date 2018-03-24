package doyenm.zooshell.commandline.general;

import doyenm.zooshell.model.Zoo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author doyenm
 */   
@Getter
@AllArgsConstructor
public class ReturnExec {
    private String message;
    private final TypeReturn typeReturn;
    private Zoo zoo;

    public ReturnExec(String message, TypeReturn typeReturn) {
        this.message = message;
        this.typeReturn = typeReturn;
    }
    
    public void concat(String msg2){
         this.message += " \n" + msg2;
    }
}