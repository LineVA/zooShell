package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Position;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionCreationController
        implements Function<PaddockExtensionCreationContext, PaddockExtensionCreationContext> {

    @Override
    public PaddockExtensionCreationContext apply(PaddockExtensionCreationContext t) {
        Position position = Position.builder()
                .x(t.getConvertedX())
                .y(t.getConvertedY())
                .build();
        Coordinates coordinates = Coordinates.builder()
                .height(t.getConvertedHeight())
                .width(t.getConvertedWidth())
                .position(position)
                .build();
        t.getConvertedPaddock().getExtensions().add(coordinates);
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }

}
