package doyenm.zooshell.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Animal {
    private Specie specie;
    private String name;
    private Sex sex;
    private Paddock paddock;
    private ReproductionAttributes reproductionAttributes;
    private LifespanAttributes lifespanAttributes;
    private int age;
    private int numberOfChildren;
    private List<Diet> diets;
    private FoodAttributes optimalFoodAttributes;
    private FoodAttributes currentFoodAttributes;
    private int daysOfDrowning = 0;
    private int daysOfHunger = 0;
    private ContraceptionMethod contraceptionMethod;
    private int monthsOfGestation;
    private SocialAttributes socialAttributes;
    private TerritoryAttributes territoryAttributes;
    private SizeAttributes sizeAttributes;
    private CharacterAttributes characterAttributes;
    private boolean notNursingByMother;
    private double wellBeing;
}
