package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.ls.LsBiome;
import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.commandLine.commandImpl.paddock.ChangePaddockName;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddockEntry;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddockExtension;
import doyenm.zooshell.commandLine.commandImpl.paddock.DetailPad;
import doyenm.zooshell.commandLine.commandImpl.paddock.LsPaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.RemovePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdatePaddockType;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockDetailsController;
import doyenm.zooshell.controller.paddockcontroller.PaddockEntryCreationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockExtensionCreationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockRemoveController;
import doyenm.zooshell.controller.paddockcontroller.UpdateBiomeController;
import doyenm.zooshell.controller.paddockcontroller.UpdatePaddockTypeController;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockEntryCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import doyenm.zooshell.validator.PaddockRemoveValidator;
import doyenm.zooshell.validator.PaddockValidator;
import doyenm.zooshell.validator.UpdateBiomeValidator;
import doyenm.zooshell.validator.UpdatePaddockTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooShellPaddockConfig {

    // Controller
    @Bean
    PaddockChangeNameController paddockChangeNameController() {
        return new PaddockChangeNameController();
    }

    @Bean
    PaddockCreationController paddockCreationController() {
        return new PaddockCreationController();
    }

    @Bean
    PaddockDetailsController paddockDetailsController() {
        return new PaddockDetailsController();
    }

    @Bean
    PaddockEntryCreationController paddockEntryCreationController() {
        return new PaddockEntryCreationController();
    }

    @Bean
    PaddockExtensionCreationController paddockExtensionCreationController() {
        return new PaddockExtensionCreationController();
    }

    @Bean
    PaddockRemoveController paddockRemoveController() {
        return new PaddockRemoveController();
    }

    @Bean
    UpdateBiomeController updateBiomeController() {
        return new UpdateBiomeController();
    }

    @Bean
    UpdatePaddockTypeController updatePaddockTypeController() {
        return new UpdatePaddockTypeController();
    }

    // Validators
    @Bean
    PaddockChangeNameValidator paddockChangeNameValidator() {
        return new PaddockChangeNameValidator();
    }

    @Bean
    PaddockCreationValidator paddockCreationValidator() {
        return new PaddockCreationValidator();
    }

    @Bean
    PaddockEntryCreationValidator paddockEntryCreationValidator() {
        return new PaddockEntryCreationValidator();
    }

    @Bean
    PaddockExtensionCreationValidator paddockExtensionCreationValidator() {
        return new PaddockExtensionCreationValidator();
    }

    @Bean
    PaddockExtensionLocationValidator paddockExtensionLocationValidator() {
        return new PaddockExtensionLocationValidator();
    }

    @Bean
    PaddockLocationValidator paddockLocationValidator() {
        return new PaddockLocationValidator();
    }

    @Bean
    PaddockRemoveValidator paddockRemoveValidator() {
        return new PaddockRemoveValidator();
    }

    @Bean
    PaddockValidator paddockValidator() {
        return new PaddockValidator();
    }

    @Bean
    UpdateBiomeValidator updateBiomeValidator() {
        return new UpdateBiomeValidator();
    }

    @Bean
    UpdatePaddockTypeValidator updatePaddockTypeValidator() {
        return new UpdatePaddockTypeValidator();
    }

    // Commands
    @Bean
    ChangePaddockName changePaddockName(){
        return new ChangePaddockName(paddockChangeNameValidator(), paddockChangeNameController());
    }
    
    @Bean
    CreatePaddock createPaddock() {
        return new CreatePaddock(paddockCreationValidator(), paddockLocationValidator(), paddockCreationController());
    }
    
     @Bean
    CreatePaddockEntry createPaddockEntry() {
        return new CreatePaddockEntry(paddockEntryCreationValidator(), paddockEntryCreationController());
    }
    
     @Bean
    CreatePaddockExtension createPaddockExtension() {
        return new CreatePaddockExtension(paddockExtensionCreationValidator(), 
                paddockExtensionLocationValidator(), paddockExtensionCreationController());
    }
    
    @Bean 
    DetailPad detailPad(){
        return new DetailPad(paddockValidator(), paddockDetailsController());
    }
    
    @Bean
    LsBiome lsBiome() {
        return new LsBiome();
    }
    
    @Bean
    LsPaddock lsPaddock(){
        return new LsPaddock();
    }

    @Bean
    LsPaddockType LsPaddockType() {
        return new LsPaddockType();
    }

    @Bean
    RemovePaddock removePaddock(){
        return new RemovePaddock(paddockRemoveValidator(), paddockRemoveController());
    }
    
    @Bean
    UpdateBiome updateBiome(){
        return new UpdateBiome(updateBiomeValidator(), updateBiomeController());
    }
    
    @Bean
    UpdatePaddockType updatePaddockType(){
        return new UpdatePaddockType(updatePaddockTypeValidator(), updatePaddockTypeController());
    }
    
}
