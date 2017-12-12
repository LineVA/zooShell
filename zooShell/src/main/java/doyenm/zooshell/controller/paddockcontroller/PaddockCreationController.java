package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Position;
import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockCreationController
        implements Function<PaddockCreationContext, PaddockCreationContext> {

    @Override
    public PaddockCreationContext apply(PaddockCreationContext t) {
        PaddockCreationContext context = t;
        Position position = Position.builder()
                .x(context.getConvertedX())
                .y(context.getConvertedY())
                .build();
        Coordinates coordinates = Coordinates.builder()
                .height(context.getConvertedHeight())
                .width(context.getConvertedWidth())
                .position(position)
                .build();
        Paddock paddock = Paddock.builder()
                .name(context.getName())
                .coordinates(coordinates)
                .extensions(new ArrayList<Coordinates>())
                .obsolescence(0.0)
                .biome(Biome.NONE)
                .type(PaddockType.UNKNOWN)
                .build();
        context.getZoo().getPaddocks().put(context.getName().toUpperCase(), paddock);
        return context;
    }

}
