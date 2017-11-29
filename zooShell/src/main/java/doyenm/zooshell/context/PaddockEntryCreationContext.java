package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import java.util.Map;
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
public class PaddockEntryCreationContext {

    private final Zoo zoo;
    private final String paddock;
    private final String x;
    private final String y;

    private int convertedX;
    private int convertedY;
    private Paddock convertedPaddock;

    public void convert() {
        this.convertedX = Integer.parseInt(this.x);
        this.convertedY = Integer.parseInt(this.y);
    }

    public Map<String, Paddock> getPaddocksMap() {
        return this.getZoo().getPaddocks();
    }

    public int getPaddockX() {
        return this.getConvertedPaddock().getCoordinates().getPosition().getX();
    }

    public int getPaddockY() {
        return this.getConvertedPaddock().getCoordinates().getPosition().getY();
    }

    public int getPaddockWidth() {
        return this.getConvertedPaddock().getCoordinates().getWidth();
    }

    public int getPaddockHeight() {
        return this.getConvertedPaddock().getCoordinates().getHeight();
    }
    
    public void build(){
        Position entry = new Position(this.getConvertedX(), this.getConvertedY());
        this.getConvertedPaddock().setEntry(entry);
        this.getZoo().getPaddocks().replace(this.getPaddock(), this.getConvertedPaddock());
    }
}
