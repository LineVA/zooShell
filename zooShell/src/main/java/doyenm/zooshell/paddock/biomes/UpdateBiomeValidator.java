package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.FindingBiomeContext;
import doyenm.zooshell.paddock.FindingBiomeFunction;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateBiomeValidator
        implements Function<UpdateBiomeContext, UpdateBiomeContext> {

    private final FindPaddock findPaddock;
    private final FindingBiomeFunction findingBiomeFunction;

    @Override
    public UpdateBiomeContext apply(UpdateBiomeContext t) {
        UpdateBiomeContext context = t;
        FindingBiomeContext findingBiomeContext = new FindingBiomeContext(context.getBiome());
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        context.setConvertedPaddock(pad);
        Biome convertedBiome = Stream.of(findingBiomeContext)
                .map(findingBiomeFunction)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        if (convertedBiome == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_BIOME));
        } else {
            context.setConvertedBiome(convertedBiome);
        }
        return context;
    }
}
