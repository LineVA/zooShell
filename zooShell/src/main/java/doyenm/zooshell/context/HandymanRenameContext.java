package doyenm.zooshell.context;

import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class HandymanRenameContext {
    private final Zoo zoo;
    private final String currentName;
    private final String newName;
    
    private Handyman handyman;
    
    public Map<String, Handyman> getHandymen(){
        return getZoo().getHandymen();
    }
}
