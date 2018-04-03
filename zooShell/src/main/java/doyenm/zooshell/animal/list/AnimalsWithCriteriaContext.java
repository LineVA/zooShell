package doyenm.zooshell.animal.list;

import doyenm.zooshell.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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
    
    private final Map<String, Specie> convertedSpecies;
    private Specie currentSpecie;
    private List<String> specieExpressionList;
    private String specieExpression;

    private final Map<String, ContraceptionMethod> convertedContraception;
    private ContraceptionMethod currentContraception;
    private List<String> contraceptionExpressionList;
    private String contraceptionExpression;
}
