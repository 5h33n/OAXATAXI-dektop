package ifg;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class AyudaAdmin extends JPanel {
	private JButton cerrar,acerca,ayuda;
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
    
    
    
    @SuppressWarnings("deprecation")
	public void crearComponente() {
    	
//    principal = new JPanel();
    
    botones = new JPanel();
    botones.setLayout(new BoxLayout(botones, BoxLayout.PAGE_AXIS));
    botones.add(Box.createRigidArea(new Dimension(0,2)));
    
    scrollPane = new JScrollPane(botones);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
 
    
    JButton cerrar = new JButton();
    cerrar= new ifg.Button(true);
    cerrar.setForeground(Color.black);
    ImageIcon a = new ImageIcon(getClass().getResource("/img/cerrarSesion.png"));
    cerrar.setIcon(a);
    //cerrar.setBounds(500,0,128,30);
    this.add(cerrar);
    cerrar.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

            
            new Login().setVisible(true);
            p.setVisible(false);
           
     		
            
        }
    }
    );
    
    this.add(cerrar);
    
    JButton acerca = new JButton();
    acerca= new ifg.Button(true);
    acerca.setForeground(Color.black);
    ImageIcon b = new ImageIcon(getClass().getResource("/img/acerca.png"));
    acerca.setIcon(b);
    //acerca.setBounds(500,0,128,30);
    acerca.setBounds(new Rectangle(50,50,100,75));
    this.add(acerca);
    acerca.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

        	 System.out.println("acerca");
            
        }
    }
    );
    this.add(acerca);
    
    JButton ayuda = new JButton();
    ayuda= new ifg.Button(true);
    ayuda.setForeground(Color.black);
    ImageIcon c = new ImageIcon(getClass().getResource("/img/ayuda.png"));
    ayuda.setIcon(c);
    //ayuda.setBounds(500,0,128,30);
    this.add(ayuda);
    ayuda.addActionListener(
            new ActionListener() {
        public void actionPerformed(ActionEvent evento) {

        	 System.out.println("ayuda");
            
            
        }
    }
    );
    this.add(ayuda);
    
    
    
    botones.add(cerrar);
    botones.add(acerca);
    botones.add(ayuda);
    
    //principal.add(botones, BorderLayout.CENTER);
    
    this.add(scrollPane);
}
    public void cerrar() {
    	
		System.exit(0);
		
		
}
    
    
//    private class Click extends MouseAdapter{
//    	public void mouseClicked(MouseEvent e) {
//    		if (e.getSource() == cerrar) {
//    			l.setVisible(true);
//    			p.dispose();
//    			System.out.println("cerrar");
//    		}
//    		else if (e.getSource() == acerca) {
//    			
//    			
//    			
//    		}
//            else if (e.getSource() == ayuda) {
//    			
//    			
//    			
//    		}
//	
//		}
//    }
    
  
//    
//    public static void main(String s[]) {
//
//       AyudaAdmin listanum = new AyudaAdmin();
//        listanum.setVisible(true);
//        listanum.setSize(120, 160);
//        listanum.setBounds(1000,0,128,30);
//        
//
//    }
}
    

