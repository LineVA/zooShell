package doyenm.zooshell.common.context;

import doyenm.zooshell.model.Specie;
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
public class FindingSpecieContext {

    private final Map<String, Specie> species;
    private final String specieName;
    private Specie specie;
}
