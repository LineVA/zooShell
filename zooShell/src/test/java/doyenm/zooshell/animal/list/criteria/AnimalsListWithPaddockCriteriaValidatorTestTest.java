package doyenm.zooshell.animal.list.criteria;

import doyenm.zooshell.animal.LsWithCriteriaContext;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.model.Paddock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    public void shouldReturnTrueWhenAllTheCriteriaAreOK(){
        // Given
        Paddock pad = mock(Paddock.class);
        when(findPaddock.find(any(), any())).thenReturn(pad);
        LsWithCriteriaContext context = null;
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }
}
