package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Zoo;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperTrainingContext {

    private final Zoo zoo;
    private String name;
    private String familyName;
    
    private AnimalKeeper convertedKeeper;
    private Family convertedFamily;
    private boolean canAccessTraining;
}
