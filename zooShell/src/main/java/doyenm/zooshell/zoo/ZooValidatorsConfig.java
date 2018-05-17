package doyenm.zooshell.zoo;


import doyenm.zooshell.common.CommonConfigs;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.zoo.creation.ZooCreationValidator;
import doyenm.zooshell.zoo.rename.RenameZooValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class ZooValidatorsConfig {
    @Autowired
    Environment environment;

    @Autowired
    CommonConfigs commonConfigs;

    @Bean
    NameValidator nameValidator() {
        return new NameValidator(commonConfigs.stringLengthPredicates(),
                commonConfigs.uniquenessNamesBiPredicates(),
                Integer.parseInt(environment.getProperty("zoo.name.max_length"))
        );
    }

    @Bean
    public ZooCreationValidator zooCreationValidator() {
        return new ZooCreationValidator(
                commonConfigs.integerValuePredicates(),
                nameValidator(),
                Integer.parseInt(environment.getProperty("zoo.width.min")),
                Integer.parseInt(environment.getProperty("zoo.height.min")),
                Integer.parseInt(environment.getProperty("zoo.horizon.min")),
                Integer.parseInt(environment.getProperty("zoo.speed.min")),
                Integer.parseInt(environment.getProperty("zoo.speed.max"))
        );
    }

    @Bean
    public RenameZooValidator renameZooValidator() {
        return new RenameZooValidator(nameValidator());
    }
}
