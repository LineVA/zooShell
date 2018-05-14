package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.PaddockArrangement;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockArrangementCoherenceValidator
        implements Function<UpdatePaddockArrangementContext, UpdatePaddockArrangementContext> {

    @Override
    public UpdatePaddockArrangementContext apply(UpdatePaddockArrangementContext context) {
        checkWhenTryingToRemoveAFacility(context);
        checkWhenTryingToAddAFacility(context);
        return context;
    }

    private void checkWhenTryingToRemoveAFacility(UpdatePaddockArrangementContext context) {
        if (!context.isAddingFacilities()) {
            context.getConvertedArrangements()
                    .stream()
                    .filter(f -> !context.getConvertedPaddock().getArrangements().contains(f)
                            || PaddockArrangement.NONE == f)
                    .forEach(f -> context.getErrors().add(new BusinessError(ErrorType.NOT_PRESENT_FACILITY)));
        }
    }

    private void checkWhenTryingToAddAFacility(UpdatePaddockArrangementContext context) {
        if (context.isAddingFacilities()) {
            context.getConvertedArrangements()
                    .stream()
                    .filter(f -> context.getConvertedPaddock().getArrangements().contains(f)
                            || PaddockArrangement.NONE == f)
                    .forEach(f -> context.getErrors().add(new BusinessError(ErrorType.ALREADY_PRESENT_FACILITY)));
        }
    }
}
