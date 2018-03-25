package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
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
        return result & t.getHandyman()!= null;
    }

}
