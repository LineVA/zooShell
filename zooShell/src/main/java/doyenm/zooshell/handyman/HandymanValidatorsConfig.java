package doyenm.zooshell.handyman;

import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.HandymenNumberPredicate;
import doyenm.zooshell.handyman.create.HandymanCreationValidator;
import doyenm.zooshell.handyman.occupations.HandymanUpdateOccupationsValidator;
import doyenm.zooshell.handyman.rename.HandymanRenameValidator;
import doyenm.zooshell.common.FindHandyman;
import doyenm.zooshell.common.FindPaddock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author doyenm
 */
@Configuration
public class HandymanValidatorsConfig {

    @Autowired
    Environment environment;

    @Autowired
    NameValidator nameValidator;

    @Autowired
    FindPaddock findPaddock;

    @Autowired
    FindHandyman findHandyman;

    // Validator
    @Bean
    public HandymanCreationValidator handymanCreationValidator() {
        return new HandymanCreationValidator(
                nameValidator,
                new HandymenNumberPredicate(environment.getProperty("handyman.creation.max_number_per_paddock", Double.class)));
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
        return new HandymanUpdateOccupationsValidator(
                findHandyman,
                findPaddock,
                environment.getProperty("handyman.affectation.max_number", Integer.class));
    }
}
