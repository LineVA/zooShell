package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PaddockExtensionCreationContext {

    private final Zoo zoo;
    private final String paddock;
    private final String x;
    private final String y;
    private final String width;
    private final String height;

    private List<BusinessError> errors = new ArrayList<>();

    private Paddock convertedPaddock;
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
        this.getConvertedPaddock().getExtensions().add(coordinates);
        this.getZoo().getPaddocks().replace(this.getPaddock(), this.getConvertedPaddock());
    }
    
    public List<Coordinates> getCoordinatesListExceptYours(){
        Collection<Paddock> paddocksCollection = this.getZoo().getPaddocks().values();
        paddocksCollection.remove(this.getConvertedPaddock());
        List<Coordinates> coordinates =new ArrayList();
        for(Paddock pad : paddocksCollection){
            coordinates.add(pad.getCoordinates());
            coordinates.addAll(pad.getExtensions());
        }
        return coordinates;
    }
    
    public List<Coordinates> getYourCoordinatesList(){
        List<Coordinates> coordinates =new ArrayList();
        coordinates.add(this.getConvertedPaddock().getCoordinates());
        coordinates.addAll(this.getConvertedPaddock().getExtensions());
        return coordinates;
    }

}
