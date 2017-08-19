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
//    private final List<Integer> diets;
//    private final int family;
//    private final FeedingAttributes specieFeeding;
//    private final ReproductionAttributes specieReproduction;
//    private final LifeSpanAttributes specieLifeSpan;
//    private final SocialAttributes specieSocial;
//    private final TerritoryAttributes specieTerritory;

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
//    private final int ecoregion;
//    private final int conservation;
//    private final int size;
//    private final List<Integer> continents;
//    private GaussianSocialAttributes gaussianSocialAttributes;
//    private GaussianTerritoryAttributes gaussianTerritoryAttributes;
//    private DocumentationURI documentation;
//    int breedingProgramme;
//    Tags tags;

    public Specie(Names names, Uicn uicn, BiomesSpecie biomes, DietsSpecie diets) {
        this.names = names;
        this.uicn = uicn;
        this.biomes = biomes;
        this.diets = diets;
    }
}
