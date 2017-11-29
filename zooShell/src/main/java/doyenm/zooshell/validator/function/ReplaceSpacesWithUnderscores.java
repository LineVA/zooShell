package doyenm.zooshell.validator.function;

/**
 *
 * @author doyenm
 */
public class ReplaceSpacesWithUnderscores {

    public String replace(String input){
        return input.replaceAll(" ", "_");
    }
}
