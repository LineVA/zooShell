package doyenm.zooshell.specie;

import doyenm.zooshell.specie.details.SpecieDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecieValidatorsConfig {

    @Bean
    public SpecieDetailsValidator specieDetailsValidator() {
        return new SpecieDetailsValidator();
    }
}
