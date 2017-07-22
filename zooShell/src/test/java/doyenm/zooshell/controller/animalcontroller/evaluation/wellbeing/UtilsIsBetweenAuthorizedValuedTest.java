package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UtilsIsBetweenAuthorizedValuedTest {

    @Test
    public void shouldReturnTrueWhenTheAuthorizedAndCurrentDeviationAreEquals(){
        // Given
        double authorized = 0.5;
        Utils utils = new Utils();
        // When
        boolean result = utils.isBetweenAuthorizedValues(authorized, authorized);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnTrueWhenTheAuthorizedisGreaterThanTheCurrentDeviation(){
        // Given
        double authorized = 0.5;
        double current = 0.3;
        Utils utils = new Utils();
        // When
        boolean result = utils.isBetweenAuthorizedValues(current, authorized);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenTheAuthorizedisLowerThanTheCurrentDeviation(){
        // Given
        double authorized = 0.5;
        double current = 0.9;
        Utils utils = new Utils();
        // When
        boolean result = utils.isBetweenAuthorizedValues(current, authorized);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
