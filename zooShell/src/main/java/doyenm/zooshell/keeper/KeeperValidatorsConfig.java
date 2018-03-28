package doyenm.zooshell.keeper;

import doyenm.zooshell.keeper.creation.KeeperCreationValidator;
import doyenm.zooshell.keeper.rename.KeeperRenameValidator;
import doyenm.zooshell.keeper.tasks.KeeperUpdateOccupationsValidator;
import doyenm.zooshell.keeper.training.KeeperAddTrainingValidator;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.common.function.FindingFamilyFunction;
import doyenm.zooshell.common.function.FindingTaskFunction;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.DoubleValuesPredicates;
import doyenm.zooshell.common.predicates.KeepersNumberPredicate;
import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import doyenm.zooshell.validator.FindKeeper;
import doyenm.zooshell.validator.FindPaddock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
public class gitKeeperValidatorsConfig {

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
                keeperNumbersPredicate
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
