package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AnimalUpdateContraceptionContext {

    private final Zoo zoo;
    private final String animal;
    private final String contraceptionMethod;
    
    private Animal convertedAnimal;
    private ContraceptionMethod convertedContraceptionMethod; 
    
    public void convert(){}
    
    public void build(){
        
    }
}
