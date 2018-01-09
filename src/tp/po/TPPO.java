package tp.po;

import java.awt.BorderLayout; 
import java.awt.EventQueue; //try
import javax.swing.JFrame;
import javax.swing.JPanel; //painel
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent; //evento
import javax.swing.JTextPane;
import gurobi.*;
import javax.swing.JTextArea;


/**
 *
 * @author mapda
 */
public class TPPO extends JFrame {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) throws GRBException{        
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                Interface Interface = new Interface(); // construtor
                Interface.setVisible(true); // setando a interface na tela
            } catch (Exception t) {
            }
        }
    });
}
    
}
