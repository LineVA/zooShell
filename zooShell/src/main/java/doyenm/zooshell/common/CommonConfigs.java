package doyenm.zooshell.common;

import doyenm.zooshell.common.function.FindingSpecieFunction;
import doyenm.zooshell.common.predicates.DoubleValuesPredicates;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class CommonConfigs {

    @Bean
    public FindAnimal findAnimal() {
        return new FindAnimal();
    }

    @Bean
    public FindingSpecieFunction findingSpecieFunction() {
        return new FindingSpecieFunction();
    }

    @Bean
    public FindPaddock findPaddock() {
        return new FindPaddock();
    }

    @Bean
    public FindHandyman findHandyman() {return new FindHandyman(); }

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


}
