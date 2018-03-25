package doyenm.zooshell.gui;

import doyenm.zooshell.commandline.general.CommandManager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class MainGUI extends JFrame {

    public MainGUI() {
        super("Zoo");

    }

    public void construct(CommandManager manager) {
        this.setLayout(new FlowLayout());
        // To set the JFrame in fullscreen 
        Dimension dimension = new Dimension(600, 600);
        TextPane editor = new TextPane(manager, 700, 700);
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
