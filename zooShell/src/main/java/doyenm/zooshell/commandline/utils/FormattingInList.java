package doyenm.zooshell.commandline.utils;

import doyenm.zooshell.model.Penalty;

import java.util.List;

/**
 * @author doyenm
 */
public class FormattingInList {

    public String format(List<Couple> couples) {
        StringBuilder builder = new StringBuilder();
        for (Couple couple : couples) {
            builder.append(couple.getKey());
            builder.append(" : ");
            builder.append(couple.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    public static String formatList(List<String> strs) {
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
            builder.append("\n");
        }
        builder.append("--> ");
        builder.append(strs.size());
        if(strs.size() > 1){
            builder.append(" items");
        } else {
            builder.append(" item");
        }
        return builder.toString();
    }
}
