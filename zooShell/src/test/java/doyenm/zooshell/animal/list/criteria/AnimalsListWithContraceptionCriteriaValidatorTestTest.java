package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.animal.contraception.FindingContraceptionContext;
import doyenm.zooshell.animal.contraception.FindingContraceptionFunction;
import doyenm.zooshell.model.ContraceptionMethod;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalsListWithContraceptionCriteriaValidatorTestTest {

    private AnimalsListWithContraceptionCriteriaValidator subject;
    private FindingContraceptionFunction findingContraceptionFunction;
    private LsWithCriteriaParser parser;


    @Before
    public void setUp() {
        findingContraceptionFunction = mock(FindingContraceptionFunction.class);
        FindingContraceptionContext context = mock(FindingContraceptionContext.class);
        when(context.getContraception()).thenReturn(null);
        when(findingContraceptionFunction.apply(anyObject())).thenReturn(context);

        parser = mock(LsWithCriteriaParser.class);
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());

        subject = new AnimalsListWithContraceptionCriteriaValidator(Arrays.asList(), parser, findingContraceptionFunction);
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
    public void shouldReturnFalseWhenAllOfTheMethodsHaveNotBeenConverted() {
        // Given
        Map<String, ContraceptionMethod> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphabetic(5), ContraceptionMethod.NONE);
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

    private LsWithCriteriaContext genContext(Map<String, ContraceptionMethod> methods,
                                             List<String> contraceptionStr,
                                             List<String> expression) {
        LsWithCriteriaContext context = mock(LsWithCriteriaContext.class);
        when(context.getContraceptionExpression()).thenReturn(expression);
        when(context.getConvertedContraception()).thenReturn(methods);
        when(context.getContraception()).thenReturn(contraceptionStr);
        return context;
    }
}
