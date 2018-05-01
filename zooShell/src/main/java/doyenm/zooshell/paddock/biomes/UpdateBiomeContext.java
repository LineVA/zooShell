package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
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
public class UpdateBiomeContext {
    private final Zoo zoo;
    private final String paddock;
    private final String biome;
    
    private Paddock convertedPaddock;
    private Biome convertedBiome;

    private List<BusinessError> errors = new ArrayList<>();
}
