package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Builder
@Getter
@Setter
public class AnimalsWithCriteriaContext {

    private final Animal animal;
    private final Map<String, Diet> convertedDiets;
    private Diet currentDiet;
    private List<String> dietExpressionList;
    private String dietExpression;
}
