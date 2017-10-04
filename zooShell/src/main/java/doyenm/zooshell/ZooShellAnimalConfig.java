package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.animal.*;
import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.commandLine.commandImpl.ls.LsDiet;
import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import doyenm.zooshell.controller.animalcontroller.*;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithDietCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithPaddockCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithSexCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithSpecieCriteriaController;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.validator.AnimalChangeNameValidator;
import doyenm.zooshell.validator.AnimalChangePaddockValidator;
import doyenm.zooshell.validator.AnimalCreationValidator;
import doyenm.zooshell.validator.AnimalUpdateContraceptionValidator;
import doyenm.zooshell.validator.AnimalUpdateDietValidator;
import doyenm.zooshell.validator.AnimalUpdateFastDaysValidator;
import doyenm.zooshell.validator.AnimalUpdateFoodQuantityValidator;
import doyenm.zooshell.validator.AnimalValidator;
import doyenm.zooshell.validator.AnimalsWithCriteria;
import doyenm.zooshell.validator.criteria.AnimalsListWithDietCriteriaValidator;
import doyenm.zooshell.validator.FindAnimal;
import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.validator.criteria.AnimalsListWithPaddockCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSexCriteriaValidator;
import doyenm.zooshell.validator.criteria.AnimalsListWithSpecieCriteriaValidator;
import doyenm.zooshell.validator.criteria.LsWithCriteriaParser;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.function.FindingContraceptionFunction;
import doyenm.zooshell.validator.function.FindingDietFunction;
import doyenm.zooshell.validator.function.FindingSexFunction;
import doyenm.zooshell.validator.function.FindingSpecieFunction;
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
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@Import(ZooShellPredicatesConfig.class)
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class ZooShellAnimalConfig {

    @Autowired
    Environment environment;

    @Bean
    Utils utils() {
        return new Utils();
    }

    // Controller
    @Bean
    AnimalChangeNameController animalChangeNameController() {
        return new AnimalChangeNameController();
    }

    @Bean
    AnimalChangePaddockController animalChangePaddockController() {
        return new AnimalChangePaddockController();
    }

    @Bean
    AnimalCreationController animalCreationController() {
        return new AnimalCreationController();
    }

    @Bean
    AnimalDetailsController animalDetailsController() {
        return new AnimalDetailsController(utils());
    }

    @Bean
    AnimalRemoveController animalRemoveController() {
        return new AnimalRemoveController();
    }

    @Bean
    AnimalResetDietController animalResetDietController() {
        return new AnimalResetDietController();
    }

    @Bean
    AnimalUpdateDietController animalUpdateDietController() {
        return new AnimalUpdateDietController();
    }

    @Bean
    AnimalUpdateContraceptionController animalUpateContraceptionController() {
        return new AnimalUpdateContraceptionController();
    }

    @Bean
    AnimalUpdateFastDaysController animalUpdateFastDaysController() {
        return new AnimalUpdateFastDaysController();
    }

    @Bean
    AnimalUpdateFoodQuantityController animalUpdateFoodQuantityController() {
        return new AnimalUpdateFoodQuantityController();
    }

    @Bean
    AnimalsWithDietCriteriaController animalsWithDietCriteriaController() {
        return new AnimalsWithDietCriteriaController();
    }

    @Bean
    AnimalsWithSexCriteriaController animalsWithSexCriteriaController() {
        return new AnimalsWithSexCriteriaController();
    }
    
    @Bean
    AnimalsWithPaddockCriteriaController animalsWithPaddockCriteriaController() {
        return new AnimalsWithPaddockCriteriaController();
    }
    
    @Bean
    AnimalsWithSpecieCriteriaController animalsWithSpecieCriteriaController() {
        return new AnimalsWithSpecieCriteriaController();
    }
    
    @Bean
    LsAnimalsWithCriteriaController lsAnimalsWithCriteriaController() {
        return new LsAnimalsWithCriteriaController(
                animalsWithDietCriteriaController(), 
                animalsWithSexCriteriaController(),
                animalsWithPaddockCriteriaController(),
                animalsWithSpecieCriteriaController()
        );
    }

    // Predicates
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

    @Bean
    FindingContraceptionFunction findingContraceptionFunction() {
        return new FindingContraceptionFunction();
    }

    @Bean
    CanHaveAChirurgicalContraceptionPredicate canHaveAChirurgicalContraceptionPredicate() {
        return new CanHaveAChirurgicalContraceptionPredicate();
    }

    @Bean
    CanHaveAHormonalContraceptionPredicate canHaveAHormonalContraceptionPredicate() {
        return new CanHaveAHormonalContraceptionPredicate();
    }

    @Bean
    IsContraceptionCompatibleWithPreviousPredicate isContraceptionCompatibleWithPreviousPredicate() {
        return new IsContraceptionCompatibleWithPreviousPredicate();
    }

    @Bean
    IsContraceptionCompatibleWithSexPredicate isContraceptionCompatibleWithSexPredicate() {
        return new IsContraceptionCompatibleWithSexPredicate();
    }

    // Validator
    @Bean
    AnimalChangeNameValidator animalChangeNameValidator() {
        return new AnimalChangeNameValidator(findAnimal,
                stringLenghtPredicates,
                uniquenessNamesBiPredicates,
                Integer.parseInt(environment.getProperty("animal.name.max_length"))
        );
    }

    @Bean
    AnimalChangePaddockValidator animalChangePaddockValidator() {
        return new AnimalChangePaddockValidator(findAnimal, findPaddock);
    }

    @Bean
    AnimalCreationValidator animalCreationValidator() {
        return new AnimalCreationValidator(Integer.parseInt(environment.getProperty("animal.name.max_length")));
    }

//    @Bean 
//    AnimalDetailsValidator animalDetailsValidator(){
//        return new AnimalDetailsValidator();
//    }
    @Bean
    AnimalUpdateContraceptionValidator animalUpdateContraceptionValidator() {
        return new AnimalUpdateContraceptionValidator(findingContraceptionFunction(),
                findAnimal,
                canHaveAHormonalContraceptionPredicate(),
                canHaveAChirurgicalContraceptionPredicate(),
                isContraceptionCompatibleWithPreviousPredicate(),
                isContraceptionCompatibleWithSexPredicate());
    }

    @Bean
    AnimalUpdateDietValidator animalUpdateDietValidator() {
        return new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
    }

    @Bean
    AnimalUpdateFastDaysValidator animalUpdateFastDaysValidator() {
        return new AnimalUpdateFastDaysValidator(findAnimal, integerValuePredicates);
    }

    @Bean
    AnimalUpdateFoodQuantityValidator animalUpdateFoodQuantityValidator() {
        return new AnimalUpdateFoodQuantityValidator(doubleValuesPredicates, findAnimal);
    }

    @Bean
    AnimalValidator animalValidator() {
        return new AnimalValidator();
    }

    @Bean
    LsWithCriteriaParser lsWithCriteriaParser() {
        return new LsWithCriteriaParser();
    }

    @Bean
    AnimalsListWithDietCriteriaValidator animalsListWithDietCriteriaValidator() {
        return new AnimalsListWithDietCriteriaValidator(lsWithCriteriaParser(), findingDietFunction);
    }

    @Bean
    AnimalsListWithSexCriteriaValidator animalsListWithSexCriteriaValidator() {
        return new AnimalsListWithSexCriteriaValidator(lsWithCriteriaParser(), findingSexFunction);
    }
    
     @Bean
    AnimalsListWithPaddockCriteriaValidator animalsListWithPaddockCriteriaValidator() {
        return new AnimalsListWithPaddockCriteriaValidator(lsWithCriteriaParser(), findPaddock);
    }
    
    @Bean
    AnimalsListWithSpecieCriteriaValidator animalsListWithSpecieCriteriaValidator() {
        return new AnimalsListWithSpecieCriteriaValidator(lsWithCriteriaParser(), findingSpecieFunction);
    }

    @Bean
    AnimalsWithCriteria animalsWithCriteria() {
        return new AnimalsWithCriteria(
                animalsListWithDietCriteriaValidator(),
                animalsListWithSexCriteriaValidator(),
                animalsListWithPaddockCriteriaValidator(),
                animalsListWithSpecieCriteriaValidator()
        );
    }

    // Commands
    @Bean
    ChangeAnimalName changeAnimalName() {
        return new ChangeAnimalName(animalChangeNameValidator(), animalChangeNameController());
    }

    @Bean
    ChangePaddock changePaddock() {
        return new ChangePaddock(animalChangePaddockValidator(), animalChangePaddockController());
    }

    @Bean
    CreateAnimal createAnimal() {
        return new CreateAnimal(animalCreationValidator(), animalCreationController());
    }

    @Bean
    DetailAnimal detailAnimal() {
        return new DetailAnimal(animalValidator(), animalDetailsController());
    }

    @Bean
    LsAnimal lsAnimal() {
        return new LsAnimal();
    }

    @Bean
    RemoveAnimal removeAnimal() {
        return new RemoveAnimal(animalValidator(), animalRemoveController());
    }

    @Bean
    ResetDiet resetDiet() {
        return new ResetDiet(animalValidator(), animalResetDietController());
    }

    @Bean
    UpdateContraceptionMethod updateContraceptionMethod() {
        return new UpdateContraceptionMethod(animalUpdateContraceptionValidator(), animalUpateContraceptionController());
    }

    @Bean
    UpdateDiet updateDiet() {
        return new UpdateDiet(animalUpdateDietValidator(), animalUpdateDietController());
    }

    @Bean
    UpdateFastDays updateFastDays() {
        return new UpdateFastDays(animalUpdateFastDaysValidator(), animalUpdateFastDaysController());
    }

    @Bean
    UpdateFoodQuantity updateFoodQuantity() {
        return new UpdateFoodQuantity(animalUpdateFoodQuantityValidator(), animalUpdateFoodQuantityController());
    }

    @Bean
    LsContraceptionMethod lscontraceptionMethod() {
        return new LsContraceptionMethod();
    }

    @Bean
    LsDiet lsDiet() {
        return new LsDiet();
    }

    @Bean
    LsSex lsSex() {
        return new LsSex();
    }

    @Bean
    LsAnimalsWithCriteria lsAnimalsWithCriteria() {
        return new LsAnimalsWithCriteria(animalsWithCriteria(), lsAnimalsWithCriteriaController());
    }
}
