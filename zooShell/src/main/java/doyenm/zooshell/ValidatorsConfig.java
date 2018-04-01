package doyenm.zooshell;

import doyenm.zooshell.specie.SpecieDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author doyenm
 */
@Configuration
public class ValidatorsConfig {

    @Bean
    public SpecieDetailsValidator specieDetailsValidator() {
        return new SpecieDetailsValidator();
    }
}
