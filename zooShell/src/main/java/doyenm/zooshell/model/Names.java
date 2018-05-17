package doyenm.zooshell.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author doyenm
 */
@EqualsAndHashCode(of={"name"})
@AllArgsConstructor
@NoArgsConstructor
public class Names {
    private String name;
    private List<Name> additionalNames;
    private  String scientificName;

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
