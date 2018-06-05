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
    	   texto.setFont(new Font("Andale Mono", 1, 11));
    	   texto.setText("\n\n                                                             OaxaTaxi\n\n"
    			   +"          Software con multiplataforma para llevar una administración del sitio de taxis ADO \n"
    			   +"          OaxaTaxi es una aplicación de uso exclusivo para el sitio de taxis ADO, \n"
    			   +"          escrita por desarrolladores jóvenes y emprendedores que busca modernizar el uso del transporte en el estado de Oaxaca.\n"
    			   +"          Queremos expresar nuestro agradecimiento a la empresa de sitios ADO por permitirnos crear este sistema. \n"
    			   +"          \n"
    			   +"          OaxaTaxi está disponible para ser utilizado en Windows, Mac y GNU/Linux (y otros sistemas operativos tipo Unix).\n"
    			   +"          Si encuentra algún error o tiene alguna sugerencia puede contactarnos.\n"
    			   +"          \n"
    			   +"                                                             Créditos\n"
    			   +"                                                             Desarrolladores de OAXATAXI\n"
    			   +"          \n"
    			   +"          Oscar Eduardo López Guzmán\n"
    			   +"          García Labastida Daniel de Jesús\n"
    			   +"          Ramírez Arellano David de Jesús\n"
    			   +"          Paz Martínez Miguel Angel\n"
    			   +"          Otros Colaboradores.\n"
    			   +"          \n"
    			   + "         \n");
    	   texto.setVisible(true);
    	   texto.setSize(500, 300);
    	   texto.setEditable(false);
    	   ;
    	   
    	   acerca = new JPanel();
    	   acerca.add(texto);
    	   acerca.setVisible(true);
    	   
    	   this.add(texto);
    	   this.setSize(750,400);
    	   this.setLocationRelativeTo(null);
    	   this.setVisible(true);
    	
      }
     
      
}
