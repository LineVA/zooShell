package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class KeeperCreationContext {

    private final Zoo zoo;
    private final String keeper;
    
    public Map<String, AnimalKeeper> getKeepers(){
        return getZoo().getKeepers();
    }
    
     public Map<String, Paddock> getPaddocks(){
        return getZoo().getPaddocks();
    }

}
