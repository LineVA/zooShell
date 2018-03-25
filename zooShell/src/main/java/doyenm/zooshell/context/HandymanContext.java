package doyenm.zooshell.context;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.model.Handyman;
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
@RequiredArgsConstructor
@Getter
@Setter
public class HandymanContext {

    private final Zoo zoo;
    private final String handymanName;
    private Handyman handyman;

    private List<Couple> couples = new ArrayList<>();

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, int value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }

    public void addCouple(String key, double value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }

}
