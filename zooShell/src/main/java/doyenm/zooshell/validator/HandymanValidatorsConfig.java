package doyenm.zooshell.validator;

import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.HandymenNumberPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class HandymanValidatorsConfig {

    @Autowired
    NameValidator nameValidator;

    FindHandyman findHandyman = new FindHandyman();
    FindPaddock findPaddock = new FindPaddock();

    // Validator
    @Bean
    public HandymanCreationValidator handymanCreationValidator() {
        return new HandymanCreationValidator(nameValidator,
                new HandymenNumberPredicate(0.2));
    }

    @Bean
    public HandymanValidator handymanValidator() {
        return new HandymanValidator();
    }

    @Bean
    public HandymanRenameValidator renamingValidator() {
        return new HandymanRenameValidator(nameValidator, findHandyman);
    }

    @Bean
    public HandymanUpdateOccupationsValidator handymanUpdateOccupationsValidator() {
        return new HandymanUpdateOccupationsValidator(findHandyman, findPaddock);
    }
}
