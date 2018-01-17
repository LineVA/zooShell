package doyenm.zooshell.validator;

import doyenm.zooshell.validator.function.FindingBiomeFunction;
import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
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
public class PaddockValidatorsConfig {

    @Autowired
    NameValidator nameValidator;

    @Autowired
    IntegerValuePredicates integerValuePredicates;

    @Autowired
    FindingBiomeFunction findingBiomeFunction;

    @Autowired
    FindingPaddockTypeFunction findingPaddockTypeFunction;

    @Autowired
    Environment environment;

    FindPaddock findPaddock = new FindPaddock();

    @Bean
    public PaddockChangeNameValidator paddockChangeNameValidator() {
        return new PaddockChangeNameValidator(findPaddock, nameValidator);
    }

    @Bean
    public PaddockCreationValidator paddockCreationValidator() {
        return new PaddockCreationValidator(
                nameValidator,
                integerValuePredicates,
                Integer.parseInt(environment.getProperty("paddock.height.min")),
                Integer.parseInt(environment.getProperty("paddock.width.min"))
        );
    }

    @Bean
    public PaddockLocationValidator paddockLocationValidator() {
        return new PaddockLocationValidator();
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