package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.model.*;

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
                .extensions(new ArrayList<>())
                .obsolescence(0.0)
                .biome(Biome.NONE)
                .type(PaddockType.UNKNOWN)
                .turnsOfUnusableState(0)
                .build();
        context.getZoo().getPaddocks().put(context.getName().toUpperCase(), paddock);
        return context;
    }

}
