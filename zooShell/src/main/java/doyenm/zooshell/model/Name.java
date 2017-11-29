package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@ToString
public class Name {

    String name;

    Name() {
    }

    Name(String name) {
        this.name = name;
    }
}
