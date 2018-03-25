package doyenm.zooshell.commandline.utils;

import doyenm.zooshell.model.Penalty;

import java.util.List;

/**
 *
 * @author doyenm
 */
public class FormattingInList {

    public String format(List<Couple> couples) {
        String returnStr = "";
        for (Couple couple : couples) {
            returnStr += couple.getKey() + " : " + couple.getValue() + "\n";
        }
        return returnStr;
    }

    public String formatList(List<String> strs) {
        String returnStr = "";
        for (String str : strs) {
            returnStr += str + "\n";
        }
        return returnStr;
    }
    
     public String formatPenaltiesList(List<Penalty> strs) {
        String returnStr = "";
        for (Penalty str : strs) {
            returnStr += str.toString() + "\n";
        }
        return returnStr;
    }
}
