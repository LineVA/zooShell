package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.ZooShellAnimalConfig;
import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.commandLine.commandImpl.ls.LsDiet;
import doyenm.zooshell.commandLine.commandImpl.ls.LsSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({ZooShellAnimalConfig.class})
public class AnimalCommandsConfig {
    
    @Autowired
    ZooShellAnimalConfig zooShellAnimalConfig;
    
    // Commands
    @Bean
    public ChangeAnimalName changeAnimalName() {
        return new ChangeAnimalName(zooShellAnimalConfig.animalChangeNameValidator(), 
                zooShellAnimalConfig.animalChangeNameController());
    }

    @Bean
    public ChangePaddock changePaddock() {
        return new ChangePaddock(
                zooShellAnimalConfig.animalChangePaddockValidator(), 
                zooShellAnimalConfig.animalChangePaddockController());
    }

    @Bean
    public CreateAnimal createAnimal() {
        return new CreateAnimal(
                zooShellAnimalConfig.animalCreationValidator(), 
                zooShellAnimalConfig.animalCreationController());
    }

    @Bean
    public DetailAnimal detailAnimal() {
        return new DetailAnimal(
                zooShellAnimalConfig.animalValidator(),
                zooShellAnimalConfig.animalDetailsController());
    }

    @Bean
    public LsAnimal lsAnimal() {
        return new LsAnimal();
    }

    @Bean
    public RemoveAnimal removeAnimal() {
        return new RemoveAnimal(
                zooShellAnimalConfig.animalValidator(),
                zooShellAnimalConfig.animalRemoveController());
    }

    @Bean
    public ResetDiet resetDiet() {
        return new ResetDiet(
                zooShellAnimalConfig.animalValidator(),
                zooShellAnimalConfig.animalResetDietController());
    }

    @Bean
    public UpdateContraceptionMethod updateContraceptionMethod() {
        return new UpdateContraceptionMethod(
                zooShellAnimalConfig.animalUpdateContraceptionValidator(), 
                zooShellAnimalConfig.animalUpateContraceptionController());
    }

    @Bean
    public UpdateDiet updateDiet() {
        return new UpdateDiet(
                zooShellAnimalConfig.animalUpdateDietValidator(), 
                zooShellAnimalConfig.animalUpdateDietController());
    }

    @Bean
    public UpdateFastDays updateFastDays() {
        return new UpdateFastDays(
                zooShellAnimalConfig.animalUpdateFastDaysValidator(), 
                zooShellAnimalConfig.animalUpdateFastDaysController());
    }

    @Bean
    public UpdateFoodQuantity updateFoodQuantity() {
        return new UpdateFoodQuantity(
                zooShellAnimalConfig.animalUpdateFoodQuantityValidator(),
                zooShellAnimalConfig.animalUpdateFoodQuantityController());
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
                zooShellAnimalConfig.animalsWithCriteria(), 
                zooShellAnimalConfig.lsAnimalsWithCriteriaController());
    }
}
