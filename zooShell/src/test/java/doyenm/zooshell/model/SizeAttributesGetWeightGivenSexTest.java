package doyenm.zooshell.model;

import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SizeAttributesGetWeightGivenSexTest {

    SizeAttributes subject = new SizeAttributes(RandomUtils.nextDouble(), RandomUtils.nextDouble());

    @Test
    public void shouldReturnFemaleWeightIfGivenSexIsFemale(){
        // Given
        // When
        Double result = subject.getWeightGivenSex(Sex.FEMALE);
        // Then
        Assertions.assertThat(result).isEqualTo(subject.getFemaleWeight());
    }

    @Test
    public void shouldReturnMaleWeightIfGivenSexIsMale(){
        // Given
        // When
        Double result = subject.getWeightGivenSex(Sex.MALE);
        // Then
        Assertions.assertThat(result).isEqualTo(subject.getMaleWeight());
    }
}
