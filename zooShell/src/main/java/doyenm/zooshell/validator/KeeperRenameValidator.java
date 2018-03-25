package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperRenameValidator implements Predicate<KeeperRenameContext> {

    private final NameValidator nameValidator;
    private final FindKeeper findKeeper;

    @Override
    public boolean test(KeeperRenameContext t) {
        boolean result;
        result = nameValidator.test(NameDto.builder()
                .testing(t.getNewKeeperName())
                .existingNames(t.getKeepers().keySet())
                .build());
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        return result & t.getConvertedKeeper() != null;
    }

}
