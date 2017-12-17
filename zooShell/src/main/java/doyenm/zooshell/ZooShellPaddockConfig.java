package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.ls.LsBiome;
import doyenm.zooshell.commandLine.commandImpl.ls.LsPaddockType;
import doyenm.zooshell.commandLine.commandImpl.paddock.*;
import doyenm.zooshell.controller.paddockcontroller.*;
import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockEntryCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import doyenm.zooshell.validator.PaddockRemoveValidator;
import doyenm.zooshell.validator.PaddockValidator;
import doyenm.zooshell.validator.UpdateBiomeValidator;
import doyenm.zooshell.validator.UpdatePaddockTypeValidator;
import doyenm.zooshell.validator.function.FindingBiomeFunction;
import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({ZooShellPredicatesConfig.class, PaddockControllerConfig.class})
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class ZooShellPaddockConfig {

    @Autowired
    Environment environment;
    
    // Predicates
    @Autowired
    FindPaddock findPaddock;

    @Autowired
    FindingBiomeFunction findingBiomeFunction;

    @Autowired
    FindingPaddockTypeFunction findingPaddockTypeFunction;

    @Autowired
    StringLengthPredicates stringLengthPredicates;

    @Autowired
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates;

    @Autowired
    IntegerValuePredicates integerValuePredicates;

    // Validators
    @Bean
    public PaddockChangeNameValidator paddockChangeNameValidator() {
        return new PaddockChangeNameValidator(
                findPaddock,
                stringLengthPredicates,
                uniquenessNamesBiPredicates,
                Integer.parseInt(environment.getProperty("paddock.name.max_length")));
    }

    @Bean
    public PaddockCreationValidator paddockCreationValidator() {
        return new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                Integer.parseInt(environment.getProperty("paddock.name.max_length")),
                Integer.parseInt(environment.getProperty("paddock.height.min")),
                Integer.parseInt(environment.getProperty("paddock.width.min"))
        );
    }

    @Bean
    public PaddockEntryCreationValidator paddockEntryCreationValidator() {
        return new PaddockEntryCreationValidator(findPaddock);
    }

    @Bean
    public PaddockExtensionCreationValidator paddockExtensionCreationValidator() {
        return new PaddockExtensionCreationValidator(findPaddock);
    }

    @Bean
    public PaddockExtensionLocationValidator paddockExtensionLocationValidator() {
        return new PaddockExtensionLocationValidator();
    }

    @Bean
    public PaddockLocationValidator paddockLocationValidator() {
        return new PaddockLocationValidator();
    }

    @Bean
    public PaddockRemoveValidator paddockRemoveValidator() {
        return new PaddockRemoveValidator(findPaddock);
    }

    @Bean
    public PaddockValidator paddockValidator() {
        return new PaddockValidator(findPaddock);
    }

    @Bean
    public UpdateBiomeValidator updateBiomeValidator() {
        return new UpdateBiomeValidator(findPaddock, findingBiomeFunction);
    }

    @Bean
    public UpdatePaddockTypeValidator updatePaddockTypeValidator() {
        return new UpdatePaddockTypeValidator(findPaddock, findingPaddockTypeFunction);
    }

}
