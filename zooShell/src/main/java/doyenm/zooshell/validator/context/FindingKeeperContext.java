package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.AnimalKeeper;
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
public class FindingKeeperContext {
    private final Map<String, AnimalKeeper> keepers;
    private final String keeper;
    private AnimalKeeper convertedKeeper;
}
