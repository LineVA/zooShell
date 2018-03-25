package doyenm.zooshell.backup;

import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.GenerateZoo;
import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class SaveAndLoadTest {

    private ZooContext givenSaveContext(String file) {
        Zoo zoo = GenerateZoo.generateZooWithNoPadAnimalOrKeeper();
        
        Map<String, Paddock> pads = new HashMap<>();
        Paddock pad = GenerateZoo.generatePaddock();
        pads.put(pad.getName(), pad );
        Map<String, Animal> animals = new HashMap<>();
        animals.put(RandomStringUtils.random(10), GenerateZoo.generateAnimalWithPaddock(pad));
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(RandomStringUtils.random(10), GenerateZoo.generateKeeper());
        
        zoo.setAnimals(animals);
        zoo.setKeepers(keepers);
        zoo.setPaddocks(pads);
        return new ZooContext(zoo, file);
    }
    
    private ZooContext givenLoadContext(String file) {
        return new ZooContext(file);
    }

//    @Test
//    public void shouldLoadTheZoo() throws JAXBException, FileNotFoundException {
//        // Given
//        SaveFunction save = new SaveFunction();
//        LoadFunction load = new LoadFunction();
//        String file = RandomStringUtils.random(10);
//        ZooContext saveContext = givenSaveContext(file);
//        ZooContext loadContext = givenLoadContext(file);
//        // When
//        save.apply(saveContext);
//        load.apply(loadContext);
//        //  Then
//        Assertions.assertThat(saveContext.getZoo()).isEqualToComparingFieldByFieldRecursively(loadContext.getZoo());
//    }
}
