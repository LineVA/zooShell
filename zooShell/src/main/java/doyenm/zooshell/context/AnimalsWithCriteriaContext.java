package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Sex;
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

    private final Map<String, Sex> convertedSexes;
    private Sex currentSex;
    private List<String> sexExpressionList;
    private String sexExpression;

    private final Map<String, Paddock> convertedPaddocks;
    private Paddock currentPaddock;
    private List<String> paddockExpressionList;
    private String paddockExpression;
}
