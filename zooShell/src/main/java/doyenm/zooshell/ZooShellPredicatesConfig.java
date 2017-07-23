package doyenm.zooshell;

import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.validator.function.FindingBiomeFunction;
import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooShellPredicatesConfig {

    @Bean
    FindPaddock findPaddock() {
        return new FindPaddock();
    }

    @Bean
    FindingBiomeFunction findingBiomeFunction() {
        return new FindingBiomeFunction();
    }

    @Bean
    FindingPaddockTypeFunction findingPaddockTypeFunction() {
        return new FindingPaddockTypeFunction();
    }
    
    @Bean
    StringLengthPredicates stringLengthPredicates(){
        return new StringLengthPredicates();
    }
    
    @Bean
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates(){
        return new UniquenessNamesBiPredicates();
    }

    @Bean
    IntegerValuePredicates integerValuePredicates(){
        return new IntegerValuePredicates();
    }
}
