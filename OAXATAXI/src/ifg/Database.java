package ifg;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Database extends JFrame {
	public DefaultTableModel dtm1;
	public DefaultTableModel dtm2;
	public DefaultTableModel dtm3;
	public DefaultTableModel dtm4;
	Object[][] a1 = {};
	String[] b1 = {"id_Taxi:","Número de placas:","Estado:","Comentarios:","Puntuación"};

	
	String[] b2 = {"id_Taxista:","Nombre:","Apellidos:","Licencia enlace:","Telefono:","Foto enlace:","Estado:","Comentarios:","Puntuación:"};
	String[] b3 = {"id_Usuario:","Nombre:","Apellidos:","Nickname","RFC:","Tel�fono:","Sexo:","Email:","Fecha de nacimiento:"};
	String[] b4 = {"id_viaje","id_taxista","Conductor","id_taxi","Placas","Usuario","Estado","Hora inicio","Hora final","Origen","Destino","Monto"};

	
	private Connection conexion=null;
    ResultSet resultado;
    JPanel content = new JPanel();
    JPanel v = new JPanel();
    Statement sentencia;
    JComboBox cbPersonas;
    PanelTabla pt,ptt,pu,pv;
    
    private JLabel titulo = new JLabel("Base de datos");
    
	public Database() {
		v.setLayout(new BoxLayout(v,BoxLayout.Y_AXIS));
		content.setLayout(new CardLayout());
		String [] unidades = {"","Taxis","Taxistas","Usuarios","Viajes"};
        cbPersonas = new JComboBox(unidades);
        v.add(cbPersonas);
        cbPersonas.addItemListener( new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
            	CardLayout cl = (CardLayout)(content.getLayout());
                cl.show(content, (String)e.getItem());
            }

         });
        
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
        v.add(titulo);
		
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
		pt = new PanelTabla(a1,b1);
		ptt = new PanelTabla(a1,b2);
		pu = new PanelTabla(a1,b3);
		pv = new PanelTabla(a1,b4);
		//pt.setBounds(0,0,500,200);
		//ptt.setBounds(0,0,500,200);
		content.add(ptt,"Taxistas");
		content.add(pt,"Taxis");
		content.add(pu,"Usuarios");
		content.add(pv,"Viajes");
		dtm1 = pt.getDtm();
		dtm2 = ptt.getDtm();
		dtm3 = pu.getDtm();
		dtm4 = pv.getDtm();
		
		try {
			mostrar(0);
			mostrar(1);
			mostrar(2);
			mostrar(3);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        v.add(content);
        content.setSize(1200,300);
        this.setContentPane(v);
        //this.setLocationRelativeTo(null);
        this.setSize(1200,400);
        this.setBounds(250,200,1200,500);
        this.setVisible(true);
        this.pack();
	}
	public void conexionDB() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/oaxataxi";
			conexion = DriverManager.getConnection(url,"postgres","Pacomegoma12");
			if (conexion!=null) {
				//System.out.println("Conexion exitosa");
			}else {
				JOptionPane.showMessageDialog(null,"Conexion fallida alv");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void mostrar(int n) throws SQLException {
    	try {
			conexionDB();
			sentencia = conexion.createStatement();
			switch(n) {
			case 0:
				
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.taxi");
				while ( resultado.next() ) {
					String id_taxi = resultado.getString("id_taxi");
					String no_placas = resultado.getString("no_placas");
					String estado = resultado.getString("estado");
					String comentarios = resultado.getString("comentarios");
					String puntuacion = resultado.getString("puntuacion");
		    			String [] modelo={id_taxi,no_placas,estado,comentarios,puntuacion};
		    			dtm1.addRow(modelo);
		         }
				break;
			case 1:
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.taxista");
				while ( resultado.next() ) {
					String id_taxista = resultado.getString("id_taxista");
					String nombre = resultado.getString("nombre");
					String apaterno = resultado.getString("apaterno");
					String amaterno = resultado.getString("amaterno");
					String licencia = resultado.getString("licencia");
					String telefono = resultado.getString("telefono");
					String c_tel = resultado.getString("c_tel");
					String foto = resultado.getString("foto");
					String estado = resultado.getString("estado");
					String comentarios = resultado.getString("comentarios");
					String puntuacion = resultado.getString("puntuacion");
		    			String [] modelo={id_taxista,nombre,apaterno+" "+amaterno,licencia,c_tel+telefono,foto,estado,comentarios,puntuacion};
		    			dtm2.addRow(modelo);
		         }
				break;
			case 2:
				conexionDB();
				sentencia = conexion.createStatement();
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.usuario");
				while ( resultado.next() ) {
					String id_usuario = resultado.getString("id_usuario");
					String nickname = resultado.getString("nickname");
					String nombre = resultado.getString("nombre");
		    		String apaterno = resultado.getString("apaterno");
		    		String amaterno = resultado.getString("amaterno");
		    		String rfc = resultado.getString("rfc");
		    		String telefono = resultado.getString("telefono");
		    		String c_tel = resultado.getString("c_tel");
		    		String sexo = resultado.getString("sexo");
		    		String email = resultado.getString("email");
		    		String fecha_nacimiento = resultado.getString("fecha_nacimiento");
		    		String [] modelo={id_usuario,nombre,apaterno + " " + amaterno,nickname,rfc,c_tel + telefono,sexo,email,fecha_nacimiento};
		    		dtm3.addRow(modelo);
		    		
		         }
				
				break;
			case 3:
				
				conexionDB();
				sentencia = conexion.createStatement();
				resultado = sentencia.executeQuery("SELECT id_taxi,no_placas,taxista_viaje_taxi.id_viaje,nickname_u,hora_inicio,hora_final,origen,destino,viaje.estado,monto_pagado,taxista_viaje_taxi.id_taxista,nombre,apaterno FROM oaxataxi.taxista_viaje_taxi INNER JOIN oaxataxi.viaje ON taxista_viaje_taxi.id_viaje = viaje.id_viaje INNER JOIN oaxataxi.taxista ON taxista_viaje_taxi.id_taxista = taxista.id_taxista");
				while ( resultado.next() ) {
					String id_taxi = resultado.getString("id_taxi");
					String no_placas = resultado.getString("no_placas");
					String id_viaje = resultado.getString("id_viaje");
		    		String nickname = resultado.getString("nickname_u");
		    		String hora_inicio = resultado.getString("hora_inicio");
		    		String hora_final = resultado.getString("hora_final");
		    		String origen = resultado.getString("origen");
		    		String destino = resultado.getString("destino");
		    		String estado = resultado.getString("estado");
		    		String monto_pagado = resultado.getString("monto_pagado");
		    		String id_taxista = resultado.getString("id_taxista");
		    		String nombre = resultado.getString("nombre");
		    		String apaterno = resultado.getString("apaterno");
		    		String [] modelo={id_viaje,id_taxista,nombre+" "+apaterno,id_taxi,no_placas,nickname,estado,hora_inicio,hora_final,origen,destino,"$"+monto_pagado};
		    		dtm4.addRow(modelo);
		    		
		         }
				break;
			}
			
	         resultado.close();
	         sentencia.close();
	         conexion.close();
			//table = new JTable(dtm);
	    
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	    } finally {
	        if (sentencia != null) { sentencia.close(); }
	    }
    }
}
