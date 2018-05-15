package doyenm.zooshell.paddock;

import doyenm.zooshell.paddock.biomes.LsBiome;
import doyenm.zooshell.paddock.biomes.UpdateBiome;
import doyenm.zooshell.paddock.creation.CreatePaddock;
import doyenm.zooshell.paddock.details.DetailPad;
import doyenm.zooshell.paddock.entry.CreatePaddockEntry;
import doyenm.zooshell.paddock.extension.CreatePaddockExtension;
import doyenm.zooshell.paddock.facilities.LsPaddockFacilities;
import doyenm.zooshell.paddock.facilities.ResetPaddockFacilities;
import doyenm.zooshell.paddock.facilities.UpdatePaddockFacilities;
import doyenm.zooshell.paddock.list.LsPaddock;
import doyenm.zooshell.paddock.remove.RemovePaddock;
import doyenm.zooshell.paddock.rename.RenamePaddock;
import doyenm.zooshell.paddock.types.LsPaddockType;
import doyenm.zooshell.paddock.types.UpdatePaddockType;
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
    public UpdatePaddockFacilities updatePaddockArrangements() {
        return new UpdatePaddockFacilities(
                validators.updatePaddockArrangementExistenceValidator(),
                validators.updatePaddockArrangementCoherenceValidator(),
                controllers.updatePaddockArrangementController());
    }

    @Bean
    public ResetPaddockFacilities resetPaddockFacilities(){
        return new ResetPaddockFacilities(
                validators.paddockValidator(),
                controllers.resetPaddockFacilitiesController());
    }

    @Bean
    public LsPaddockType lsPaddockType() {
        return new LsPaddockType();
    }

    @Bean
    public LsBiome lsBiome() {
        return new LsBiome();
    }
    
     @Bean
    public LsPaddockFacilities lsPaddockArrangements() {
        return new LsPaddockFacilities();
    }
}
