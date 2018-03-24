package doyenm.zooshell.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@EqualsAndHashCode(of={"names"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Specie {

    private Names names;
    private Uicn uicn;
    private Family family;
    private ReproductionAttributes reproductionAttributes;
    private LifespanAttributes lifespanAttributes;
    private BiomesSpecie biomes;
    private DietsSpecie diets;
    private FoodAttributes foodAttributes;
    private SocialAttributes socialAttributes;
    private TerritoryAttributes territoryAttributes;
    private SizeAttributes sizeAttributes;
    private CharacterAttributes characterAttributes;

    public Specie(Names names, Uicn uicn, BiomesSpecie biomes, DietsSpecie diets) {
        this.names = names;
        this.uicn = uicn;
        this.biomes = biomes;
        this.diets = diets;
    }
}
