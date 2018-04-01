package doyenm.zooshell.common.context;

import doyenm.zooshell.model.Biome;
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
public class FindingBiomeContext {

    private final String biome;
    private Biome convertedBiome;
}
