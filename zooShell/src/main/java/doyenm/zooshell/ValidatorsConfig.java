package doyenm.zooshell;

import doyenm.zooshell.common.FindAnimal;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.function.*;
import doyenm.zooshell.common.predicates.DoubleValuesPredicates;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import doyenm.zooshell.specie.details.SpecieDetailsValidator;
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
    FindingPaddockFacilityFunction findingPaddockArrangementFunction() {
        return new FindingPaddockFacilityFunction();
    }

    @Bean
    FindPaddock findPaddock() {
        return new FindPaddock();
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
