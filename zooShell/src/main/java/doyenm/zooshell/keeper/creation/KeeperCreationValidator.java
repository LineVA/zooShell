package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperCreationValidator implements Predicate<KeeperCreationContext> {

    private final NameValidator nameValidator;
    private final KeeperCreationPredicates keeperCreationPredicates;

    @Override
    public boolean test(KeeperCreationContext t) {
        NameDto dto = NameDto.builder()
                .testing(t.getKeeper())
                .existingNames(t.getKeepers().keySet())
                .build();

        return Stream.of(t)
                .filter(keeperCreationPredicates::test)
                .filter(i -> nameValidator.test(dto))
                .anyMatch(i -> true);
    }

}
