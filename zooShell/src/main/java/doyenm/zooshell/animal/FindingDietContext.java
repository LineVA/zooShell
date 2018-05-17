package doyenm.zooshell.animal;

import doyenm.zooshell.model.Diet;
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
