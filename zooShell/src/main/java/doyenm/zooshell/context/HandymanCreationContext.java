package doyenm.zooshell.context;

import doyenm.zooshell.model.Zoo;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
@Getter
public class HandymanCreationContext {
    private final Zoo zoo;
    private final String name;
    
    public Set<String> getHandymenSet(){
        return getZoo().getHandymen().keySet();
    }
}
