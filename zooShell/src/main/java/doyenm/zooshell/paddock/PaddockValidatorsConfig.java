package doyenm.zooshell.paddock;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.function.FindingBiomeFunction;
import doyenm.zooshell.common.function.FindingPaddockArrangementFunction;
import doyenm.zooshell.common.function.FindingPaddockTypeFunction;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.paddock.biomes.UpdateBiomeValidator;
import doyenm.zooshell.paddock.creation.PaddockCreationValidator;
import doyenm.zooshell.paddock.creation.PaddockLocationValidator;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationValidator;
import doyenm.zooshell.paddock.extension.PaddockExtensionCreationValidator;
import doyenm.zooshell.paddock.extension.PaddockExtensionLocationValidator;
import doyenm.zooshell.paddock.facilities.UpdatePaddockArrangementValidator;
import doyenm.zooshell.paddock.remove.PaddockRemoveValidator;
import doyenm.zooshell.paddock.rename.PaddockChangeNameValidator;
import doyenm.zooshell.paddock.types.UpdatePaddockTypeValidator;
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
    FindingPaddockArrangementFunction findingPaddockArrangementFunction;
    
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
    
    @Bean
    public UpdatePaddockArrangementValidator updatePaddockArrangementValidator() {
        return new UpdatePaddockArrangementValidator(findPaddock, findingPaddockArrangementFunction);
    }

}
