package doyenm.zooshell.gui;

/**
 *
 * @author doyenm
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/** 
 * Extension de la classe JTextField permettant le rappel d'entrées 
 * précédentes avec les touches haut et bas.
 */
public class JTextFieldX extends JTextField implements ActionListener, KeyListener {
  ArrayList cmds;
  int cmdIndex;
  

  public JTextFieldX() {
    super();
    init();
  }
  
  
  public JTextFieldX(int columns) {
    super(columns);
    init();
  }
  

  public JTextFieldX(String text) {
    super(text);
    init();
  } 
  
  
  public JTextFieldX(String text, int columns) {
    super(text, columns);
    init();
  }  
  

  public void init() {
    cmds = new ArrayList();
    addKeyListener(this);
    addActionListener(this);
  }
  

  public void actionPerformed(ActionEvent e) {
    cmds.add(getText());
    cmdIndex = cmds.size();   
    setText("");
  }  


  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (cmdIndex > 0) setText((String)cmds.get(--cmdIndex));
      e.consume();
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if (cmdIndex < cmds.size()-1) setText((String)cmds.get(++cmdIndex));
      e.consume();
    }
  }

  
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}   
}
