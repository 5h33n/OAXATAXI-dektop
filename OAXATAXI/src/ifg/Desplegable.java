package ifg;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import recursos.Conexion;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class Desplegable extends JPanel {
    private Button ocultar;
    private JLabel et = new JLabel("Buscar:");
    private JTextField busqueda;
    private JPanel container;
    private int contenedores = 0;
    private JScrollPane scrollPane;
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    JPanel panel;
    Conexion c = new Conexion();
	private JLabel fondo;
    public Desplegable() throws SQLException {
    		busqueda = new JTextField();
    		
        this.setSize(250, 550);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        crearComponentes();
        
    }
    
    
    
    
    private void setJTexFieldChanged(JTextField txt)
    {
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
 
    }
 
    private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
 
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
        		try {
					consultar(this.busqueda.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
        		refrescarResultados(0);
        }
    }
    
    
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
    public void consultar(String consulta) throws SQLException {
    		panel.removeAll();
    		
    		
    		
    		if(isNumeric(consulta)) {
    			String[] cadena = new String[3];
    			conexion = c.conexionDB();
        		sentencia = conexion.createStatement();
        		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxi where id_taxi="+consulta+";");
        		ResultSetMetaData rsmd = resultado.getMetaData();

    		   while (resultado.next()) {
    			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
    		            for (int i = 1; i <= 3; i++) {
    		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
    		          
    		            }
    		            Busqueda_c p= new Busqueda_c("Taxi:",cadena[0],cadena[1],cadena[2]);
    		 		   	panel.add(p);
    		   }
    		   
    		   
       		sentencia = conexion.createStatement();
       		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxista where id_taxista="+consulta+";");
       		rsmd = resultado.getMetaData();

   		   while (resultado.next()) {
   			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
   		            for (int i = 1; i <= 3; i++) {
   		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
   		          
   		            }
   		            Busqueda_c p= new Busqueda_c("Taxista:",cadena[0],cadena[1],cadena[2]);
   		 		   	panel.add(p);
   		   }
    		   
   		sentencia = conexion.createStatement();
   		resultado = sentencia.executeQuery("SELECT * from oaxataxi.usuario where id_usuario="+consulta+";");
   		rsmd = resultado.getMetaData();

		   while (resultado.next()) {
			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
		            for (int i = 1; i <= 3; i++) {
		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
		          
		            }
		            Busqueda_c p= new Busqueda_c("Usuario:",cadena[0],cadena[1],cadena[2]);
		 		   	panel.add(p);
		   }
		   
		   sentencia = conexion.createStatement();
      		resultado = sentencia.executeQuery("SELECT * from oaxataxi.viaje where id_viaje="+consulta+";");
      		rsmd = resultado.getMetaData();

  		   while (resultado.next()) {
  			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
  		            for (int i = 1; i <= 3; i++) {
  		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
  		          
  		            }
  		            Busqueda_c p= new Busqueda_c("Viaje:",cadena[0],cadena[1],cadena[2]);
  		 		   	panel.add(p);
  		   }
    		   resultado.close();
  	       sentencia.close();
  	       conexion.close();
    		}else {
    			String[] cadena = new String[3];
    			conexion = c.conexionDB();
        		sentencia = conexion.createStatement();
        		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxi where no_placas='"+consulta+"';");
        		ResultSetMetaData rsmd = resultado.getMetaData();

        		
    		   while (resultado.next()) {
    			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
    		            for (int i = 1; i <= 3; i++) {
    		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
    		          
    		            }
    		            Busqueda_c p= new Busqueda_c("Taxi:",cadena[0],cadena[1],cadena[2]);
    		 		   	panel.add(p);
    		   }
    		   
    		   
    		   
    		   sentencia = conexion.createStatement();
          		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxista where nombre='"+consulta+"' OR apaterno ='"+consulta+"' OR amaterno='"+consulta+"';");
          		rsmd = resultado.getMetaData();

      		   while (resultado.next()) {
      			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
      		            for (int i = 1; i <= 3; i++) {
      		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
      		          
      		            }
      		            Busqueda_c p= new Busqueda_c("Taxista:",cadena[0],cadena[1],cadena[2]);
      		 		   	panel.add(p);
      		   }
      		   
      		 sentencia = conexion.createStatement();
       		resultado = sentencia.executeQuery("SELECT * from oaxataxi.usuario where nombre='"+consulta+"' OR apaterno ='"+consulta+"' OR amaterno='"+consulta+"' OR nickname='"+consulta+"';");
       		rsmd = resultado.getMetaData();

   		   while (resultado.next()) {
   			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
   		            for (int i = 1; i <= 3; i++) {
   		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +":"+ resultado.getString(i);
   		          
   		            }
   		            Busqueda_c p= new Busqueda_c("Usuario:",cadena[0],cadena[1],cadena[2]);
   		 		   	panel.add(p);
   		   }
    		   
    		}
		   
		   
		
		
		refrescarResultados(panel.getComponents().length);
    }
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == ocultar) {
    			bye();
    		}
    	}
    }
    public void bye() {
    		this.setVisible(false);
    }
    public void crearComponentes() {
    		
    		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        refrescarResultados(contenedores);
        
    		
    	
    		this.add(scrollPane);
    	
    		et.setBounds(30,20,120,35);
    		et.setFont(new Font("Arial", Font.BOLD, 20));
    		this.add(et);
    		busqueda = new JTextField();
    		busqueda.setBounds(30,60,170,30);
    		this.add(busqueda);
    		setJTexFieldChanged(busqueda);
        ocultar = new Button(false);
        ocultar.setBounds(65,410,120,40);
        ocultar.setColor1(new Color(255, 196, 0));
        ocultar.setColor2(new Color(202, 147, 0));
        ocultar.setForeground(Color.black);
        ocultar.setText("Ocultar");
        ocultar.addMouseListener(new Click());
        this.add(ocultar);
        
        ImageIcon fd = new ImageIcon(getClass().getResource("/img/fondoDesp.jpg"));
        fondo = new JLabel();
        fondo.setIcon(fd);
        fondo.setBounds(0,0,200,300);
        this.add(fondo);
        this.setBackground(Color.BLACK);
    }
    public void refrescarResultados(int c) {
    		
        switch(c) {
        		case 0:
        			scrollPane.setVisible(false);
        			break;
        		case 1:
        			scrollPane.setBounds(25, 100, 200, 74);
        			scrollPane.setVisible(true);
        				break;
        		case 2:
        			scrollPane.setBounds(25, 100, 200, 148);
        			scrollPane.setVisible(true);
        			break;
        		case 3:
        			scrollPane.setBounds(25, 100, 200, 222);
        			scrollPane.setVisible(true);
        			break;
        		case 4:
        			scrollPane.setBounds(25, 100, 200, 296);
        			scrollPane.setVisible(true);
        			break;
        		default:
        			scrollPane.setBounds(25, 100, 200, 296);
        			scrollPane.setVisible(true);
        			break;
        }
    }
    
}
