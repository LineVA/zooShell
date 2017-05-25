package doyenm.zooshell.model;

import doyenm.zooshell.launch.options.Option;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Builder
@Getter
@Setter
public class Paddock {

    private Option option;
    /**
     * The name of the paddock
     */
    private String name;
    /**
     * Its coordinates
     */
    private Coordinates coordinates;
    private Position entry;

    /**
     * Its biome
     */
    private Biome biome;
    /**
     * Its type
     */
    private PaddockType type;
    /**
     * List of extensions
     */
    private List<Coordinates> extensions;
    
//    @Getter
//    Map<String, Animal> animals;
//    @Getter
//    private int paddockType;
//    private List<IPaddock> neightbourhood;

    public int getX() {
        return this.getCoordinates().getPosition().getX();
    }

    public int getY() {
        return this.getCoordinates().getPosition().getY();
    }

    public int getWidth() {
        return this.getCoordinates().getWidth();
    }

    public int getHeight() {
        return this.getCoordinates().getHeight();
    }
}
