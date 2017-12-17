package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

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
