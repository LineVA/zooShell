package doyenm.zooshell.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@Builder
public class Animal {
    private final Specie specie;
    private String name;
    private final Sex sex;
    private Paddock paddock;
    private final ReproductionAttributes reproductionAttributes;
    private final LifespanAttributes lifespanAttributes;
    private int age;
    private int numberOfChildren;
    private List<Diet> diets;
    private final FoodAttributes optimalFoodAttributes;
    private FoodAttributes currentFoodAttributes;
    private int daysOfDrowning = 0;
    private int daysOfHunger = 0;
    private ContraceptionMethod contraceptionMethod;
    private int monthsOfGestation;
    private final SocialAttributes socialAttributes;
    private final TerritoryAttributes territoryAttributes;
    private final SizeAttributes sizeAttributes;
    private final CharacterAttributes characterAttributes;
    private boolean needWeaningByHumans;
}
