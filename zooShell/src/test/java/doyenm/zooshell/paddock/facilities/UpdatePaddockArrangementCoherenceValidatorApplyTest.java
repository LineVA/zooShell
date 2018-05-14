package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockArrangementCoherenceValidatorApplyTest {

    private UpdatePaddockArrangementCoherenceValidator subject;

    private UpdatePaddockArrangementContext context;

    private Paddock paddock;
    private PaddockArrangement alreadyPresentFacility;
    private PaddockArrangement givenFacility;
    private boolean isAddition;
    private Zoo zoo;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
        zoo = mock(Zoo.class);

        context = mock(UpdatePaddockArrangementContext.class);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getZoo()).thenReturn(zoo);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());


        subject = new UpdatePaddockArrangementCoherenceValidator();
    }

    @Test
    public void shouldReturnFalseIfOneOfTheFacilityIsAlreadyInThePaddockAndWeTryToAddItAgain() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = alreadyPresentFacility;
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.ALREADY_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnFalseIfTheFacilityWeTryToAddIsNONE() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = PaddockArrangement.NONE;
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.ALREADY_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnTrueIfOneOfTheFacilityIsNotAlreadyInThePaddockAndWeTryToAddIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE, alreadyPresentFacility);
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseIfOneOfTheFacilityIsNotAlreadyInThePaddockAndWeTryToRemoveIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE, alreadyPresentFacility);
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.NOT_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnFalseIfTheFacilityWeTryToRemoveIsNONE() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = PaddockArrangement.NONE;
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.NOT_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnTrueIfOneOfTheFacilityIsAlreadyInThePaddockAndWeTryToRemoveIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockArrangement();
        givenFacility = alreadyPresentFacility;
        when(context.getConvertedArrangements()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
}
