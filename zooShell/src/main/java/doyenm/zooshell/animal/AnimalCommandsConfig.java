package doyenm.zooshell.animal;

import doyenm.zooshell.animal.contraception.UpdateContraceptionMethod;
import doyenm.zooshell.animal.creation.CreateAnimal;
import doyenm.zooshell.animal.details.DetailAnimal;
import doyenm.zooshell.animal.diets.ResetDiet;
import doyenm.zooshell.animal.diets.UpdateDiet;
import doyenm.zooshell.animal.diets.UpdateFastDays;
import doyenm.zooshell.animal.diets.UpdateFoodQuantity;
import doyenm.zooshell.animal.list.LsAnimal;
import doyenm.zooshell.animal.list.LsAnimalsWithCriteria;
import doyenm.zooshell.animal.move.ChangePaddock;
import doyenm.zooshell.animal.remove.RemoveAnimal;
import doyenm.zooshell.animal.rename.RenameAnimal;
import doyenm.zooshell.animal.contraception.LsContraceptionMethod;
import doyenm.zooshell.animal.diets.LsDiet;
import doyenm.zooshell.animal.creation.LsSex;
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