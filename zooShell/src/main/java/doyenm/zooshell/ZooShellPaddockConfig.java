package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.ls.LsBiome;
import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.commandLine.commandImpl.paddock.*;
import doyenm.zooshell.controller.paddockcontroller.*;
import doyenm.zooshell.validator.FindPaddock;
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
import doyenm.zooshell.validator.function.FindingBiomeFunction;
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

    // Predicates
    @Bean
    FindPaddock findPaddock() {
        return new FindPaddock();
    }

    @Bean
    FindingBiomeFunction findingBiomeFunction() {
        return new FindingBiomeFunction();
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
        return new PaddockRemoveValidator(findPaddock());
    }

    @Bean
    PaddockValidator paddockValidator() {
        return new PaddockValidator(findPaddock());
    }

    @Bean
    UpdateBiomeValidator updateBiomeValidator() {
        return new UpdateBiomeValidator(findPaddock(), findingBiomeFunction());
    }

    @Bean
    UpdatePaddockTypeValidator updatePaddockTypeValidator() {
        return new UpdatePaddockTypeValidator();
    }

    // Commands
    @Bean
    ChangePaddockName changePaddockName() {
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
    DetailPad detailPad() {
        return new DetailPad(paddockValidator(), paddockDetailsController());
    }

    @Bean
    LsBiome lsBiome() {
        return new LsBiome();
    }

    @Bean
    LsPaddock lsPaddock() {
        return new LsPaddock();
    }

    @Bean
    LsPaddockType LsPaddockType() {
        return new LsPaddockType();
    }

    @Bean
    RemovePaddock removePaddock() {
        return new RemovePaddock(paddockRemoveValidator(), paddockRemoveController());
    }

    @Bean
    UpdateBiome updateBiome() {
        return new UpdateBiome(updateBiomeValidator(), updateBiomeController());
    }

    @Bean
    UpdatePaddockType updatePaddockType() {
        return new UpdatePaddockType(updatePaddockTypeValidator(), updatePaddockTypeController());
    }

}
