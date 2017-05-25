package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class Name {

    String name;

    Name() {
    }

    Name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
