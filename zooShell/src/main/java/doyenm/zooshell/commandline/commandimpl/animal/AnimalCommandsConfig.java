package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.commandimpl.ls.LsContraceptionMethod;
import doyenm.zooshell.commandline.commandimpl.ls.LsDiet;
import doyenm.zooshell.commandline.commandimpl.ls.LsSex;
import doyenm.zooshell.controller.animalcontroller.AnimalControllersConfig;
import doyenm.zooshell.validator.AnimalValidatorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({AnimalValidatorsConfig.class, AnimalControllersConfig.class})
public class AnimalCommandsConfig {
    
    @Autowired
    AnimalValidatorsConfig validators;
    
    @Autowired
    AnimalControllersConfig controllers;
    
    // Commands
    @Bean
    public RenameAnimal changeAnimalName() {
        return new RenameAnimal(validators.animalChangeNameValidator(), 
                controllers.animalChangeNameController());
    }

    @Bean
    public ChangePaddock changePaddock() {
        return new ChangePaddock(
                validators.animalChangePaddockValidator(), 
                controllers.animalChangePaddockController());
    }

    @Bean
    public CreateAnimal createAnimal() {
        return new CreateAnimal(
                validators.animalCreationValidator(), 
                controllers.animalCreationController());
    }

    @Bean
    public DetailAnimal detailAnimal() {
        return new DetailAnimal(
                validators.animalValidator(),
                controllers.animalDetailsController());
    }

    @Bean
    public LsAnimal lsAnimal() {
        return new LsAnimal();
    }

    @Bean
    public RemoveAnimal removeAnimal() {
        return new RemoveAnimal(
                validators.animalValidator(),
                controllers.animalRemoveController());
    }

    @Bean
    public ResetDiet resetDiet() {
        return new ResetDiet(
                validators.animalValidator(),
                controllers.animalResetDietController());
    }

    @Bean
    public UpdateContraceptionMethod updateContraceptionMethod() {
        return new UpdateContraceptionMethod(
                validators.animalUpdateContraceptionValidator(), 
                controllers.animalUpateContraceptionController());
    }

    @Bean
    public UpdateDiet updateDiet() {
        return new UpdateDiet(
                validators.animalUpdateDietValidator(), 
                controllers.animalUpdateDietController());
    }

    @Bean
    public UpdateFastDays updateFastDays() {
        return new UpdateFastDays(
                validators.animalUpdateFastDaysValidator(), 
                controllers.animalUpdateFastDaysController());
    }

    @Bean
    public UpdateFoodQuantity updateFoodQuantity() {
        return new UpdateFoodQuantity(
                validators.animalUpdateFoodQuantityValidator(),
                controllers.animalUpdateFoodQuantityController());
    }

    @Bean
    public LsContraceptionMethod lsContraceptionMethod() {
        return new LsContraceptionMethod();
    }

    @Bean
    public LsDiet lsDiet() {
        return new LsDiet();
    }

    @Bean
    public LsSex lsSex() {
        return new LsSex();
    }

    @Bean
    public LsAnimalsWithCriteria lsAnimalsWithCriteria() {
        return new LsAnimalsWithCriteria(
                validators.animalsWithCriteria(), 
                controllers.lsAnimalsWithCriteriaController());
    }
}
