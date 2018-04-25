package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private List<BusinessError> errors = new ArrayList<>();

}
