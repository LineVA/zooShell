package doyenm.zooshell.backup;

import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.xml.specie.SpecieGenerator;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author doyenm
 */
public class Load {

    public Zoo load() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zoo.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File file = new File("src/main/resources/doyenm/zooshell/save/toto.xml");
            Zoo zoo = (Zoo) jaxbUnmarshaller.unmarshal(file);
            return zoo;
        } catch (JAXBException ex) {
            Logger.getLogger(SpecieGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
