package doyenm.zooshell.zoo.rename;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.zoo.creation.ZooCreationContext;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.function.Function;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RenameZooValidator implements Function<ZooContext, ZooContext> {

    private final NameValidator nameValidator;

    @Override
    public ZooContext apply(ZooContext t) {
        NameDto dto = NameDto.builder()
                .testing(t.getSaveName())
                .existingNames(new HashSet<>())
                .build();
        boolean result = nameValidator.test(dto);
        if (!result) {
            t.getErrors().add(new BusinessError(ErrorType.INCORRECT_NAME));
        }
        return t;
    }
}
