package doyenm.zooshell.validator;

import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.KeepersNumberPredicate;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
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

    @Bean
    FindKeeper findKeeper() {
        return new FindKeeper();
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
        return new KeeperUpdateOccupationsValidator();
    }
}
