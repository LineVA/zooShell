package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
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
public class UpdateBiomeContext {
    private final Zoo zoo;
    private final String paddock;
    private final String biome;
    
    private Paddock convertedPaddock;
    private Biome convertedBiome;
    
    public void build(){
        this.getConvertedPaddock().setBiome(this.getConvertedBiome());
        this.getZoo().getPaddocks().replace(this.getPaddock(), this.getConvertedPaddock());
    }
}
