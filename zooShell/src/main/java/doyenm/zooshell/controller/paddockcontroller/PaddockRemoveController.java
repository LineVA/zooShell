package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveController implements Function<PaddockContext, PaddockContext> {

    @Override
    public PaddockContext apply(PaddockContext t) {
        PaddockContext context = t;
        Paddock pad = context.getConvertedPaddock();
        // Delete the paddock
        context.getZoo().getPaddocks().remove(context.getPaddock().toUpperCase());
        // Delete the occupations of the keepers in this paddock
        context.getZoo().getKeepers().values()
                .stream()
                .forEach(keeper -> {
                    List<TimedOccupation> occutpationsToDelete = keeper.getOccupations()
                            .stream()
                            .filter(occ -> occ.getPaddock().equals(pad))
                            .collect(Collectors.toList());
                    context.getZoo().getKeepers().get(keeper.getName().toUpperCase()).getOccupations().removeAll(occutpationsToDelete);
                });
        // Delete the occupations of the handymen in this paddock
        context.getZoo().getHandymen().values()
                .stream()
                .forEach(handyman -> {
                    if (handyman.getAffectations().contains(pad)) {
                        context.getZoo().getHandymen().get(handyman.getName().toUpperCase()).getAffectations().remove(pad);
                    }
                });
        return context;
    }

}
