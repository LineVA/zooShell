package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UtilsComputeDeviationBetweenCurrentAndOptimalTest {

    @Test
    public void shouldReturnTheExpectedValueWhenCurrentIsGreaterThanOptimal(){
        // Given
        double current = 2.0;
        double optimal = 1.0;
        Utils utils = new Utils();
        // When
        double result = utils.computeDeviationBetweenCurrentAndOptimal(current, optimal);
        // Then
        Assertions.assertThat(result).isEqualTo(1.0);
    }
    
       @Test
    public void shouldReturnTheExpectedValueWhenCurrentIsLowerThanOptimal(){
        // Given
        double current = 1.0;
        double optimal = 2.0;
        Utils utils = new Utils();
        // When
        double result = utils.computeDeviationBetweenCurrentAndOptimal(current, optimal);
        // Then
        Assertions.assertThat(result).isEqualTo(0.5);
    }
    
        @Test
    public void shouldReturnZeroWhenCurrentAndOptimalAreEquals(){
        // Given
        double current = 1.0;
        double optimal = 1.0;
        Utils utils = new Utils();
        // When
        double result = utils.computeDeviationBetweenCurrentAndOptimal(current, optimal);
        // Then
        Assertions.assertThat(result).isEqualTo(0.0);
    }
    
     @Test
    public void shouldReturnTheZeroWhenOptimalValueISZero(){
        // Given
        double current = 1.0;
        double optimal = 0.0;
        Utils utils = new Utils();
        // When
        double result = utils.computeDeviationBetweenCurrentAndOptimal(current, optimal);
        // Then
        Assertions.assertThat(result).isEqualTo(0.0);
    }
    
}
