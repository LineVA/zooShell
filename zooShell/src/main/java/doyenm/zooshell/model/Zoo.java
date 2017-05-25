package doyenm.zooshell.model;

import java.util.Map;
import doyenm.zooshell.launch.options.Option;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class Zoo {
    private Option option;

    /**
     * The name of the zoo
     */
    private String name;
    /**
     * Its width
     */
    private int width;
    /**
     * Its height
     */
    private int height;
    /**
     * The hashmap of the paddocks it contains
     */
    private Map<String, Paddock> paddocks = new HashMap<>();
       /**
     * The hashmap of the animals it contains
     */
    private Map<String, Animal> animals = new HashMap<>();
//    /**
//     * The hashmap of the animal keepers it contains
//     */
//    private Map<String, AnimalKeeper> keepers;
    /**
     * The HashMap of the existing species
     */
    private Map<String, Specie> species;
    /**
     * The number of months which flows when we evaluate the zoo
     */
    private int monthsPerEvaluation;
    /**
     * The age of the zoo
     */
    private int age;
    /**
     * The grade of the zoo Does not have to be save, can be re-calculate each
     * time we need it
     */
    private double grade;
    private int horizon;
}
