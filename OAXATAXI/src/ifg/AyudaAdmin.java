package ifg;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class AyudaAdmin extends JPanel{
	private JButton cerrar,acerca,ayuda,configuracion;
    private JPanel principal,botones;
    private JScrollPane scrollPane;
    Login l;
    Principal p;
    AyudaAdmin y;
    
    public AyudaAdmin(Principal p)  {
    
    this.setVisible(true);
    this.setSize(165, 145);
    this.setLayout(new BorderLayout());
    this.p= p;
    crearComponente();
    }
    
    
    
    public void crearComponente() {
    	
//    principal = new JPanel();
    
    botones = new JPanel();
    botones.setLayout(new BoxLayout(botones, BoxLayout.PAGE_AXIS));
    botones.add(Box.createRigidArea(new Dimension(0,3)));
    
//    scrollPane = new JScrollPane();
//    scrollPane.setLayout(new BoxLayout(botones, BoxLayout.PAGE_AXIS));
//    scrollPane.add(Box.createRigidArea(new Dimension(0,3)));
//    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
 
    
    JButton cerrar = new JButton();
    cerrar= new ifg.Button(true);
    cerrar.setForeground(Color.black);
    ImageIcon a = new ImageIcon(getClass().getResource("/img/cerrarSesion.png"));
    cerrar.setIcon(a);
    //cerrar.setBounds(500,0,128,30)ñ
    
    cerrar.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

            
            new Login().setVisible(true);
            p.setVisible(false);
           
     		
            
        }
    }
    );
    

    
    JButton acerca = new JButton();
    acerca= new ifg.Button(true);
    acerca.setForeground(Color.black);
    ImageIcon b = new ImageIcon(getClass().getResource("/img/acerca.png"));
    acerca.setIcon(b);
    acerca.setBounds(new Rectangle(50,50,100,75));
    
    acerca.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

        	 JPanelAcerca a = new JPanelAcerca();
        	 a.crearPanel();
            
        }
    }
    );
    
    
    JButton ayuda = new JButton();
    ayuda= new ifg.Button(true);
    ayuda.setForeground(Color.black);
    ImageIcon c = new ImageIcon(getClass().getResource("/img/ayuda.png"));
    ayuda.setIcon(c);
    ayuda.setBounds(500,0,128,30);
   
    ayuda.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

        	 System.out.println("ayuda");
            
            
        }
    }
    );
    this.add(ayuda);
    
    
    
    JButton configuracion = new JButton();
    configuracion= new ifg.Button(true);
    configuracion.setForeground(Color.black);
    ImageIcon d = new ImageIcon(getClass().getResource("/img/acerca.png"));
    configuracion.setIcon(d);
    ayuda.setBounds(500,0,128,30);
    
    configuracion.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

        	 System.out.println("configuracion");
            
            
        }
    }
    );
    
    
    botones.add(cerrar);
    botones.add(ayuda);
    botones.add(acerca);
    
    this.add(botones);
}
    public void cerrar() {
    	
		System.exit(0);
		
		
}
    
    

}
    

