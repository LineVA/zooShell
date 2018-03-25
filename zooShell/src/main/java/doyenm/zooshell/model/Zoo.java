package doyenm.zooshell.model;

import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventType;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Zoo {
    /**
     * The name of the zoo
     */
    @XmlElement
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
    /**
     * The hashmap of the animal keepers it contains
     */
    private Map<String, AnimalKeeper> keepers = new HashMap<>();
    /**
     * The hashmap of the handymen it contains
     */
    private Map<String, Handyman> handymen = new HashMap<>();
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
    
    /**
     * The object that save all the details of the grade of the zoo
     */
    private Grade gradeObj;
    
    /**
     * The horizon of the zoo
     */
    private int horizon;
    
    /**
     * The list of the zoo that currently affects it
     */
    private List<ZooEvent> zooEvents;

    public List<ZooEvent> getAvailableKeeperTrainings(){
        return getZooEvents()
                .stream()
                .filter(e -> ZooEventType.KEEPER_TRAINING == e.getEventType())
                .collect(Collectors.toList());
    }
    
    private List<Penalty> penalties = new ArrayList<>();
    
}
