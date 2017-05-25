package doyenm.zooshell.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class BiomesSpecie {

     @XmlElement(name = "biome")
    List<Biome> biomes;

    public BiomesSpecie(List<Biome> biomes) {
        this.biomes = biomes;
    }

    public BiomesSpecie() {
    }
}
