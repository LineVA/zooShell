package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

}
