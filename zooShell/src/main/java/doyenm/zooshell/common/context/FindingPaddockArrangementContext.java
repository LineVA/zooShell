package doyenm.zooshell.common.context;

import doyenm.zooshell.model.PaddockFacility;
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
    private PaddockFacility convertedArrangement;
}
