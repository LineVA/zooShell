package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.animal.ChangeAnimalName;
import doyenm.zooshell.commandLine.commandImpl.animal.ChangePaddock;
import doyenm.zooshell.commandLine.commandImpl.animal.CreateAnimal;
import doyenm.zooshell.commandLine.commandImpl.animal.DetailAnimal;
import doyenm.zooshell.commandLine.commandImpl.animal.LsAnimal;
import doyenm.zooshell.commandLine.commandImpl.animal.RemoveAnimal;
import doyenm.zooshell.commandLine.commandImpl.animal.ResetDiet;
import doyenm.zooshell.commandLine.commandImpl.animal.UpdateDiet;
import doyenm.zooshell.commandLine.commandImpl.animal.UpdateFastDays;
import doyenm.zooshell.commandLine.commandImpl.animal.UpdateFoodQuantity;
import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.commandLine.commandImpl.ls.LsDiet;
import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import doyenm.zooshell.controller.animalcontroller.AnimalChangeNameController;
import doyenm.zooshell.controller.animalcontroller.AnimalChangePaddockController;
import doyenm.zooshell.controller.animalcontroller.AnimalCreationController;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.controller.animalcontroller.AnimalRemoveController;
import doyenm.zooshell.controller.animalcontroller.AnimalResetDietController;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateDietController;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateFastDaysController;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateFoodQuantityController;
import doyenm.zooshell.validator.AnimalChangeNameValidator;
import doyenm.zooshell.validator.AnimalChangePaddockValidator;
import doyenm.zooshell.validator.AnimalCreationValidator;
import doyenm.zooshell.validator.AnimalDetailsValidator;
import doyenm.zooshell.validator.AnimalUpdateDietValidator;
import doyenm.zooshell.validator.AnimalUpdateFastDaysValidator;
import doyenm.zooshell.validator.AnimalUpdateFoodQuantityValidator;
import doyenm.zooshell.validator.AnimalValidator;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author doyenm
 */
public class ZooShellAnimalConfig {

    // Controller
    @Bean
    AnimalChangeNameController animalChangeNameController() {
        return new AnimalChangeNameController();
    }
    
    @Bean
    AnimalChangePaddockController animalChangePaddockController(){
        return new AnimalChangePaddockController();
    }
    
    @Bean
    AnimalCreationController animalCreationController(){
        return new AnimalCreationController();
    }
            
    @Bean 
    AnimalDetailsController animalDetailsController(){
        return new AnimalDetailsController();
    }
    
    @Bean
    AnimalRemoveController animalRemoveController(){
        return new AnimalRemoveController();
    }
    
    @Bean
    AnimalResetDietController animalResetDietController(){
        return new AnimalResetDietController();
    }
    
    @Bean
    AnimalUpdateDietController animalUpdateDietController(){
        return new AnimalUpdateDietController();
    }
    
    @Bean
    AnimalUpdateFastDaysController animalUpdateFastDaysController(){
        return new AnimalUpdateFastDaysController();
    }
    
    @Bean
    AnimalUpdateFoodQuantityController animalUpdateFoodQuantityController(){
        return new AnimalUpdateFoodQuantityController();
    }
    
    // Validator
    @Bean
    AnimalChangeNameValidator animalChangeNameValidator(){
        return new AnimalChangeNameValidator();
    }
    
      @Bean
    AnimalChangePaddockValidator animalChangePaddockValidator(){
        return new AnimalChangePaddockValidator();
    }
    
    @Bean
    AnimalCreationValidator animalCreationValidator(){
        return new AnimalCreationValidator();
    }
    
    @Bean 
    AnimalDetailsValidator animalDetailsValidator(){
        return new AnimalDetailsValidator();
    }
    
    @Bean
    AnimalUpdateDietValidator animalUpdateDietValidator(){
        return new AnimalUpdateDietValidator();
    }
    
     @Bean
    AnimalUpdateFastDaysValidator animalUpdateFastDaysValidator(){
        return new AnimalUpdateFastDaysValidator();
    }
    
     @Bean
    AnimalUpdateFoodQuantityValidator animalUpdateFoodQuantityValidator(){
        return new AnimalUpdateFoodQuantityValidator();
    }
    
    @Bean
    AnimalValidator animalValidator(){
        return new AnimalValidator();
    }
    
    // Commands
    @Bean
    ChangeAnimalName changeAnimalName(){
        return new ChangeAnimalName(animalChangeNameValidator(), animalChangeNameController());
    }

    @Bean
    ChangePaddock changePaddock(){
        return new ChangePaddock(animalChangePaddockValidator(), animalChangePaddockController());
    }
    
    @Bean
    CreateAnimal createAnimal(){
        return new CreateAnimal(animalCreationValidator(), animalCreationController());
    }
    
    @Bean
    DetailAnimal detailAnimal(){
        return new DetailAnimal(animalDetailsValidator(), animalDetailsController());
    }
    
    @Bean
    LsAnimal lsAnimal(){
        return new LsAnimal();
    }
    
    @Bean
    RemoveAnimal removeAnimal(){
        return new RemoveAnimal(animalValidator(), animalRemoveController());
    }
    
    @Bean
    ResetDiet resetDiet(){
        return new ResetDiet(animalValidator(), animalResetDietController());
    }
    
    @Bean
    UpdateDiet updateDiet(){
        return new UpdateDiet(animalUpdateDietValidator(), animalUpdateDietController());
    }
    
    @Bean
    UpdateFastDays updateFastDays(){
        return new UpdateFastDays(animalUpdateFastDaysValidator(), animalUpdateFastDaysController());
    }
    
    @Bean
    UpdateFoodQuantity updateFoodQuantity(){
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
}















