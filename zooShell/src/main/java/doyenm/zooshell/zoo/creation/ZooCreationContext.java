package doyenm.zooshell.zoo.creation;

import doyenm.zooshell.errorhandling.Errors;
import doyenm.zooshell.model.Grade;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.specie.xml.SpecieGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class ZooCreationContext {

    private final SpecieGenerator specieGenerator = new SpecieGenerator();
    private static final String SPECIE_PATH = "src/main/resources/doyenm/zooshell/species";

    private static final String INIT_WIDTH = "50";
    private static final String INIT_HEIGHT = "50";
    private static final String INIT_HORIZON = "5";
    private static final String INIT_SPEED = "6";

    private Errors errors = new Errors();

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
    private static final int AGE = 0;

    private boolean isValidation;

    public ZooCreationContext(String name) {
       this(name, INIT_WIDTH, INIT_HEIGHT, INIT_HORIZON,INIT_SPEED);
    }

    public ZooCreationContext(String name, String width, String height) {
        this(name, width, height, INIT_HORIZON, INIT_SPEED);
    }

    public ZooCreationContext(String name, String width, String height, String horizon, String speed) {
        this.name = name;
        this.initHeight = height;
        this.initWidth = width;
        this.initHorizon = horizon;
        this.initSpeed = speed;
        this.species = specieGenerator.generateSpecie(SPECIE_PATH);
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
        zoo.setAge(AGE);
        zoo.setSpecies(this.getSpecies());
        zoo.setGradeObj(new Grade());
        zoo.setZooEvents(new ArrayList<>());
    }

}
