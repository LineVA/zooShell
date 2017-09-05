package doyenm.zooshell.backup;

import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.FindPaddock;
import doyenm.zooshell.xml.specie.SpecieGenerator;
import java.io.File;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author doyenm
 */
public class LoadFunction implements Function<ZooContext, ZooContext> {

    private final FindPaddock findPaddock = new FindPaddock();

    @Override
    public ZooContext apply(ZooContext t) {
        ZooContext zooContext = t;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zoo.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File file = new File("src/main/resources/doyenm/zooshell/save/" + zooContext.getSaveName() + ".xml");
            Zoo zoo = (Zoo) jaxbUnmarshaller.unmarshal(file);
            zoo = mergePaddocks(zoo);
            zooContext.setZoo(zoo);
            return zooContext;
        } catch (JAXBException ex) {
            Logger.getLogger(SpecieGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Merge the paddocks : because the paddocks are stored in three differents
     * places (the list of the paddocks, the paddocks viewed by each animal and
     * the paddocks viewed by each keeper), after loading a zoo, the
     * modifications of an existing paddock aren't seen by an already existing
     * animal or keeper.
     *
     * @param zoo
     * @return a zoo with merged paddocks
     */
    private Zoo mergePaddocks(Zoo zoo) {
        zoo = mergePaddocksForAnimals(zoo);
        return zoo;
    }

    private Zoo mergePaddocksForAnimals(Zoo zoo) {
        Paddock paddock;
        for (Animal animal : zoo.getAnimals().values()) {
            paddock = findPaddock.find(zoo, animal.getPaddock().getName());
            animal.setPaddock(paddock);
        }
        return zoo;
    }

    private Zoo mergePaddocksForKeepers(Zoo zoo) {
        Paddock paddock;
        for (AnimalKeeper keeper : zoo.getKeepers().values()) {
            for (TimedOccupation timedTask : keeper.getOccupations()) {
                paddock = findPaddock.find(zoo, timedTask.getPaddock().getName());
                timedTask.setPaddock(paddock);
            }
        }
        return zoo;
    }

}
