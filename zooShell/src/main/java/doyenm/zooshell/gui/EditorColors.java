package doyenm.zooshell.gui;

import doyenm.zooshell.commandLine.general.TypeReturn;
import static doyenm.zooshell.commandLine.general.TypeReturn.ERROR;
import static doyenm.zooshell.commandLine.general.TypeReturn.QUESTION;
import static doyenm.zooshell.commandLine.general.TypeReturn.SUCCESS;
import java.awt.Color;

/**
 *
 * @author doyenm
 */
public enum EditorColors {

    CMD(Color.WHITE), EXCEPTION(Color.ORANGE), INFO(Color.CYAN), WAIT(Color.GREEN);

    private Color color;

    EditorColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getCorrespondingColor(TypeReturn type) {
        switch (type) {
            case SUCCESS:
                return EditorColors.INFO.getColor();
            case ERROR:
                return EditorColors.EXCEPTION.getColor();
            case QUESTION:
                return EditorColors.WAIT.getColor();
            default:
                return EditorColors.INFO.getColor();
        }
    }
}
