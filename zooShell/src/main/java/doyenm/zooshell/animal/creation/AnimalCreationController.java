package doyenm.zooshell.animal.creation;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalCreationController implements Function<AnimalCreationContext, AnimalCreationContext>{

    @Override
    public AnimalCreationContext apply(AnimalCreationContext t) {
        t.build();
        return t;
    }

}
