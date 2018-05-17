package doyenm.zooshell.keeper;

import doyenm.zooshell.common.function.FindingFamilyFunction;
import doyenm.zooshell.handyman.FindingTaskFunction;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.DoubleValuesPredicates;
import doyenm.zooshell.keeper.creation.NumberOfKeepersPredicates;
import doyenm.zooshell.keeper.creation.KeeperCreationValidator;
import doyenm.zooshell.keeper.creation.PresenceOfAnimalsPredicate;
import doyenm.zooshell.keeper.rename.KeeperRenameValidator;
import doyenm.zooshell.keeper.tasks.KeeperUpdateOccupationsValidator;
import doyenm.zooshell.keeper.training.KeeperAddTrainingValidator;
import doyenm.zooshell.common.FindKeeper;
import doyenm.zooshell.common.FindPaddock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
public class KeeperValidatorsConfig {

    @Autowired
    Environment environment;

    // Predicates
    @Bean
    NumberOfKeepersPredicates keeperNumbersPredicates(){
        return new NumberOfKeepersPredicates();
    }

    @Bean
    PresenceOfAnimalsPredicate presenceOfAnimalsPredicate(){
        return new PresenceOfAnimalsPredicate();
    }

    @Autowired
    NameValidator nameValidator;

    @Autowired
    FindPaddock findPaddock;

    @Bean
    FindKeeper findKeeper() {
        return new FindKeeper();
    }

    @Bean
    FindingFamilyFunction findingFamilyFunction() {
        return new FindingFamilyFunction();
    }

    @Bean
    FindingTaskFunction findingTaskFunction() {
        return new FindingTaskFunction();
    }

    @Bean
    public KeeperRenameValidator keeperRenameValidator() {
        return new KeeperRenameValidator(
                nameValidator,
                findKeeper());
    }

    @Bean
    public KeeperCreationValidator keeperCreationValidator() {
        return new KeeperCreationValidator(
                nameValidator,
                keeperNumbersPredicates(),
                presenceOfAnimalsPredicate()
        );
    }

    @Bean
    public KeeperValidator keeperValidator() {
        return new KeeperValidator();
    }

    @Bean
    public KeeperUpdateOccupationsValidator keeperUpdateOccupationsValidator() {
        int maxTurnsInUnusabeState =  environment.getProperty("paddock.obsolescence.max_number_of_turn_when_unusable", Integer.class);
        return new KeeperUpdateOccupationsValidator(
                findKeeper(),
                findPaddock,
                findingTaskFunction(),
                new DoubleValuesPredicates(),
                maxTurnsInUnusabeState
        );
    }

    @Bean
    public KeeperAddTrainingValidator keeperAddTrainingValidator() {
        return new KeeperAddTrainingValidator(findKeeper(), findingFamilyFunction());
    }
}
