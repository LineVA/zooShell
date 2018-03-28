package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockChangeNameValidator
        implements Predicate<PaddockChangeNameContext> {

    private final FindPaddock findPaddock;
    private final NameValidator nameValidator;

    @Override
    public boolean test(PaddockChangeNameContext t) {
        PaddockChangeNameContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), context.getCurrentName());
        if (pad == null) {
            return false;
        }
        context.setConvertedPaddock(pad);
        return nameValidator.test(NameDto.builder()
                .testing(context.getNewName())
                .existingNames(context.getPaddocks())
                .build());
    }
}
