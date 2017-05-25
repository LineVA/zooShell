package doyenm.zooshell.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import doyenm.zooshell.launch.play.Play;
import lombok.Getter;
import doyenm.zooshell.gui.TextPane;

/**
 *
 * @author doyenm
 */
public class MainGUI extends JFrame {

    @Getter
    private TextPane editor;

    public MainGUI(Play play) {
        super("Zoo");
        this.setLayout(new FlowLayout());
        // To set the JFrame in fullscreen 
        Dimension dimension = new Dimension(600, 600);
        TextPane editor =  new TextPane(play, 700, 700);
        JScrollPane scroll = new JScrollPane(editor);
        this.getContentPane().add(scroll);
        this.setSize(dimension);
        this.closeWindows();
        this.pack();
        setVisible(true);
    }

    private void closeWindows() {
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
    }
}
