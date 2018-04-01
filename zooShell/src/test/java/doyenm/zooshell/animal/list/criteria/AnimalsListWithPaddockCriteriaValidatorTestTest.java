package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.list.LsWithCriteriaContext;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.model.Paddock;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalsListWithPaddockCriteriaValidatorTestTest {

    private AnimalsListWithPaddockCriteriaValidator subject;
    private FindPaddock findPaddock;
    private LsWithCriteriaParser parser;


    @Before
    public void setUp() {
        findPaddock = mock(FindPaddock.class);

        parser = mock(LsWithCriteriaParser.class);
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());
        when(parser.parse(anyList(), anyList())).thenReturn(new ArrayList<>());

        subject = new AnimalsListWithPaddockCriteriaValidator(parser, findPaddock);
    }

    @Test
    public void shouldReturnTrueWhenAllTheCriteriaAreOK() {
        // Given
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
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
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
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
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
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
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
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
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
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
    public void shouldReturnFalseWhenAllOfThePaddocksHaveNotBeenConverted() {
        // Given
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
        Map<String, Paddock> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphabetic(5), mock(Paddock.class));
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

    private LsWithCriteriaContext genContext(Map<String, Paddock> paddocks, List<String> paddockStr, List<String> expression) {
        LsWithCriteriaContext context = mock(LsWithCriteriaContext.class);
        when(context.getPaddocksExpression()).thenReturn(expression);
        when(context.getConvertedPaddocks()).thenReturn(paddocks);
        when(context.getPaddocks()).thenReturn(paddockStr);
        return context;
    }
}
