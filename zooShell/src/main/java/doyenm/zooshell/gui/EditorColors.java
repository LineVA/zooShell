package doyenm.zooshell.gui;

import doyenm.zooshell.commandline.general.TypeReturn;

import java.awt.*;

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
