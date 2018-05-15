package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.PaddockFacility;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockFacilityCoherenceValidator
        implements Function<UpdatePaddockFacilityContext, UpdatePaddockFacilityContext> {

    @Override
    public UpdatePaddockFacilityContext apply(UpdatePaddockFacilityContext context) {
        checkWhenTryingToRemoveAFacility(context);
        checkWhenTryingToAddAFacility(context);
        return context;
    }

    private void checkWhenTryingToRemoveAFacility(UpdatePaddockFacilityContext context) {
        if (!context.isAddingFacilities()) {
            context.getConvertedFacilities()
                    .stream()
                    .filter(f -> !context.getConvertedPaddock().getFacilities().contains(f)
                            || PaddockFacility.NONE == f)
                    .forEach(f -> context.getErrors().add(new BusinessError(ErrorType.NOT_PRESENT_FACILITY)));
        }
    }

    private void checkWhenTryingToAddAFacility(UpdatePaddockFacilityContext context) {
        if (context.isAddingFacilities()) {
            context.getConvertedFacilities()
                    .stream()
                    .filter(f -> context.getConvertedPaddock().getFacilities().contains(f)
                            || PaddockFacility.NONE == f)
                    .forEach(f -> context.getErrors().add(new BusinessError(ErrorType.ALREADY_PRESENT_FACILITY)));
        }
    }
}
