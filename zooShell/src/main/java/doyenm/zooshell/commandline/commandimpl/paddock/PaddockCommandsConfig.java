package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.controller.paddockcontroller.PaddockControllerConfig;
import doyenm.zooshell.validator.PaddockValidatorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({PaddockControllerConfig.class, PaddockValidatorsConfig.class})
public class PaddockCommandsConfig {

    @Autowired
    PaddockValidatorsConfig validators;

    @Autowired
    PaddockControllerConfig controllers;

    @Bean
    public RenamePaddock changePaddockName() {
        return new RenamePaddock(
                validators.paddockChangeNameValidator(),
                controllers.paddockChangeNameController());
    }

    @Bean
    public CreatePaddock createPaddock() {
        return new CreatePaddock(
                validators.paddockCreationValidator(),
                validators.paddockLocationValidator(),
                controllers.paddockCreationController());
    }

    @Bean
    public CreatePaddockEntry createPaddockEntry() {
        return new CreatePaddockEntry(
                validators.paddockEntryCreationValidator(),
                controllers.paddockEntryCreationController());
    }

    @Bean
    public CreatePaddockExtension createPaddockExtension() {
        return new CreatePaddockExtension(
                validators.paddockExtensionCreationValidator(),
                validators.paddockExtensionLocationValidator(),
                controllers.paddockExtensionCreationController());
    }

    @Bean
    public DetailPad detailPad() {
        return new DetailPad(
                validators.paddockValidator(),
                controllers.paddockDetailsController());
    }

    @Bean
    public LsPaddock lsPaddock() {
        return new LsPaddock();
    }

    @Bean
    public RemovePaddock removePaddock() {
        return new RemovePaddock(
                validators.paddockRemoveValidator(),
                controllers.paddockRemoveController());
    }

    @Bean
    public UpdateBiome updateBiome() {
        return new UpdateBiome(
                validators.updateBiomeValidator(),
                controllers.updateBiomeController());
    }

    @Bean
    public UpdatePaddockType updatePaddockType() {
        return new UpdatePaddockType(
                validators.updatePaddockTypeValidator(),
                controllers.updatePaddockTypeController());
    }

    @Bean
    public LsPaddockType LsPaddockType() {
        return new LsPaddockType();
    }

    @Bean
    public LsBiome lsBiome() {
        return new LsBiome();
    }
}
