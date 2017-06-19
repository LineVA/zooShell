package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.keeper.ChangeKeeperName;
import doyenm.zooshell.commandLine.commandImpl.keeper.CreateKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.DetailKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.LsKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.RemoveKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.ResetOccupations;
import doyenm.zooshell.commandLine.commandImpl.keeper.UpdateOccupations;
import doyenm.zooshell.controller.keepercontroller.*;
import doyenm.zooshell.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooShellKeeperConfig {

// Controllers
    @Bean
    KeeperRenameController keeperRenameController() {
        return new KeeperRenameController();
    }

    @Bean
    KeeperCreationController keeperCreationController() {
        return new KeeperCreationController();
    }

    @Bean
    KeeperDetailsController keeperDetailsController() {
        return new KeeperDetailsController();
    }

    @Bean
    KeeperDeletionController keeperDeletionController() {
        return new KeeperDeletionController();
    }

    @Bean
    KeeperResetOccupationsController keeperResetOccupationsController() {
        return new KeeperResetOccupationsController();
    }
    
    @Bean
    KeeperUpdateOccupationsController keeperUpdateOccupationsController(){
        return new KeeperUpdateOccupationsController();
    }

    // Validators
    @Bean
    KeeperRenameValidator keeperRenameValidator() {
        return new KeeperRenameValidator();
    }

    @Bean
    KeeperCreationValidator keeperCreationValidator() {
        return new KeeperCreationValidator();
    }

    @Bean
    KeeperValidator keeperValidator() {
        return new KeeperValidator();
    }

    @Bean
    KeeperUpdateOccupationsValidator keeperUpdateOccupationsValidator(){
        return new KeeperUpdateOccupationsValidator();
    }
    
    // Commands
    @Bean 
    ChangeKeeperName changeKeeperName(){
        return new ChangeKeeperName(keeperRenameValidator(), keeperRenameController());
    }

    @Bean
    CreateKeeper createKeeper(){
        return new CreateKeeper(keeperCreationValidator(), keeperCreationController());
    }
    
    @Bean
    DetailKeeper detailKeeper(){
        return new DetailKeeper(keeperValidator(), keeperDetailsController());
    }
    
    @Bean
    LsKeeper lsKeeper(){
        return new LsKeeper();
    }
    
    @Bean
    RemoveKeeper removeKeeper(){
        return new RemoveKeeper(keeperValidator(), keeperDeletionController());
    }
    
    @Bean
    ResetOccupations resetOccupations(){
        return new ResetOccupations(keeperValidator(), keeperResetOccupationsController());
    }
    
    @Bean
    UpdateOccupations updateOccupations(){
        return new UpdateOccupations(keeperUpdateOccupationsValidator(), keeperUpdateOccupationsController());
    }
}






























