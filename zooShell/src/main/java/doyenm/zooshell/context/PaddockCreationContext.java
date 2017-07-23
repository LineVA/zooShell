package doyenm.zooshell.context;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
public class PaddockCreationContext {

    private final Zoo zoo;
    private final String name;
    private final Biome biome = Biome.NONE;
    private final PaddockType type = PaddockType.UNKNOWN;
    private final String x;
    private final String y;
    private final String width;
    private final String height;

    private int convertedX;
    private int convertedY;
    private int convertedWidth;
    private int convertedHeight;

    public void convert() {
        this.convertedWidth = Integer.parseInt(this.width);
        this.convertedHeight = Integer.parseInt(this.height);
        this.convertedX = Integer.parseInt(this.x);
        this.convertedY = Integer.parseInt(this.y);
    }

    public void build() {
        Position position = Position.builder()
                .x(this.getConvertedX())
                .y(this.getConvertedY())
                .build();
        Coordinates coordinates = Coordinates.builder()
                .height(this.getConvertedHeight())
                .width(this.getConvertedWidth())
                .position(position)
                .build();
        Paddock paddock = Paddock.builder()
                .name(this.getName())
                .coordinates(coordinates)
                .extensions(new ArrayList<Coordinates>())
                .biome(biome)
                .type(type)
                .build();
        this.getZoo().getPaddocks().put(this.getName().toUpperCase(), paddock);
    }

    public Integer getWidthZoo() {
        return this.getZoo().getWidth();
    }

    public Integer getHeightZoo() {
        return this.getZoo().getHeight();
    }

    public List<Paddock> getPaddocksList() {
        return new ArrayList<>(this.getZoo().getPaddocks().values());
    }
    
    public Set<String> getPaddocksNameSet(){
        return getZoo().getPaddocks().keySet();
    }

}
