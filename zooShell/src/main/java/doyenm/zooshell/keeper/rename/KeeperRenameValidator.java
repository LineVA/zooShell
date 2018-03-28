package doyenm.zooshell.keeper.rename;

import doyenm.zooshell.keeper.rename.KeeperRenameContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.validator.FindKeeper;
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
        return result && t.getConvertedKeeper() != null;
    }

}
