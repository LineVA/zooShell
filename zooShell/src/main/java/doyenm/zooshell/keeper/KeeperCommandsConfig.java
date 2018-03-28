package doyenm.zooshell.keeper;

import doyenm.zooshell.keeper.rename.ChangeKeeperName;
import doyenm.zooshell.keeper.creation.CreateKeeper;
import doyenm.zooshell.keeper.details.DetailKeeper;
import doyenm.zooshell.keeper.tasks.LsKeeperTask;
import doyenm.zooshell.keeper.list.LsKeeper;
import doyenm.zooshell.keeper.remove.RemoveKeeper;
import doyenm.zooshell.keeper.tasks.ResetOccupations;
import doyenm.zooshell.keeper.tasks.UpdateOccupations;
import doyenm.zooshell.keeper.training.AddTraining;
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

    @Bean
    public LsKeeperTask lsKeeperTask() {
        return new LsKeeperTask();
    }
    
    @Bean
    public AddTraining addTraining(){
        return new AddTraining(
                validators.keeperAddTrainingValidator(), 
                controllers.keeperAddTrainingController()
        );
    }
}
