package doyenm.zooshell.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 *
 * @author doyenm
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Paddock {

    /**
     * The name of the paddock
     */
    private String name;
    private int age;
    private double obsolescence;
    
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
     * its arrangements
     */
    private List<PaddockFacility> arrangements;
    /**
     * List of extensions
     */
    private List<Coordinates> extensions;
    
    private int turnsOfUnusableState;
    
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
