package doyenm.zooshell.validator.context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class OverlapContext {
    private final int firstValue;
    private final int firstSize;
    private final int secondValue;
    private final int secondSize;
}
