package doyenm.zooshell.testUtils;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.LifespanAttributes;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.SizeAttributes;
import doyenm.zooshell.model.SocialAttributes;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author doyenm
 */
public class GenerateZoo {

    public static Zoo generateZooWithNoPadAnimalOrKeeper() {
        return Zoo.builder()
                .age(RandomUtils.nextInt())
                .name(RandomStringUtils.random(10))
                .width(RandomUtils.nextInt())
                .height(RandomUtils.nextInt())
                .monthsPerEvaluation(RandomUtils.nextInt())
                .horizon(RandomUtils.nextInt())
                .grade(RandomUtils.nextDouble())
                .animals(new HashMap<>())
                .keepers(new HashMap<>())
                .paddocks(new HashMap<>())
                .build();
    }

    public static Animal generateAnimal() {
        return Animal.builder()
                .specie(new Specie())
                .name(RandomStringUtils.random(10))
                .sex(Sex.FEMALE)
                .paddock(new Paddock())
                .reproductionAttributes(generateReproductionAttributes())
                .lifespanAttributes(generateLifespanAttributes())
                .age(RandomUtils.nextInt())
                .numberOfChildren(RandomUtils.nextInt())
                .diets(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS))
                .optimalFoodAttributes(generateFoodAttributes())
                .currentFoodAttributes(generateFoodAttributes())
                .contraceptionMethod(ContraceptionMethod.CASTRACTION)
                .sizeAttributes(generateSizeAttributes())
                .socialAttributes(generateSocialAttributes())
                .characterAttributes(generateCharacterAttributes())
                .notNursingByMother(true)
                .wellBeing(RandomUtils.nextDouble())
                .build();
    }

    public static ReproductionAttributes generateReproductionAttributes() {
        return ReproductionAttributes.builder()
                .femaleMaturityAge(RandomUtils.nextInt())
                .maleMaturityAge(RandomUtils.nextInt())
                .frequency(RandomUtils.nextDouble())
                .gestationDuration(RandomUtils.nextInt())
                .litterSize(RandomUtils.nextInt())
                .weaningAge(RandomUtils.nextInt())
                .build();
    }

    public static LifespanAttributes generateLifespanAttributes() {
        return LifespanAttributes.builder()
                .femaleLifespan(RandomUtils.nextInt())
                .maleLifespan(RandomUtils.nextInt())
                .build();
    }

    public static FoodAttributes generateFoodAttributes() {
        return FoodAttributes.builder()
                .fastDays(RandomUtils.nextInt())
                .quantity(RandomUtils.nextDouble())
                .build();
    }

    public static SocialAttributes generateSocialAttributes() {
        return SocialAttributes.builder()
                .individualsPerGroup(RandomUtils.nextInt())
                .build();
    }

    public static SizeAttributes generateSizeAttributes() {
        return SizeAttributes.builder()
                .femaleWeight(RandomUtils.nextDouble())
                .maleWeight(RandomUtils.nextDouble())
                .build();
    }

    public static CharacterAttributes generateCharacterAttributes() {
        return CharacterAttributes.builder()
                .agressivity(RandomUtils.nextDouble())
                .bravery(RandomUtils.nextDouble())
                .curiosity(RandomUtils.nextDouble())
                .gourmandise(RandomUtils.nextDouble())
                .inteligence(RandomUtils.nextDouble())
                .meticulousness(RandomUtils.nextDouble())
                .cohabitationFactor(RandomUtils.nextDouble())
                .build();
    }

    public static Paddock generatePaddock() {
        return Paddock.builder()
                .name(RandomStringUtils.random(10))
                .coordinates(generateCoordinates())
                .entry(generatePosition())
                .biome(Biome.NONE)
                .type(PaddockType.PIT)
                .extensions(Arrays.asList(generateCoordinates()))
                .build();
    }

    public static Coordinates generateCoordinates() {
        return Coordinates.builder()
                .height(RandomUtils.nextInt())
                .width(RandomUtils.nextInt())
                .position(generatePosition())
                .build();
    }

    public static Position generatePosition() {
        return Position.builder()
                .y(RandomUtils.nextInt())
                .x(RandomUtils.nextInt())
                .build();
    }

    public static AnimalKeeper generateKeeper() {
        return AnimalKeeper.builder()
                .age(RandomUtils.nextInt())
                .name(RandomStringUtils.random(10))
                .familyEvaluations(new HashMap<>())
                .taskEvaluations(new HashMap<>())
                .occupations(new ArrayList<>())
                .build();
    }

}
