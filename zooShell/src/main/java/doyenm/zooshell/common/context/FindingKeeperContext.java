package doyenm.zooshell.common.context;

import doyenm.zooshell.model.AnimalKeeper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

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
