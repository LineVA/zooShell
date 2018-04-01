package doyenm.zooshell.specie.xml;

import doyenm.zooshell.model.Specie;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doyenm
 */
public class SpecieGenerator {

    public Map<String, Specie> generateSpecie(String path) {
        try {
            Map<String, Specie> species = new HashMap<>();
            JAXBContext jaxbContext = JAXBContext.newInstance(Specie.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File folder = new File(path);
            Specie specie;
            for (File file : folder.listFiles()) {
                specie = (Specie) jaxbUnmarshaller.unmarshal(file);
                species.put(specie.getNames().getName().toUpperCase(), specie);
            }
            return species;
        } catch (JAXBException ex) {
            Logger.getLogger(SpecieGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
}
