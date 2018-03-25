package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

/**
 *
 * @author doyenm
 */
public class AnimalsListWithDietCriteriaValidatorTestTest {

    private LsWithCriteriaContext givenContext(Map<String, Diet> diets, List<String> dietsStr, List<String> expression) {
        LsWithCriteriaContext context = Mockito.mock(LsWithCriteriaContext.class);
        Mockito.when(context.getDietsExpression()).thenReturn(expression);
        Mockito.when(context.getConvertedDiets()).thenReturn(diets);
        Mockito.when(context.getDiets()).thenReturn(dietsStr);
        return context;
    }

    private FindingDietFunction givenFindingDiet() {
        FindingDietFunction mock = Mockito.mock(FindingDietFunction.class);
        FindingDietContext context = Mockito.mock(FindingDietContext.class);
        Mockito.when(context.getConvertedDiet()).thenReturn(null);
        Mockito.when(mock.apply(Mockito.anyObject())).thenReturn(context);
        return mock;
    }

    private LsWithCriteriaParser givenParser() {
        LsWithCriteriaParser mock = Mockito.mock(LsWithCriteriaParser.class);
        Mockito.when(mock.parse(Mockito.anyList(), Mockito.anyList())).thenReturn(new ArrayList<>());
        Mockito.when(mock.parse(Mockito.anyList(), Mockito.anyList())).thenReturn(new ArrayList<>());
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheAllTheDietHaveBeenConverted() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaParser parser = givenParser();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                new ArrayList<String>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheDietExpressionIsNull() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaParser parser = givenParser();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                new ArrayList<String>(),
                null);
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheDietExpressionIsEmpty() {
        // Given
        LsWithCriteriaParser parser = givenParser();
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                new ArrayList<String>(),
                new ArrayList<>());
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheConvertedDietsAreNull() {
        // Given
        LsWithCriteriaParser parser = givenParser();
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                null,
                new ArrayList<String>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    public void shouldReturnFalseWhenSomeOfTheRequestedDietsDoNotExist() {
        // Given
        LsWithCriteriaParser parser = givenParser();
        FindingDietFunction findingDietFunction = givenFindingDiet();
        List<String> expression = new ArrayList<String>();
        expression.add(RandomStringUtils.randomAlphabetic(10));
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)),
                expression);
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    public void shouldReturnTrueWhenThereIsNoDietCriteria() {
        // Given
        LsWithCriteriaParser parser = givenParser();
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                null,
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(parser, findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }
}
