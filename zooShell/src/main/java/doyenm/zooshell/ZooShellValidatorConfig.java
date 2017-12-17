package doyenm.zooshell;

import doyenm.zooshell.validator.SpecieDetailsValidator;
import doyenm.zooshell.validator.ZooCreationValidator;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
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
public class ZooShellValidatorConfig {

    @Autowired
    Environment environment;

    @Bean
    SpecieDetailsValidator specieDetailsValidator() {
        return new SpecieDetailsValidator();
    }

    @Bean
    IntegerValuePredicates integerValuePredicates() {
        return new IntegerValuePredicates();
    }

    @Bean
    StringLengthPredicates stringLengthPredicates() {
        return new StringLengthPredicates();
    }

    @Bean
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates() {
        return new UniquenessNamesBiPredicates();
    }

    @Bean
    NameValidator nameValidator() {
        return new NameValidator(stringLengthPredicates(),
                uniquenessNamesBiPredicates(),
                Integer.parseInt(environment.getProperty("zoo.name.max_length"))
        );
    }

    @Bean
    ZooCreationValidator zooCreationValidator() {
        return new ZooCreationValidator(
                integerValuePredicates(),
                nameValidator(),
                Integer.parseInt(environment.getProperty("zoo.width.min")),
                Integer.parseInt(environment.getProperty("zoo.height.min")),
                Integer.parseInt(environment.getProperty("zoo.horizon.min")),
                Integer.parseInt(environment.getProperty("zoo.speed.min")),
                Integer.parseInt(environment.getProperty("zoo.speed.max"))
        );
    }
}
