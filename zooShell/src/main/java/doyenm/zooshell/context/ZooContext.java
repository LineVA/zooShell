package doyenm.zooshell.context;

import doyenm.zooshell.commandLine.utils.Couple;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class ZooContext {

    private final Zoo zoo;
    private List<Couple> couples = new ArrayList<>();
    
    public void addCouple(String key, String value){
        couples.add(new Couple(key, value));
    }
    
     public void addCouple(String key, int value){
        couples.add(new Couple(key, String.valueOf(value)));
    }
}
