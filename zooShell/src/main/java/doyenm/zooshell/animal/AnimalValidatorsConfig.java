package doyenm.zooshell.animal;

import doyenm.zooshell.animal.contraception.AnimalUpdateContraceptionValidator;
import doyenm.zooshell.animal.creation.AnimalCreationValidator;
import doyenm.zooshell.animal.diets.AnimalUpdateDietValidator;
import doyenm.zooshell.animal.diets.AnimalUpdateFastDaysValidator;
import doyenm.zooshell.animal.diets.AnimalUpdateFoodQuantityValidator;
import doyenm.zooshell.animal.list.AnimalsWithCriteria;
import doyenm.zooshell.animal.move.AnimalChangePaddockValidator;
import doyenm.zooshell.animal.rename.AnimalChangeNameValidator;
import doyenm.zooshell.common.CommonConfigs;
import doyenm.zooshell.common.FindAnimal;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.animal.list.criteria.*;
import doyenm.zooshell.common.function.*;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

/**
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
@Import({CommonConfigs.class})
public class AnimalValidatorsConfig {

    @Autowired
    Environment environment;

    @Autowired
    FindAnimal findAnimal;

    @Autowired
    FindingAnimalWithEntryCheckFunction findingAnimalWithEntryCheckFunction;

    @Autowired
    FindingDietFunction findingDietFunction;

    @Autowired
    FindingSexFunction findingSexFunction;

    @Autowired
    FindingSpecieFunction findingSpecieFunction;

    @Autowired
    FindPaddock findPaddock;

    @Autowired
    DoubleValuesPredicates doubleValuesPredicates;

    @Autowired
    IntegerValuePredicates integerValuePredicates;

    @Autowired
    NameValidator animalNameValidator;

    @Autowired
    FindingContraceptionFunction findingContraceptionFunction;

    @Autowired
    CanHaveAChirurgicalContraceptionPredicate canHaveAChirurgicalContraceptionPredicate;

    @Autowired
    CanHaveAHormonalContraceptionPredicate canHaveAHormonalContraceptionPredicate;

    @Autowired
    IsContraceptionCompatibleWithPreviousPredicate isContraceptionCompatibleWithPreviousPredicate;

    @Autowired
    IsContraceptionCompatibleWithSexPredicate isContraceptionCompatibleWithSexPredicate;

    // Validator
    @Bean
    public AnimalChangeNameValidator animalChangeNameValidator() {
        return new AnimalChangeNameValidator(findAnimal,
                animalNameValidator
        );
    }

    @Bean
    public AnimalChangePaddockValidator animalChangePaddockValidator() {
        return new AnimalChangePaddockValidator(findAnimal, findPaddock);
    }

    @Bean
    public AnimalCreationValidator animalCreationValidator() {
        return new AnimalCreationValidator(findPaddock,
                environment.getProperty("paddock.obsolescence.max_number_of_turn_when_unusable", Integer.class),
                Integer.parseInt(environment.getProperty("animal.name.max_length")));
    }

    @Bean
    public AnimalUpdateContraceptionValidator animalUpdateContraceptionValidator() {
        return new AnimalUpdateContraceptionValidator(findingContraceptionFunction,
                findAnimal,
                canHaveAHormonalContraceptionPredicate,
                canHaveAChirurgicalContraceptionPredicate,
                isContraceptionCompatibleWithPreviousPredicate,
                isContraceptionCompatibleWithSexPredicate
        );
    }

    @Bean
    public AnimalUpdateDietValidator animalUpdateDietValidator() {
        return new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
    }

    @Bean
    public AnimalUpdateFastDaysValidator animalUpdateFastDaysValidator() {
        return new AnimalUpdateFastDaysValidator(findAnimal, integerValuePredicates);
    }

    @Bean
    public AnimalUpdateFoodQuantityValidator animalUpdateFoodQuantityValidator() {
        return new AnimalUpdateFoodQuantityValidator(doubleValuesPredicates, findAnimal);
    }

    @Bean
    public AnimalValidator animalValidator() {
        return new AnimalValidator();
    }

    @Bean
    public LsWithCriteriaParser lsWithCriteriaParser() {
        return new LsWithCriteriaParser();
    }

    private final List<String> excluded = Arrays.asList("AND", "OR", "NOT", "(", ")", ",");

    @Bean
    public AnimalsListWithDietCriteriaValidator animalsListWithDietCriteriaValidator() {
        return new AnimalsListWithDietCriteriaValidator(excluded, lsWithCriteriaParser(), findingDietFunction);
    }

    @Bean
    public AnimalsListWithSexCriteriaValidator animalsListWithSexCriteriaValidator() {
        return new AnimalsListWithSexCriteriaValidator(excluded, lsWithCriteriaParser(), findingSexFunction);
    }

    @Bean
    public AnimalsListWithPaddockCriteriaValidator animalsListWithPaddockCriteriaValidator() {
        return new AnimalsListWithPaddockCriteriaValidator(excluded, lsWithCriteriaParser(), findPaddock);
    }

    @Bean
    public AnimalsListWithSpecieCriteriaValidator animalsListWithSpecieCriteriaValidator() {
        return new AnimalsListWithSpecieCriteriaValidator(excluded, lsWithCriteriaParser(), findingSpecieFunction);
    }

    @Bean
    public AnimalsListWithContraceptionCriteriaValidator animalsListWithContraceptionCriteriaValidator() {
        return new AnimalsListWithContraceptionCriteriaValidator(excluded, lsWithCriteriaParser(), findingContraceptionFunction);
    }

    @Bean
    public AnimalsWithCriteria animalsWithCriteria() {
        return new AnimalsWithCriteria(
                animalsListWithDietCriteriaValidator(),
                animalsListWithSexCriteriaValidator(),
                animalsListWithPaddockCriteriaValidator(),
                animalsListWithSpecieCriteriaValidator(),
                animalsListWithContraceptionCriteriaValidator()
        );
    }
}
