package doyenm.zooshell.controller.paddockcontroller;

import org.springframework.context.annotation.Bean;

/**
 *
 * @author doyenm
 */
public class PaddockControllerConfig {

    @Bean
    public PaddockChangeNameController paddockChangeNameController() {
        return new PaddockChangeNameController();
    }

    @Bean
    public PaddockCreationController paddockCreationController() {
        return new PaddockCreationController();
    }

    @Bean
    public PaddockDetailsController paddockDetailsController() {
        return new PaddockDetailsController();
    }

    @Bean
    public PaddockEntryCreationController paddockEntryCreationController() {
        return new PaddockEntryCreationController();
    }

    @Bean
    public PaddockExtensionCreationController paddockExtensionCreationController() {
        return new PaddockExtensionCreationController();
    }

    @Bean
    public PaddockRemoveController paddockRemoveController() {
        return new PaddockRemoveController();
    }

    @Bean
    public UpdateBiomeController updateBiomeController() {
        return new UpdateBiomeController();
    }

    @Bean
    public UpdatePaddockTypeController updatePaddockTypeController() {
        return new UpdatePaddockTypeController();
    }
}
