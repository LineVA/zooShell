package doyenm.zooshell.context;

import doyenm.zooshell.model.Grade;
import doyenm.zooshell.model.Specie;
import lombok.Getter;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.xml.specie.SpecieGenerator;
import java.util.ArrayList;
import java.util.Map;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class ZooCreationContext {

    private final SpecieGenerator specieGenerator = new SpecieGenerator();
    private final String speciePath = "src/main/resources/doyenm/zooshell/species";

    private final String INIT_WIDTH = "50";
    private final String INIT_HEIGHT = "50";
    private final String INIT_HORIZON = "5";
    private final String INIT_SPEED = "6";

    private final String name;
    private final String initWidth;
    private final String initHeight;
    private final String initHorizon;
    private final String initSpeed;
    private final Map<String, Specie> species;
    private final Zoo zoo = new Zoo();

    private int convertedWidth;
    private int convertedHeight;
    private int convertedHorizon;
    private int convertedSpeed;
    private final int age = 0;

    private boolean isValidation;

    public ZooCreationContext(String name) {
        this.name = name;
        this.initHeight = INIT_HEIGHT;
        this.initWidth = INIT_WIDTH;
        this.initHorizon = INIT_HORIZON;
        this.initSpeed = INIT_SPEED;
        this.species = specieGenerator.generateSpecie(this.speciePath);
    }

    public ZooCreationContext(String name, String width, String height) {
        this.name = name;
        this.initHeight = height;
        this.initWidth = width;
        this.initHorizon = INIT_HORIZON;
        this.initSpeed = INIT_SPEED;
        this.species = specieGenerator.generateSpecie(this.speciePath);
    }

    public ZooCreationContext(String name, String width, String height, String horizon, String speed) {
        this.name = name;
        this.initHeight = height;
        this.initWidth = width;
        this.initHorizon = horizon;
        this.initSpeed = speed;
        this.species = specieGenerator.generateSpecie(this.speciePath);
    }

    public void convert() {
        this.setConvertedWidth(Integer.parseInt(this.getInitWidth()));
        this.setConvertedHeight(Integer.parseInt(this.getInitHeight()));
        this.setConvertedHorizon(Integer.parseInt(this.getInitHorizon()));
        this.setConvertedSpeed(Integer.parseInt(this.getInitSpeed()));
    }

    public void build() {
        zoo.setWidth(getConvertedWidth());
        zoo.setHeight(this.getConvertedHeight());
        zoo.setName(this.getName());
        zoo.setHorizon(this.getConvertedHorizon());
        zoo.setMonthsPerEvaluation(this.getConvertedSpeed());
        zoo.setAge(this.getAge());
        zoo.setSpecies(this.getSpecies());
        zoo.setGradeObj(new Grade());
        zoo.setZooEvents(new ArrayList<>());
    }

}
