package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private static final Biome BIOME = Biome.NONE;
    private static final PaddockType TYPE = PaddockType.UNKNOWN;
    private final String x;
    private final String y;
    private final String width;
    private final String height;

    private List<BusinessError> errors = new ArrayList<>();

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

    public List<Paddock> getPaddocksList() {
        return new ArrayList<>(this.getZoo().getPaddocks().values());
    }

    public Map<String, Paddock> getPaddocks() {
        return this.getZoo().getPaddocks();
    }

    public Set<String> getPaddocksNameSet() {
        return getZoo().getPaddocks().keySet();
    }

}
