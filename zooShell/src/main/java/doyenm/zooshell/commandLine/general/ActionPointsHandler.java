package doyenm.zooshell.commandLine.general;

import lombok.NoArgsConstructor;

/**
 *
 * @author doyenm
 */
@NoArgsConstructor
public class ActionPointsHandler {
    
    private int actionPointsNumber;
    
    public void initialize(){
       this.actionPointsNumber = 7;
    }
    
    public boolean hasEnoughPoints(int desired){
        return this.actionPointsNumber >= desired;
    }
    
    public void update(int consumed){
        this.actionPointsNumber -= consumed;
    }

}
