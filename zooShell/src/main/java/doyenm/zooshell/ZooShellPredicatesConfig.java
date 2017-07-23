package doyenm.zooshell;

import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.validator.function.*;
import doyenm.zooshell.validator.predicates.*;
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
    FindingAnimalWithEntryCheckFunction findingAnimalWithEntryCheckFunction(){
        return new FindingAnimalWithEntryCheckFunction();
    }

    @Bean
    FindingBiomeFunction findingBiomeFunction() {
        return new FindingBiomeFunction();
    }
    
     @Bean
    FindingDietFunction findingDietFunction() {
        return new FindingDietFunction();
    }

    @Bean
    FindingPaddockTypeFunction findingPaddockTypeFunction() {
        return new FindingPaddockTypeFunction();
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

}
