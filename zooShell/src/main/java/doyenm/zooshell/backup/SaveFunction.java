package doyenm.zooshell.backup;

import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.model.Zoo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doyenm
 */
public class SaveFunction implements Function<ZooContext, ZooContext> {

    @Override
    public ZooContext apply(ZooContext t) {
        ZooContext zooContext = t;
        try {
            JAXBContext context = JAXBContext.newInstance(Zoo.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream output = new FileOutputStream("src/main/resources/doyenm/zooshell/save/"
                    + zooContext.getSaveName() + ".xml");
            m.marshal(zooContext.getZoo(), output);
            return zooContext;
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(SaveFunction.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
