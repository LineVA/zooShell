package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Specie;
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
public class FindingSpecieContext {

    private final Map<String, Specie> species;
    private final String specieName;
    private Specie specie;
}
