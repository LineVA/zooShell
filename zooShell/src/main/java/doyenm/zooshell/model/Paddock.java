package doyenm.zooshell.model;

import doyenm.zooshell.launch.options.Option;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Option option;
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
