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
    			   +"          Software con multiplataforma para llevar una administraci�n del sitio de taxis ADO \n"
    			   +"          OaxaTaxi es una aplicaci�n de uso exclusivo para el sitio de taxis ADO, \n"
    			   +"          escrita por desarrolladores j�venes y emprendedores que busca modernizar el uso del transporte en el estado de Oaxaca.\n"
    			   +"          Queremos expresar nuestro agradecimiento a la empresa de sitios ADO por permitirnos crear este sistema. \n"
    			   +"          \n"
    			   +"          OaxaTaxi est� disponible para ser utilizado en Windows, Mac y GNU/Linux (y otros sistemas operativos tipo Unix).\n"
    			   +"          Si encuentra alg�n error o tiene alguna sugerencia puede contactarnos.\n"
    			   +"          \n"
    			   +"                                                             Cr�ditos\n"
    			   +"                                                             Desarrolladores de OAXATAXI\n"
    			   +"          \n"
    			   +"          Oscar Eduardo L�pez Guzm�n\n"
    			   +"          Garc�a Labastida Daniel de Jes�s\n"
    			   +"          Ram�rez Arellano David de Jes�s\n"
    			   +"          Paz Mart�nez Miguel Angel\n"
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
