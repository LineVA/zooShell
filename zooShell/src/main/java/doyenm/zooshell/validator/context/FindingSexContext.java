package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Sex;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class FindingSexContext {

    private final String sexName;
    @Setter
    private Sex sex;
}
