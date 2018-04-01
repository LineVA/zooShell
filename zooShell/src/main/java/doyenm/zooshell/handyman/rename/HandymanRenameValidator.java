package doyenm.zooshell.handyman.rename;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.FindHandyman;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanRenameValidator implements Predicate<HandymanRenameContext> {

    private final NameValidator nameValidator;
    private final FindHandyman findHandyman;

    @Override
    public boolean test(HandymanRenameContext t) {
        boolean result;
        result = nameValidator.test(NameDto.builder()
                .testing(t.getNewName())
                .existingNames(t.getHandymen().keySet())
                .build());
        t.setHandyman(findHandyman.find(t.getZoo(), t.getCurrentName()));
        return result && t.getHandyman()!= null;
    }

}
