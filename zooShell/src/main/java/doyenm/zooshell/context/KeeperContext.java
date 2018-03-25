package doyenm.zooshell.context;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private final boolean detailed;
    private List<Couple> couples = new ArrayList<>();

    private AnimalKeeper convertedKeeper;

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, double value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }
    
    public void addCouple(String key, int value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }

    public void addCouplesList(List<String> keys, List<Double> values) {
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                couples.add(new Couple("    " + keys.get(i), String.valueOf(values.get(i))));
            }
        }
    }
}
