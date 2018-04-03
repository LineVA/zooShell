package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.context.FindingSpecieContext;
import doyenm.zooshell.common.function.FindingSpecieFunction;
import doyenm.zooshell.model.Specie;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalsListWithSpecieCriteriaValidatorTestTest {

    private AnimalsListWithSpecieCriteriaValidator subject;
    private FindingSpecieFunction findingSpecieFunction;
    private LsWithCriteriaParser parser;


    @Before
    public void setUp() {
        findingSpecieFunction = mock(FindingSpecieFunction.class);
        FindingSpecieContext context = mock(FindingSpecieContext.class);
        when(context.getSpecie()).thenReturn(null);
        when(findingSpecieFunction.apply(anyObject())).thenReturn(context);

        parser = mock(LsWithCriteriaParser.class);
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());

        subject = new AnimalsListWithSpecieCriteriaValidator(Arrays.asList(), parser, findingSpecieFunction);
    }

    @Test
    public void shouldReturnTrueWhenAllTheCriteriaAreOK() {
        // Given
        LsWithCriteriaContext context = genContext(
                new HashMap<>(),
                new ArrayList<>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10))
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheExpressionIsNull() {
        // Given
        LsWithCriteriaContext context = genContext(
                new HashMap<>(),
                new ArrayList<>(),
                null
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheExpressionIsEmpty() {
        // Given
        LsWithCriteriaContext context = genContext(
                new HashMap<>(),
                new ArrayList<>(),
                Arrays.asList()
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheListOfPaddocksNamesIsNull() {
        // Given
        LsWithCriteriaContext context = genContext(
                new HashMap<>(),
                null,
                Arrays.asList(RandomStringUtils.randomAlphabetic(10))
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheMapOfPaddocksIsNull() {
        // Given
        LsWithCriteriaContext context = genContext(
                null,
                new ArrayList<>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10))
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenAllOfTheSpeciesHaveNotBeenConverted() {
        // Given
        Map<String, Specie> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphabetic(5), mock(Specie.class));
        LsWithCriteriaContext context = genContext(
                map,
                Arrays.asList(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10))
        );
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    private LsWithCriteriaContext genContext(Map<String, Specie> species, List<String> specieStr, List<String> expression) {
        LsWithCriteriaContext context = mock(LsWithCriteriaContext.class);
        when(context.getSpeciesExpression()).thenReturn(expression);
        when(context.getConvertedSpecies()).thenReturn(species);
        when(context.getSpecies()).thenReturn(specieStr);
        return context;
    }
}
