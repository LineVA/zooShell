package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.PaddockArrangement;
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
public class FindingPaddockArrangementContext {
    private final String arrangement;
    private PaddockArrangement convertedArrangement;
}
