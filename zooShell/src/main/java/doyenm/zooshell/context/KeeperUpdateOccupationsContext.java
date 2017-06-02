package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.Zoo;
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
public class KeeperUpdateOccupationsContext {

    private final Zoo zoo;
    private final String keeper;
    private final String paddock;
    private final String task;
    private final String time;

    private AnimalKeeper convertedKeeper;
    private Paddock convertedPaddock;
    private TaskType convertedTask;
    private double convertedTime;

    public void convert() {
        this.convertedTime = Double.parseDouble(getTime());
    }
}
