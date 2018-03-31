package doyenm.zooshell.handyman.create;

import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

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
