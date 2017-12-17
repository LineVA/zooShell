package doyenm.zooshell.validator.name;

import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class NameValidator implements Predicate<NameDto> {

    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final int maxLengthName;

    @Override
    public boolean test(NameDto t) {
        return this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getTesting(), maxLengthName)
                & this.uniquenessNamesBiPredicates.test(t.getTesting().toUpperCase(), t.getExistingNames());
    }
}
