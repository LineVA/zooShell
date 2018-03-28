package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.paddock.biomes.UpdateBiomeContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.common.context.FindingBiomeContext;
import doyenm.zooshell.common.function.FindingBiomeFunction;
import doyenm.zooshell.validator.FindPaddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateBiomeValidator implements Predicate<UpdateBiomeContext> {

    private final FindPaddock findPaddock;
    private final FindingBiomeFunction findingBiomeFunction;

    @Override
    public boolean test(UpdateBiomeContext t) {
        UpdateBiomeContext context = t;
        FindingBiomeContext findingBiomeContext = new FindingBiomeContext(context.getBiome());
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            return false;
        }
        context.setConvertedPaddock(pad);
        context.setConvertedBiome(Stream.of(findingBiomeContext)
                .map(findingBiomeFunction)
                .findFirst()
                .orElseGet(null)
                .getConvertedBiome());
       return context.getConvertedBiome() != null;
    }
}
