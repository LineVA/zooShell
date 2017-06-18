package doyenm.zooshell;

import doyenm.zooshell.validator.SpecieDetailsValidator;
import doyenm.zooshell.validator.ZooCreationValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooShellValidatorConfig {

    @Bean
    SpecieDetailsValidator specieDetailsValidator() {
        return new SpecieDetailsValidator();
    }

    @Bean
    ZooCreationValidator zooCreationValidator() {
        return new ZooCreationValidator();
    }
}
