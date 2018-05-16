package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockFacility;
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
public class UpdatePaddockFacilityCoherenceValidatorApplyTest {

    private UpdatePaddockFacilityCoherenceValidator subject;

    private UpdatePaddockFacilityContext context;

    private Paddock paddock;
    private PaddockFacility alreadyPresentFacility;
    private PaddockFacility givenFacility;
    private Zoo zoo;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
        zoo = mock(Zoo.class);

        context = mock(UpdatePaddockFacilityContext.class);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getZoo()).thenReturn(zoo);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());


        subject = new UpdatePaddockFacilityCoherenceValidator();
    }

    @Test
    public void shouldReturnFalseIfOneOfTheFacilityIsAlreadyInThePaddockAndWeTryToAddItAgain() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = alreadyPresentFacility;
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.ALREADY_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnFalseIfTheFacilityWeTryToAddIsNONE() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = PaddockFacility.NONE;
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.ALREADY_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnTrueIfOneOfTheFacilityIsNotAlreadyInThePaddockAndWeTryToAddIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE, alreadyPresentFacility);
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(true);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseIfOneOfTheFacilityIsNotAlreadyInThePaddockAndWeTryToRemoveIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE, alreadyPresentFacility);
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.NOT_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnFalseIfTheFacilityWeTryToRemoveIsNONE() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = PaddockFacility.NONE;
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.NOT_PRESENT_FACILITY);
    }

    @Test
    public void shouldReturnTrueIfOneOfTheFacilityIsAlreadyInThePaddockAndWeTryToRemoveIt() {
        // Given
        alreadyPresentFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        givenFacility = alreadyPresentFacility;
        when(context.getConvertedFacilities()).thenReturn(Arrays.asList(givenFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(alreadyPresentFacility));
        when(context.isAddingFacilities()).thenReturn(false);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
}
