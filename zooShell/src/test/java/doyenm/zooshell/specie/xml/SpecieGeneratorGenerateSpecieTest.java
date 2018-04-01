package doyenm.zooshell.specie.xml;

import doyenm.zooshell.model.Specie;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Map;

/**
 *
 * @author doyenm
 */
public class SpecieGeneratorGenerateSpecieTest {

    @Test
    public void shouldReturnASpecieWithTheExpectedNames(){
        // Given
        SpecieGenerator generator = new SpecieGenerator();
        // When
        Map<String, Specie> species = generator.generateSpecie("src/main/resources/doyenm/zooshell/species");
        // Then
        Assertions.assertThat(species).isNotNull();
        Assertions.assertThat(species.size()).isEqualTo(1);
    }
}
