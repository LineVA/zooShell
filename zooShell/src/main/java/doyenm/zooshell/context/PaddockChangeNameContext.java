package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.Map;
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
public class PaddockChangeNameContext {

    private final Zoo zoo;
    private final String currentName;
    private final String newName;

    private Paddock convertedPaddock;

    public Map<String, Paddock> getPaddocks() {
        return getZoo().getPaddocks();
    }
}
