package doyenm.zooshell.specie;

import doyenm.zooshell.specie.details.DetailSpecie;
import doyenm.zooshell.specie.list.LsSpecie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        SpecieValidatorsConfig.class,
        SpecieControllersConfig.class})
@Configuration
public class SpecieCommandsConfig {

    @Autowired
    SpecieValidatorsConfig validators;

    @Autowired
    SpecieControllersConfig controllers;

    @Bean
    public DetailSpecie detailSpecie() {
        return new DetailSpecie(
                validators.specieDetailsValidator(),
                controllers.specieDetailsController()
        );
    }

    @Bean
    public LsSpecie lsSpecie() {
        return new LsSpecie();
    }
}
