package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class KeeperRenameContext {
    private final Zoo zoo;
    private final String keeper;
    private final String newKeeperName;
    
    private AnimalKeeper convertedKeeper;
    
    public Map<String, AnimalKeeper> getKeepers(){
        return getZoo().getKeepers();
    }
}