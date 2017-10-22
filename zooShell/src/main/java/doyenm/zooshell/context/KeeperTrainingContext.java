package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class KeeperTrainingContext {

    private final Zoo zoo;
    private String name;
    private String familyName;
    
    @Setter
    private AnimalKeeper convertedKeeper;
    @Setter
    private Family convertedFamily;
    private boolean canAccessTraining;
}
