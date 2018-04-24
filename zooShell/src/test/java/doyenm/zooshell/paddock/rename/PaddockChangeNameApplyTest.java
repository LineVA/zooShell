package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaddockChangeNameApplyTest {

    private PaddockChangeNameController subject;

    private PaddockChangeNameContext context;
    private Paddock paddock;
    String newName;
    String currentName;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
        newName = RandomStringUtils.randomAlphabetic(5);
        currentName = RandomStringUtils.randomAlphabetic(5);
        when(paddock.getName()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setName(anyString());
        paddock.setName(currentName);

        Zoo zoo = mock(Zoo.class);
        when(zoo.getPaddocks()).thenCallRealMethod();
        doCallRealMethod().when(zoo).setPaddocks(anyMap());
        Map<String, Paddock> paddocks = new HashMap<>();
        paddocks.put(paddock.getName().toUpperCase(), paddock);
        when(zoo.getPaddocks()).thenReturn(paddocks);

        context = mock(PaddockChangeNameContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getCurrentName()).thenReturn(currentName);
        when(context.getNewName()).thenReturn(newName);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new PaddockChangeNameController();
    }

    @Test
    public void shouldRenameThePaddock(){
        // Given
        // When
        PaddockChangeNameContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        assertThat(result.getZoo().getPaddocks().get(newName.toUpperCase())).isNotNull();
        assertThat(result.getZoo().getPaddocks().get(newName.toUpperCase()).getName()).isEqualTo(newName);
    }
}
