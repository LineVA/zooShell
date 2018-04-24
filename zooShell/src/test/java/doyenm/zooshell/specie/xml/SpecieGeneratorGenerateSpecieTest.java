package doyenm.zooshell.specie.xml;

import doyenm.zooshell.model.Specie;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;


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
        Map<String, Specie> species = generator.generateSpecie("src/test/resources/doyenm/zooshell/species");
        // Then
        assertThat(species).isNotNull();
        assertThat(species.size()).isEqualTo(1);
    }
}
