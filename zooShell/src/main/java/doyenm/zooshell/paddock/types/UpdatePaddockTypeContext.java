package doyenm.zooshell.paddock.types;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
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
public class UpdatePaddockTypeContext {

    private final Zoo zoo;
    private final String paddock;
    private final String type;

    private Paddock convertedPaddock;
    private PaddockType convertedType;

    private List<BusinessError> errors = new ArrayList<>();
}
