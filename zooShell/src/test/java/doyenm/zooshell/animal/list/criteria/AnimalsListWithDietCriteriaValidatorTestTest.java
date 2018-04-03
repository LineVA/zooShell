package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.common.context.FindingDietContext;
import doyenm.zooshell.common.function.FindingDietFunction;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author doyenm
 */
public class AnimalsListWithDietCriteriaValidatorTestTest {

    private AnimalsListWithDietCriteriaValidator subject;
    private FindingDietFunction findingDietFunction;
    private LsWithCriteriaParser parser;


    @Before
    public void setUp() {
        findingDietFunction = mock(FindingDietFunction.class);
        FindingDietContext context = mock(FindingDietContext.class);
        when(context.getConvertedDiet()).thenReturn(null);
        when(findingDietFunction.apply(anyObject())).thenReturn(context);

        parser = mock(LsWithCriteriaParser.class);
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());

        subject = new AnimalsListWithDietCriteriaValidator(Arrays.asList(), parser, findingDietFunction);
    }

    @Test
    public void shouldReturnTrueWhenTheAllTheDietHaveBeenConverted() {
        // Given
        LsWithCriteriaContext context = givenContext(
                new HashMap<>(),
                new ArrayList<>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheDietExpressionIsNull() {
        // Given
        LsWithCriteriaContext context = givenContext(
                new HashMap<>(),
                new ArrayList<>(),
                null);
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheDietExpressionIsEmpty() {
        // Given
        LsWithCriteriaContext context = givenContext(
                new HashMap<>(),
                new ArrayList<>(),
                new ArrayList<>());
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheConvertedDietsAreNull() {
        // Given
        LsWithCriteriaContext context = givenContext(
                null,
                new ArrayList<>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    public void shouldReturnFalseWhenSomeOfTheRequestedDietsDoNotExist() {
        // Given
        List<String> expression = new ArrayList<String>();
        expression.add(RandomStringUtils.randomAlphabetic(10));
        LsWithCriteriaContext context = givenContext(
                new HashMap<>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)),
                expression);
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    public void shouldReturnTrueWhenThereIsNoDietCriteria() {
        // Given
        LsWithCriteriaContext context = givenContext(
                new HashMap<>(),
                null,
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(Arrays.asList(), parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    private LsWithCriteriaContext givenContext(Map<String, Diet> diets, List<String> dietsStr, List<String> expression) {
        LsWithCriteriaContext context = mock(LsWithCriteriaContext.class);
        when(context.getDietsExpression()).thenReturn(expression);
        when(context.getConvertedDiets()).thenReturn(diets);
        when(context.getDiets()).thenReturn(dietsStr);
        return context;
    }

}
