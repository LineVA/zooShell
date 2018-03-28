package doyenm.zooshell.validator;

import doyenm.zooshell.common.function.*;
import doyenm.zooshell.common.predicates.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author doyenm
 */
@Configuration
public class ValidatorsConfig {

    @Bean
    FindAnimal findAnimal() {
        return new FindAnimal();
    }

    @Bean
    FindingAnimalWithEntryCheckFunction findingAnimalWithEntryCheckFunction() {
        return new FindingAnimalWithEntryCheckFunction();
    }

    @Bean
    FindingBiomeFunction findingBiomeFunction() {
        return new FindingBiomeFunction();
    }

    @Bean
    FindingSpecieFunction findingSpecieFunction() {
        return new FindingSpecieFunction();
    }

    @Bean
    FindingDietFunction findingDietFunction() {
        return new FindingDietFunction();
    }

    @Bean
    FindingSexFunction findingSexFunction() {
        return new FindingSexFunction();
    }

    @Bean
    FindingPaddockTypeFunction findingPaddockTypeFunction() {
        return new FindingPaddockTypeFunction();
    }
    
      @Bean
    FindingPaddockArrangementFunction findingPaddockArrangementFunction() {
        return new FindingPaddockArrangementFunction();
    }

    @Bean
    FindPaddock findPaddock() {
        return new FindPaddock();
    }

    @Bean
    KeepersNumberPredicate keeperNumbersPredicate() {
        return new KeepersNumberPredicate();
    }

    @Bean
    DoubleValuesPredicates doubleValuesPredicates() {
        return new DoubleValuesPredicates();
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
    public SpecieDetailsValidator specieDetailsValidator() {
        return new SpecieDetailsValidator();
    }
}
