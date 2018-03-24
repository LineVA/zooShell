package doyenm.zooshell.commandline.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class ExtractingExpression {

    public static List<String> extractExpression(String beginning, String[] cmd) {
        int first = -1;
        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].equals(beginning)) {
                first = i+1;
                break;
            }
        }
        if (first == -1) {
            return new ArrayList<>();
        }
        int last = -1;
        for (int i = first; i < cmd.length; i++) {
            if (cmd[i].endsWith(",")) {
                cmd[i].substring(0, cmd[i].length()-1);
                last = i;
                break;
            }
        }
        if(last == -1){
            last = cmd.length - 1;
        }
        return new ArrayList(Arrays.asList(Arrays.copyOfRange(cmd, first, last + 1)));
    }
}
