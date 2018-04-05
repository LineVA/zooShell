package doyenm.zooshell.zoo;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doyenm
 */
@Getter
public class ZooContext {

    @Setter
    private Zoo zoo;
    private boolean detailed;
    private List<Couple> couples = new ArrayList<>();
    private String saveName;

    private List<BusinessError> errors = new ArrayList<>();

    public ZooContext(Zoo zoo, String saveName) {
        this.zoo = zoo;
        this.saveName = saveName;
        this.detailed = false;
    }

    public ZooContext(String saveName) {
        this.zoo = null;
        this.saveName = saveName;
        this.detailed = false;
    }

    public ZooContext(Zoo zoo, boolean detailed) {
        this.zoo = zoo;
        this.saveName = "";
        this.detailed = detailed;
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
