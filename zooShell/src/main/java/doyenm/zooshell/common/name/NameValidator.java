package doyenm.zooshell.common.name;

import doyenm.zooshell.common.predicates.StringLengthPredicates;
import doyenm.zooshell.common.predicates.UniquenessNamesBiPredicates;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

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
                && this.uniquenessNamesBiPredicates.test(t.getTesting().toUpperCase(), t.getExistingNames());
    }
}
