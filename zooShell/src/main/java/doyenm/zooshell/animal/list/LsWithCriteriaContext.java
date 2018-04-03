package doyenm.zooshell.animal.list;

import doyenm.zooshell.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LsWithCriteriaContext {

    private final Zoo zoo;

    private List<String> dietsExpression;
    private List<String> diets = new ArrayList<>();
    private Map<String, Diet> convertedDiets = new HashMap<>();

    private List<String> sexesExpression;
    private List<String> sexes = new ArrayList<>();
    private Map<String, Sex> convertedSexes = new HashMap<>();

    private List<String> paddocksExpression;
    private List<String> paddocks = new ArrayList<>();
    private Map<String, Paddock> convertedPaddocks = new HashMap<>();

    private List<String> speciesExpression;
    private List<String> species = new ArrayList<>();
    private Map<String, Specie> convertedSpecies = new HashMap<>();

    private List<String> contraceptionExpression;
    private List<String> contraception = new ArrayList<>();
    private Map<String, ContraceptionMethod> convertedContraception = new HashMap<>();

    private List<String> animalsList = new ArrayList<>();

    public LsWithCriteriaContext(Zoo zoo,
                                 List<String> dietExpression,
                                 List<String> sexExpression,
                                 List<String> paddockExpression,
                                 List<String> specieExpression,
                                 List<String> contraceptionExpression) {
        this.zoo = zoo;
        this.dietsExpression = dietExpression;
        this.sexesExpression = sexExpression;
        this.paddocksExpression = paddockExpression;
        this.speciesExpression = specieExpression;
        this.contraceptionExpression = contraceptionExpression;
    }
}
