package doyenm.zooshell.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public enum Biome {
    /*
     The 14 biomes according to the WWF
     RAINFOREST -  Forets de feuillus humides tropicales et subtropicales - Tropical rainforest
     DRYBROADLEAF - Forets de feuillus seches tropicales et subtropicales - Tropical and subtropical dry broadleaf forests
     TROPICALCONIFEROUS - Forets de coniferes tropicales et subtropicales - Tropical and subtropical coniferous forests
     TEMPERATEBROADLEAF - Forets de feuillus et forets mixtes temperees - Temperate broadleaf and mixed forest
     TEMPERATECONIFEROUS - Forets de coniferes temperees - Temperate coniferous forest
     TAIGA - Forets boreales et taiga - Taiga
     TROPICALGRASSLAND - Prairies, savanes et brousses tropicales et subtropicales - Tropical and subtropical grasslands, savannas, and shrublands
     TEMPERATEGRASSLAND - Prairies, savanes et brousses temperees - Temperate grasslands, savannas, and shrublands
     FLOODEDGRASSLAND - Prairies et savanes inondables - Flooded grasslands and savannas
     MONTANE - Prairies et brousses d altitude - Montane grasslands and shrublands 
     TUNDRA - Toundra - Tundra
     MEDITERRANEAN - Forets, bois et maquis mediterraneens - Mediterranean forests, woodlands, and scrub
     DESERT - Deserts et brousses xeriques - Deserts and xeric shrublands
     MANGROVE - Mangroves   - Mangrove 
     */

    NONE(0),
    RAINFOREST(1),
    DRY_BROADLEAF(2),
    TROPICAL_CONIFEROUS(3),
    TEMPERATE_BROADLEAF(4),
    TEMPERATE_CONIFEROUS(5),
    TAIGA(6),
    TROPICAL_GRASSLANDS(7),
    TEMPERATE_GRASSLANDS(8),
    FLOODED_GRASSLANDS(9),
    MONTANE(10),
    TUNDRA(11),
    MEDITERRANEAN(12),
    DESERT(13),
    MANGROVE(14);

    private final int id;

    Biome(int id) {
        this.id = id;
    }

}
