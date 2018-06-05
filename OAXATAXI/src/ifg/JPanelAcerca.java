package ifg;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPanelAcerca extends JFrame {
      private JPanel acerca;
      private JTextArea texto;
      
      
      public void crearPanel() {
    
    	  
    	   texto = new JTextArea();
    	   texto.setFont(new Font("Andale Mono", 0, 9));
    	   texto.setText("\n\n                                                    Este Sistema fue creado por:\n\n"
    			   +"          Lopez Guzman Oscar Eduardo \n"
    			   +"          Garcia Labastida Daniel De Jesus \n"
    			   +"          Ramirez Arellano David De Jesus \n");
    	   texto.setVisible(true);
    	   texto.setSize(500, 300);
    	   texto.setEditable(false);
    	   
    	   acerca = new JPanel();
    	   acerca.add(texto);
    	   acerca.setVisible(true);
    	   
    	   this.add(texto);
    	   this.setSize(500,300);
    	   this.setLocationRelativeTo(null);
    	   this.setVisible(true);
    	
      }
     
      
}
