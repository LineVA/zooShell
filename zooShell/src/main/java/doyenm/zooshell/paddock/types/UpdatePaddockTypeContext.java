package doyenm.zooshell.paddock.types;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Zoo;
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
public class UpdatePaddockTypeContext {

    private final Zoo zoo;
    private final String paddock;
    private final String type;

    private Paddock convertedPaddock;
    private PaddockType convertedType;

    public void build() {
        this.getConvertedPaddock().setType(this.getConvertedType());
        this.getZoo().getPaddocks().replace(this.getPaddock(), this.getConvertedPaddock());
    }
}
