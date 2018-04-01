package doyenm.zooshell.animal.creation;

import doyenm.zooshell.model.*;
import doyenm.zooshell.model.utils.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AnimalCreationContext {

    private final Zoo zoo;
    private final String name;
    private final String specieName;
    private final String sexName;
    private final String paddockName;

    private Specie specie;
    private Paddock paddock;
    private Sex sex;

    private final FoodAttributesConstructor foodAttributesConstructor = new FoodAttributesConstructor();
    private final LifespanAttributesConstructor lifespanAttributesConstructor = new LifespanAttributesConstructor();
    private final ReproductionAttributesConstructor reproductionAttributesConstructor = new ReproductionAttributesConstructor();
    private final SocialAttributesConstructor socialAttributesConstructor = new SocialAttributesConstructor();
    private final TerritoryAttributesConstructor territoryAttributesConstructor = new TerritoryAttributesConstructor();
    private final SizeAttributesConstructor sizeAttributesConstructor = new SizeAttributesConstructor();
    private final CharacterAttributesConstructor characterConstructor = new CharacterAttributesConstructor();

    public void build() {
        ReproductionAttributes reproductionAttributes = reproductionAttributesConstructor.build(specie);
        int age = (getSex() == Sex.FEMALE) ? reproductionAttributes.getFemaleMaturityAge() : reproductionAttributes.getMaleMaturityAge();
        LifespanAttributes lifespanAttributes = lifespanAttributesConstructor.build(specie);
        FoodAttributes optimalFoodAttributes = foodAttributesConstructor.build(specie);
        SocialAttributes socialAttributes = socialAttributesConstructor.build(specie);
        TerritoryAttributes territoryAttributes = territoryAttributesConstructor.build(specie);
        FoodAttributes currentFoodAttributes = new FoodAttributes(0.0, 0);
        SizeAttributes sizeAttributes = sizeAttributesConstructor.build(specie);
        List<Diet> diets = new ArrayList<>();
        diets.add(Diet.NONE);
        CharacterAttributes characterAttributes = characterConstructor.build(specie, sizeAttributes, sex);
        Animal animal = Animal.builder()
                .name(this.getName())
                .specie(this.getSpecie())
                .paddock(this.getPaddock())
                .sex(this.getSex())
                .reproductionAttributes(reproductionAttributes)
                .lifespanAttributes(lifespanAttributes)
                .socialAttributes(socialAttributes)
                .diets(diets)
                .currentFoodAttributes(currentFoodAttributes)
                .optimalFoodAttributes(optimalFoodAttributes)
                .territoryAttributes(territoryAttributes)
                .contraceptionMethod(ContraceptionMethod.NONE)
                .monthsOfGestation(0)
                .sizeAttributes(sizeAttributes)
                .age(age)
                .characterAttributes(characterAttributes)
                .notNursingByMother(false)
                .wellBeing(0.0)
                .wellBeingObj(new WellBeing())
                .build();
        this.getZoo().getAnimals().put(this.getName().toUpperCase(), animal);
    }

    public Animal createNewborn() {
        ReproductionAttributes reproductionAttributes = reproductionAttributesConstructor.build(specie);
        LifespanAttributes lifespanAttributes = lifespanAttributesConstructor.build(specie);
        FoodAttributes optimalFoodAttributes = foodAttributesConstructor.build(specie);
        FoodAttributes currentFoodAttributes = new FoodAttributes(0.0, 0);
        SocialAttributes socialAttributes = socialAttributesConstructor.build(specie);
        TerritoryAttributes territoryAttributes = territoryAttributesConstructor.build(specie);
        SizeAttributes sizeAttributes = sizeAttributesConstructor.build(specie);
        List<Diet> diets = new ArrayList<>();
        diets.add(Diet.NONE);
        CharacterAttributes characterAttributes = characterConstructor.build(specie, sizeAttributes, sex);
        return Animal.builder()
                .name(this.getName())
                .specie(this.getSpecie())
                .paddock(this.getPaddock())
                .sex(this.getSex())
                .reproductionAttributes(reproductionAttributes)
                .lifespanAttributes(lifespanAttributes)
                .socialAttributes(socialAttributes)
                .territoryAttributes(territoryAttributes)
                .diets(diets)
                .currentFoodAttributes(currentFoodAttributes)
                .optimalFoodAttributes(optimalFoodAttributes)
                .contraceptionMethod(ContraceptionMethod.NONE)
                .monthsOfGestation(0)
                .sizeAttributes(sizeAttributes)
                .characterAttributes(characterAttributes)
                .age(0)
                .notNursingByMother(false)
                .wellBeing(0.0)
                .wellBeingObj(new WellBeing())
                .build();
    }

    public List<Animal> getAnimalsList() {
        return new ArrayList<>(this.getZoo().getAnimals().values());
    }

    public Map<String, Animal> getAnimals() {
        return getZoo().getAnimals();
    }

    public Map<String, Specie> getSpecies() {
        return getZoo().getSpecies();
    }

    public Map<String, Paddock> getPaddocks() {
        return getZoo().getPaddocks();
    }
}
