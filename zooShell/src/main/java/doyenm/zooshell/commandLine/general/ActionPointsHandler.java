package doyenm.zooshell.commandLine.general;

import lombok.NoArgsConstructor;

/**
 *
 * @author doyenm
 */
@NoArgsConstructor
public class ActionPointsHandler {
    
    private int actionPointsNumber;
    
    public void initialize(int init){
       this.actionPointsNumber = init;
    }
    
    public boolean hasEnoughPoints(int desired){
        return this.actionPointsNumber >= desired;
    }
    
    public void update(int consumed){
        this.actionPointsNumber -= consumed;
    }
    
    public String updateMessage(){
        return "Remaining action points : " + this.actionPointsNumber;
    }

}
