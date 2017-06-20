package doyenm.zooshell.context;

import doyenm.zooshell.commandLine.utils.Couple;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
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
public class KeeperContext {

    private final Zoo zoo;
    private final String keeper;
    private List<Couple> couples = new ArrayList<>();

    private AnimalKeeper convertedKeeper;

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, double value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }
}
