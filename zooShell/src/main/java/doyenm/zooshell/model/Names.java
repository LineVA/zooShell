package doyenm.zooshell.model;

import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @author doyenm
 */
@EqualsAndHashCode(of={"name"})
public class Names {
    private String name;
    private List<Name> additionalNames;
    private  String scientificName;

    public Names(String name, List<Name> additionalNames, String scientificName) {
        this.name = name;
        this.additionalNames = additionalNames;
        this.scientificName = scientificName;
    }
    
    public Names() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getAdditionalNames() {
        return additionalNames;
    }

    public void setAdditionalNames(List<Name> additionalNames) {
        this.additionalNames = additionalNames;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
    
    
}
