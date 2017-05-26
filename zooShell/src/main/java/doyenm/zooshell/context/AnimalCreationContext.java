package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.LifespanAttributes;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.SocialAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.TerritoryAttributes;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.model.utils.FoodAttributesConstructor;
import doyenm.zooshell.model.utils.LifespanAttributesConstructor;
import doyenm.zooshell.model.utils.ReproductionAttributesConstructor;
import doyenm.zooshell.model.utils.SocialAttributesConstructor;
import doyenm.zooshell.model.utils.TerritoryAttributesConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public void convert() {
    }

    public void build() {
        ReproductionAttributes reproductionAttributes = reproductionAttributesConstructor.build(specie);
        int age = (getSex() == Sex.FEMALE) ? reproductionAttributes.getFemaleMaturityAge() : reproductionAttributes.getMaleMaturityAge();
        LifespanAttributes lifespanAttributes = lifespanAttributesConstructor.build(specie);
        FoodAttributes optimalFoodAttributes = foodAttributesConstructor.build(specie);
        SocialAttributes socialAttributes = socialAttributesConstructor.build(specie);
        TerritoryAttributes territoryAttributes = territoryAttributesConstructor.build(specie);
        FoodAttributes currentFoodAttributes = new FoodAttributes(0.0, 0);
        List<Diet> diets = new ArrayList<>();
        diets.add(Diet.NONE);
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
                .age(age)
                .build();
        this.getZoo().getAnimals().put(this.getName(), animal);
    }

    public Animal createNewborn() {
        ReproductionAttributes reproductionAttributes = reproductionAttributesConstructor.build(specie);
        LifespanAttributes lifespanAttributes = lifespanAttributesConstructor.build(specie);
        FoodAttributes optimalFoodAttributes = foodAttributesConstructor.build(specie);
        FoodAttributes currentFoodAttributes = new FoodAttributes(0.0, 0);
        SocialAttributes socialAttributes = socialAttributesConstructor.build(specie);
        TerritoryAttributes territoryAttributes = territoryAttributesConstructor.build(specie);
        List<Diet> diets = new ArrayList<>();
        diets.add(Diet.NONE);
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
                .age(0)
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
