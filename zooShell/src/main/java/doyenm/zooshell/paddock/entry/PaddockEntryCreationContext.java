package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private List<BusinessError> errors = new ArrayList<>();

    public void convert() {
        this.convertedX = Integer.parseInt(this.x);
        this.convertedY = Integer.parseInt(this.y);
    }

}
