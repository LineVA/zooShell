package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockFacilityContext;
import doyenm.zooshell.common.function.FindingPaddockFacilityFunction;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockFacility;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddocFacilityExistenceValidator
        implements Function<UpdatePaddockFacilityContext, UpdatePaddockFacilityContext> {

    private final FindPaddock findPaddock;
    private final FindingPaddockFacilityFunction findingPaddockFacilityFunction;

    @Override
    public UpdatePaddockFacilityContext apply(UpdatePaddockFacilityContext context) {
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        context.setConvertedPaddock(pad);
        retrieveFacilities(context);
        return context;
    }

    private void retrieveFacilities(UpdatePaddockFacilityContext context) {
        FindingPaddockFacilityContext findingPaddockFacilityContext;
        PaddockFacility convertedFacility;
        context.setConvertedFacilities(new ArrayList<>());
        for (String str : context.getFacilities()) {
            findingPaddockFacilityContext
                    = new FindingPaddockFacilityContext(str);
            convertedFacility = Stream.of(findingPaddockFacilityContext)
                    .map(findingPaddockFacilityFunction)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
            if (convertedFacility == null) {
                context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK_FACILITY));
            }
            context.getConvertedFacilities().add(convertedFacility);
        }
    }
}
