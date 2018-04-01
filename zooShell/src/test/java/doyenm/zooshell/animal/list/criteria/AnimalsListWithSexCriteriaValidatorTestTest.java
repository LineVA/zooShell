package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.context.FindingSexContext;
import doyenm.zooshell.common.function.FindingSexFunction;
import doyenm.zooshell.model.Sex;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalsListWithSexCriteriaValidatorTestTest {

    private AnimalsListWithSexCriteriaValidator subject;
    private FindingSexFunction findingSexFunction;
    private LsWithCriteriaParser parser;


    @Before
    public void setUp() {
        findingSexFunction = mock(FindingSexFunction.class);
        FindingSexContext context = mock(FindingSexContext.class);
        when(context.getSex()).thenReturn(null);
        when(findingSexFunction.apply(anyObject())).thenReturn(context);

        parser = mock(LsWithCriteriaParser.class);
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());

        subject = new AnimalsListWithSexCriteriaValidator(parser, findingSexFunction);
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
    public void shouldReturnFalseWhenAllOfTheSexesHaveNotBeenConverted() {
        // Given
        Map<String, Sex> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphabetic(5), Sex.UNKNOWN);
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

    private LsWithCriteriaContext genContext(Map<String, Sex> sexes, List<String> sexStr, List<String> expression) {
        LsWithCriteriaContext context = mock(LsWithCriteriaContext.class);
        when(context.getSexesExpression()).thenReturn(expression);
        when(context.getConvertedSexes()).thenReturn(sexes);
        when(context.getSexes()).thenReturn(sexStr);
        return context;
    }
}
