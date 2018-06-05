package ifg;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import recursos.Conexion;
import recursos.ConexionServer;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
/**
 * Panel desplegable para busquedas
 * @author sheen
 *
 */
public class Desplegable extends JPanel {
    private Button ocultar;
    private JLabel et = new JLabel("Buscar:");
    static JTextField busqueda;
    private int contenedores = 0;
    private JScrollPane scrollPane;
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    JPanel panel;
    Conexion c = new Conexion();
	private JLabel fondo;
	
	/*
	 * Constructor que define las medidas del panel
	 */
    public Desplegable() throws SQLException {
    		busqueda = new JTextField();
    		
        this.setSize(250, 550);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        //Se crean los componentes
        crearComponentes();
        
        
    }
   
    /**
     * Método que detecta implementa un documentlistener para escuchar
     * los cambios en el JTextField que se le envie
     * @param txt = JTextField donde se va a escuchar cambios
     */
    private void setJTexFieldChanged(JTextField txt)
    {
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            
				try {
					printIt(documentEvent);
				} catch (ParseException e) {
					e.printStackTrace();
				}
        }
        /**
         * CUando se actualiza o borra algo del TextField
         */
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            try {
            		//Se manda a imprimir la busqueda
				printIt(documentEvent);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            try {
				printIt(documentEvent);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
 
    }
    /**
     * Método que recibe el evento del cambio del JTextField
     * @param documentEvent = Evento de cambio
     * @throws ParseException
     */
    private void printIt(DocumentEvent documentEvent) throws ParseException {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
 
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
        		//Se intenta manddar a llamar al método consultar con el texto actual del JTextField
        	
        		/*
        		try {
					consultar(this.busqueda.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			*/
        	consultarDeServer(this.busqueda.getText());
        		
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
        		refrescarResultados(0);
        }
    }
    /**
     * Método que comprueba si una cadena se puede convertir a un numero
     * @param cadena = Cadena de String a evaluar
     * @return
     */
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
    public void consultarDeServer(String consulta){
		//Se remueve todo del panel y se comprueba si la cadena es numérica
		panel.removeAll();
		if(isNumeric(consulta)) {
			String[] cadena = new String[3];
			ConexionServer con = new ConexionServer();
			String bloque[]  = con.Select("SELECT * from taxi where id_taxi="+consulta+";", 30);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Placas:"+cadena[1];
					pase[2] = "Modelo:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Taxi:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from taxista where id_taxista="+consulta+";", 40);
			
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Nombre:"+cadena[1];
					pase[2] = "Apellido:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Taxista:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from usuario where id_usuario="+consulta+";", 50);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Nickname:"+cadena[1];
					pase[2] = "Nombre:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Usuario:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from oaxataxi.viaje where id_viaje="+consulta+";", 60);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Origen:"+cadena[1];
					pase[2] = "Destino:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Viaje:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
		}else {
			
			String[] cadena = new String[3];
			ConexionServer con = new ConexionServer();
			
			String bloque[]  = con.Select("SELECT * from taxi where no_placas='"+consulta+"';", 30);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Placas:"+cadena[1];
					pase[2] = "Modelo:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Taxi:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from taxista where nombre='"+consulta+"' OR apaterno ='"+consulta+"' OR amaterno='"+consulta+"';", 40);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Nombre:"+cadena[1];
					pase[2] = "Apellido:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Taxista:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from usuario where nombre='"+consulta+"' OR apaterno ='"+consulta+"' OR amaterno='"+consulta+"' OR nickname='"+consulta+"';", 50);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Nickname:"+cadena[1];
					pase[2] = "Nombre:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Usuario:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
			con = new ConexionServer();
			bloque =null;
			bloque = con.Select("SELECT * from oaxataxi.viaje where origen="+consulta+"' OR destino='"+consulta+"';", 60);
			if(!bloque[0].equals("0 resultdcs ")) {
				int cont=0;
				if(bloque.length>1) {
					cont = bloque.length-1;
				}else {
					cont = cont;
				}
				for(int a=0;a<cont;a++) {
					cadena = bloque[a].split("<>");
					String pase[] = new String[3];
					pase[0] = "Id:"+cadena[0];
					pase[1] = "Origen:"+cadena[1];
					pase[2] = "Destino:"+cadena[2];
		            Busqueda_c p= new Busqueda_c("Viaje:",cadena[0],cadena[1],cadena[2]);
		            p.addMouseListener(new Click());
		 		   	panel.add(p);
				}
			}
			
		}
		refrescarResultados(panel.getComponents().length);
}
    
    /**
     * Metodo para consultar DE MANERA LOCAL elementos en la base de datos local
     * @param consulta
     * @throws SQLException 
     * @throws ParseException
     */
    public void consultar(String consulta) throws SQLException, ParseException {
    		//Se remueve todo del panel y se comprueba si la cadena es numérica
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
    		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
    		          
    		            }
    		            Busqueda_c p= new Busqueda_c("Taxi:",cadena[0],cadena[1],cadena[2]);
    		            p.addMouseListener(new Click());
    		 		   	panel.add(p);
    		 		
    		   }
    		   
    		   
       		sentencia = conexion.createStatement();
       		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxista where id_taxista="+consulta+";");
       		rsmd = resultado.getMetaData();

   		   while (resultado.next()) {
   			   cadena[0] = "";cadena[1] = "";cadena[2] = "";
   		            for (int i = 1; i <= 3; i++) {
   		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
   		          
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
		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
		          
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
  		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
  		          
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
    		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
    		          
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
      		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
      		          
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
   		            		cadena[i-1] = cadena[i-1] + rsmd.getColumnName(i) +": "+ resultado.getString(i);
   		          
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