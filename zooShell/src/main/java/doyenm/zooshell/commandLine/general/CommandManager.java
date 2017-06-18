package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.model.Zoo;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CommandManager {

    private final List<CommandBis> commands;
    
    private Zoo zoo;
    
    public ReturnExec run(String cmd){
        String[] cmdArray = SplitDoubleQuotes.split(cmd);
        for(CommandBis command : commands){
            if(command.canExecute(cmdArray)){
                return save(command.execute(cmdArray, zoo));
            }
        }
        return new ReturnExec("UNKNOWN CMD", TypeReturn.ERROR, null);
    }
    
    public String getFirstLine(){
        return "Welcome";
    }
    
    private ReturnExec save(ReturnExec returnExec){
        if(TypeReturn.SUCCESS == returnExec.getTypeReturn() && returnExec.getZoo() != null){
            this.zoo = returnExec.getZoo();
        }
        return returnExec;
    }
}
