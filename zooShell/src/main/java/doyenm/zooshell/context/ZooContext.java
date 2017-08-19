package doyenm.zooshell.context;

import doyenm.zooshell.commandLine.utils.Couple;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
public class ZooContext {

    @Setter
    private Zoo zoo;
    private List<Couple> couples = new ArrayList<>();
    private String saveName;

    public ZooContext(Zoo zoo, String saveName) {
        this.zoo = zoo;
        this.saveName = saveName;
    }

    public ZooContext(String saveName) {
        this.zoo = null;
        this.saveName = saveName;
    }

    public ZooContext(Zoo zoo) {
        this.zoo = zoo;
        this.saveName = "";
    }

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
