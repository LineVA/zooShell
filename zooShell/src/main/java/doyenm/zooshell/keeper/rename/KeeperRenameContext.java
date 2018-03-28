package doyenm.zooshell.keeper.rename;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

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