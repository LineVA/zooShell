package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.controller.keepercontroller.KeeperControllersConfig;
import doyenm.zooshell.validator.KeeperValidatorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({KeeperValidatorsConfig.class, KeeperControllersConfig.class})
public class KeeperCommandsConfig {

    @Autowired
    KeeperValidatorsConfig validators;

    @Autowired
    KeeperControllersConfig controllers;

    @Bean
    public ChangeKeeperName changeKeeperName() {
        return new ChangeKeeperName(
                validators.keeperRenameValidator(),
                controllers.keeperRenameController()
        );
    }

    @Bean
    public CreateKeeper createKeeper() {
        return new CreateKeeper(
                validators.keeperCreationValidator(),
                controllers.keeperCreationController()
        );
    }

    @Bean
    public DetailKeeper detailKeeper() {
        return new DetailKeeper(
                validators.keeperValidator(),
                controllers.keeperDetailsController()
        );
    }

    @Bean
    public LsKeeper lsKeeper() {
        return new LsKeeper();
    }

    @Bean
    public RemoveKeeper removeKeeper() {
        return new RemoveKeeper(
                validators.keeperValidator(),
                controllers.keeperDeletionController()
        );
    }

    @Bean
    public ResetOccupations resetOccupations() {
        return new ResetOccupations(
                validators.keeperValidator(),
                controllers.keeperResetOccupationsController()
        );
    }

    @Bean
    public UpdateOccupations updateOccupations() {
        return new UpdateOccupations(
                validators.keeperUpdateOccupationsValidator(),
                controllers.keeperUpdateOccupationsController()
        );
    }
}
