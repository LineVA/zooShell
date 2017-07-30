package doyenm.zooshell.backup;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.Zoo;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class SaveSaveTest {

    @Test
    public void shouldDoSomething() throws JAXBException, FileNotFoundException{
        Zoo zoo = new Zoo();
        zoo.setAge(10);
        zoo.setName("oo");
        Animal animal = Animal.builder().age(3).daysOfDrowning(5).sex(Sex.FEMALE).build();
        zoo.getAnimals().put("toto", animal);
        Save save = new Save();
        save.save(zoo);
    }
}
