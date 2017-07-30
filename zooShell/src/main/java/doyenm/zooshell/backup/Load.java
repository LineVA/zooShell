package doyenm.zooshell.backup;

import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.xml.specie.SpecieGenerator;
import java.io.File;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author doyenm
 */
public class Load implements Consumer<ZooContext> {

    @Override
    public void accept(ZooContext t) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zoo.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File file = new File("src/main/resources/doyenm/zooshell/save/" + t.getSaveName() + ".xml");
            t.setZoo((Zoo) jaxbUnmarshaller.unmarshal(file));
        } catch (JAXBException ex) {
            Logger.getLogger(SpecieGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
