package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.PaddockType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FindingPaddockTypeContext {
    private final String type;
    private PaddockType convertedType;
}
