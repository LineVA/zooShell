package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PaddockChangeNameContext {

    private final Zoo zoo;
    private final String currentName;
    private final String newName;

    private Paddock convertedPaddock;

    private List<BusinessError> errors = new ArrayList<>();

    public Set<String> getPaddocks() {
        return getZoo().getPaddocks().keySet();
    }
}
