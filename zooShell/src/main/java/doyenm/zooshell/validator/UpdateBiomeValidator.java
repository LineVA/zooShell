package doyenm.zooshell.validator;

import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.context.UpdateBiomeContext;
import doyenm.zooshell.validator.context.FindingBiomeContext;
import doyenm.zooshell.validator.function.FindingBiomeFunction;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class UpdateBiomeValidator implements Predicate<UpdateBiomeContext> {

    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();
    FindingBiomeFunction findingBiomeFunction = new FindingBiomeFunction();

    @Override
    public boolean test(UpdateBiomeContext t) {
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(t.getZoo().getPaddocks(), t.getPaddock());
        FindingBiomeContext findingBiomeContext = new FindingBiomeContext(t.getBiome());
        t.setConvertedPaddock(Stream.of(findingPaddockContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        t.setConvertedBiome(Stream.of(findingBiomeContext)
                .map(findingBiomeFunction)
                .findFirst()
                .get()
                .getConvertedBiome());
        if(t.getConvertedPaddock() != null && t.getConvertedBiome() != null){
            return t.getConvertedPaddock().getEntry() != null;
        } 
        return false;
    }
}
