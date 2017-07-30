package doyenm.zooshell.backup;

import doyenm.zooshell.model.Zoo;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author doyenm
 */
public class Save {

    public boolean save(Zoo zoo) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Zoo.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ClassLoader classLoader = getClass().getClassLoader();
        OutputStream output = new FileOutputStream("src/main/resources/doyenm/zooshell/save/toto.xml");
        m.marshal(zoo, output);
        return true;
    }
}
