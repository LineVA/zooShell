package doyenm.zooshell.validator;

import doyenm.zooshell.validator.criteria.AnimalsListWithDietCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithPaddockCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSexCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSpecieCriteriaValidator;
import doyenm.zooshell.validator.criteria.LsWithCriteriaParser;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.function.FindingContraceptionFunction;
import doyenm.zooshell.validator.function.FindingDietFunction;
import doyenm.zooshell.validator.function.FindingSexFunction;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.CanHaveAChirurgicalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.CanHaveAHormonalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithPreviousPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithSexPredicate;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
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
    StringLengthPredicates stringLenghtPredicates;

    @Autowired
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates;

    @Autowired
    IntegerValuePredicates integerValuePredicates;

    @Autowired
    NameValidator nameValidator;

    @Bean
    public FindingContraceptionFunction findingContraceptionFunction() {
        return new FindingContraceptionFunction();
    }

    @Bean
    public CanHaveAChirurgicalContraceptionPredicate canHaveAChirurgicalContraceptionPredicate() {
        return new CanHaveAChirurgicalContraceptionPredicate();
    }

    @Bean
    public CanHaveAHormonalContraceptionPredicate canHaveAHormonalContraceptionPredicate() {
        return new CanHaveAHormonalContraceptionPredicate();
    }

    @Bean
    public IsContraceptionCompatibleWithPreviousPredicate isContraceptionCompatibleWithPreviousPredicate() {
        return new IsContraceptionCompatibleWithPreviousPredicate();
    }

    @Bean
    public IsContraceptionCompatibleWithSexPredicate isContraceptionCompatibleWithSexPredicate() {
        return new IsContraceptionCompatibleWithSexPredicate();
    }

    // Validator
    @Bean
    public AnimalChangeNameValidator animalChangeNameValidator() {
        return new AnimalChangeNameValidator(findAnimal,
                nameValidator
        );
    }

    @Bean
    public AnimalChangePaddockValidator animalChangePaddockValidator() {
        return new AnimalChangePaddockValidator(findAnimal, findPaddock);
    }

    @Bean
    public AnimalCreationValidator animalCreationValidator() {
        return new AnimalCreationValidator(findPaddock, 
                Integer.parseInt(environment.getProperty("animal.name.max_length")),
        environment.getProperty("paddock.obsolescence.max_number_of_turn_when_unusable", Integer.class));
    }

//    @Bean 
//    AnimalDetailsValidator animalDetailsValidator(){
//        return new AnimalDetailsValidator();
//    }
    @Bean
    public AnimalUpdateContraceptionValidator animalUpdateContraceptionValidator() {
        return new AnimalUpdateContraceptionValidator(findingContraceptionFunction(),
                findAnimal,
                canHaveAHormonalContraceptionPredicate(),
                canHaveAChirurgicalContraceptionPredicate(),
                isContraceptionCompatibleWithPreviousPredicate(),
                isContraceptionCompatibleWithSexPredicate());
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

    @Bean
    public AnimalsListWithDietCriteriaValidator animalsListWithDietCriteriaValidator() {
        return new AnimalsListWithDietCriteriaValidator(lsWithCriteriaParser(), findingDietFunction);
    }

    @Bean
    public AnimalsListWithSexCriteriaValidator animalsListWithSexCriteriaValidator() {
        return new AnimalsListWithSexCriteriaValidator(lsWithCriteriaParser(), findingSexFunction);
    }

    @Bean
    public AnimalsListWithPaddockCriteriaValidator animalsListWithPaddockCriteriaValidator() {
        return new AnimalsListWithPaddockCriteriaValidator(lsWithCriteriaParser(), findPaddock);
    }

    @Bean
    public AnimalsListWithSpecieCriteriaValidator animalsListWithSpecieCriteriaValidator() {
        return new AnimalsListWithSpecieCriteriaValidator(lsWithCriteriaParser(), findingSpecieFunction);
    }

    @Bean
    public AnimalsWithCriteria animalsWithCriteria() {
        return new AnimalsWithCriteria(
                animalsListWithDietCriteriaValidator(),
                animalsListWithSexCriteriaValidator(),
                animalsListWithPaddockCriteriaValidator(),
                animalsListWithSpecieCriteriaValidator()
        );
    }
}
