package doyenm.zooshell.context;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.model.Specie;
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
public class SpecieDetailsContext {

    private final Zoo zoo;
    private final String specieName;
    private Specie specie;
    
    private List<Couple> couples = new ArrayList<>();

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, int value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }
}
