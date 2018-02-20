package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
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
public class UpdatePaddockArrangementContext {

    private final Zoo zoo;
    private final String paddock;
    private final String arrangement;

    private Paddock convertedPaddock;
    private PaddockArrangement convertedArrangement;

}
