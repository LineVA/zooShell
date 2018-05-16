package doyenm.zooshell.common;

import doyenm.zooshell.common.function.*;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.DoubleValuesPredicates;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class CommonConfigs {

    @Autowired
    Environment environment;

    @Bean
    public FindAnimal findAnimal() {
        return new FindAnimal();
    }

    @Bean
    public FindingDietFunction findingDietFunction() {
        return new FindingDietFunction();
    }

    @Bean
    public FindingSexFunction findingSexFunction() {
        return new FindingSexFunction();
    }

    @Bean
    public FindingSpecieFunction findingSpecieFunction() {
        return new FindingSpecieFunction();
    }

    @Bean
    public DoubleValuesPredicates doubleValuesPredicates() {
        return new DoubleValuesPredicates();
    }

    @Bean
    public IntegerValuePredicates integerValuePredicates() {
        return new IntegerValuePredicates();
    }

    @Bean
    public StringLengthPredicates stringLengthPredicates() {
        return new StringLengthPredicates();
    }

    @Bean
    public UniquenessNamesBiPredicates uniquenessNamesBiPredicates() {
        return new UniquenessNamesBiPredicates();
    }


    @Bean
    public NameValidator animalNameValidator() {
        return new NameValidator(
                stringLengthPredicates(),
                uniquenessNamesBiPredicates(),
                Integer.parseInt(environment.getProperty("animal.name.max_length"))
        );
    }

    @Bean
    NameValidator zooNameValidator() {
        return new NameValidator(stringLengthPredicates(),
                uniquenessNamesBiPredicates(),
                Integer.parseInt(environment.getProperty("zoo.name.max_length"))
        );
    }

    @Bean
    NameValidator paddockNameValidator() {
        return new NameValidator(stringLengthPredicates(),
                uniquenessNamesBiPredicates(),
                Integer.parseInt(environment.getProperty("paddock.name.max_length"))
        );
    }

    @Bean
    public FindingBiomeFunction findingBiomeFunction() {
        return new FindingBiomeFunction();
    }

    @Bean
    public FindingPaddockTypeFunction findingPaddockTypeFunction(){
        return new FindingPaddockTypeFunction();
    }

    @Bean
    public FindingPaddockFacilityFunction findingPaddockFacilityFunction(){
        return new FindingPaddockFacilityFunction();
    }

    @Bean
    public FindPaddock findPaddock() {
        return new FindPaddock();
    }
}
