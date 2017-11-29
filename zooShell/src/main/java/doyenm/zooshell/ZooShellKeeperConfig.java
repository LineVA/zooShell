package doyenm.zooshell;

//import doyenm.zooshell.commandLine.commandImpl.keeper.AddTraining;
import doyenm.zooshell.commandLine.commandImpl.keeper.ChangeKeeperName;
import doyenm.zooshell.commandLine.commandImpl.keeper.CreateKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.DetailKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.LsKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.RemoveKeeper;
import doyenm.zooshell.commandLine.commandImpl.keeper.ResetOccupations;
import doyenm.zooshell.commandLine.commandImpl.keeper.UpdateOccupations;
import doyenm.zooshell.controller.keepercontroller.*;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.validator.*;
//import doyenm.zooshell.validator.function.FindingFamilyFunction;
import doyenm.zooshell.validator.predicates.KeepersNumberPredicate;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class ZooShellKeeperConfig {

    @Autowired
    Environment environment;

    @Bean
    Utils utils() {
        return new Utils();
    }

    // Predicates
    @Autowired
    KeepersNumberPredicate keeperNumbersPredicate;

    @Autowired
    StringLengthPredicates stringLengthPredicates;

    @Autowired
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates;

    // Controllers
//    @Bean
//    KeeperAddTrainingController keeperAddTrainingController(){
//        return new KeeperAddTrainingController();
//    }
    
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
        return new KeeperDetailsController(utils());
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
    KeeperUpdateOccupationsController keeperUpdateOccupationsController() {
        return new KeeperUpdateOccupationsController();
    }

    // Validators
    @Bean
    FindKeeper findKeeper(){
        return new FindKeeper();
    }
    
//    @Bean
//    FindingFamilyFunction findingFamilyFunction(){
//        return new FindingFamilyFunction();
//    }
//    
//    @Bean
//    KeeperAddTrainingValidator keeperAddTrainingValidator(){
//        return new KeeperAddTrainingValidator(findKeeper(), findingFamilyFunction());
//    }
    
    @Bean
    KeeperRenameValidator keeperRenameValidator() {
        return new KeeperRenameValidator(
                stringLengthPredicates,
                uniquenessNamesBiPredicates,
                findKeeper(),
                Integer.parseInt(environment.getProperty("keeper.name.max_length")));
    }

    @Bean
    KeeperCreationValidator keeperCreationValidator() {
        return new KeeperCreationValidator(
                stringLengthPredicates,
                uniquenessNamesBiPredicates,
                keeperNumbersPredicate,
                Integer.parseInt(environment.getProperty("keeper.name.max_length")));
    }

    @Bean
    KeeperValidator keeperValidator() {
        return new KeeperValidator();
    }

    @Bean
    KeeperUpdateOccupationsValidator keeperUpdateOccupationsValidator() {
        return new KeeperUpdateOccupationsValidator();
    }

    // Commands
//    @Bean
//    AddTraining addTraining(){
//        return new AddTraining(keeperAddTrainingValidator(), keeperAddTrainingController());
//    }
    
    @Bean
    ChangeKeeperName changeKeeperName() {
        return new ChangeKeeperName(keeperRenameValidator(), keeperRenameController());
    }

    @Bean
    CreateKeeper createKeeper() {
        return new CreateKeeper(keeperCreationValidator(), keeperCreationController());
    }

    @Bean
    DetailKeeper detailKeeper() {
        return new DetailKeeper(keeperValidator(), keeperDetailsController());
    }

    @Bean
    LsKeeper lsKeeper() {
        return new LsKeeper();
    }

    @Bean
    RemoveKeeper removeKeeper() {
        return new RemoveKeeper(keeperValidator(), keeperDeletionController());
    }

    @Bean
    ResetOccupations resetOccupations() {
        return new ResetOccupations(keeperValidator(), keeperResetOccupationsController());
    }

    @Bean
    UpdateOccupations updateOccupations() {
        return new UpdateOccupations(keeperUpdateOccupationsValidator(), keeperUpdateOccupationsController());
    }
}
