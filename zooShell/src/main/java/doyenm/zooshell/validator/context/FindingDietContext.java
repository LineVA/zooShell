package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Diet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class FindingDietContext {
    private final String diet;
    private Diet convertedDiet;
}
