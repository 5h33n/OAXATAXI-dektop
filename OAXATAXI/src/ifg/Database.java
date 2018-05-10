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
	
	Object[][] a1 = {};
	String[] b1 = {"id_Taxi:","Número de placas:","Estado:","Comentarios:","Puntuación"};
	Object[][] a2 = {};
	
	String[] b2 = {"id_Taxista:","Nombre:","Apellidos:","Licencia enlace:","Telefono:","Foto enlace:","Estado:","Comentarios:","Puntuación:"};
	/*Object[][] a3 = {};
	String[] b3 = {};
	Object[][] a4 = {};
	String[] b4 = {};
	*/
	
	private Connection conexion=null;
    ResultSet resultado;
    JPanel content = new JPanel();
    JPanel v = new JPanel();
    Statement sentencia;
    JComboBox cbPersonas;
    PanelTabla pt,ptt;
    
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
            		if ( e.getStateChange() == ItemEvent.SELECTED ) {
            			if(e.getItem() == "Taxis") {
            				mostrarpt();
            			}if(e.getItem() == "Taxista") {
            			}
                }
            }

         });
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
        v.add(titulo);
		
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
		pt = new PanelTabla(a1,b1);
		ptt = new PanelTabla(a2,b2);
		pt.setBounds(0,0,500,200);
		ptt.setBounds(0,0,500,200);
		content.add(ptt,"ptt");
		content.add(pt,"pt");
		dtm1 = pt.getDtm();
		dtm2 = ptt.getDtm();
		
		try {
			mostrar(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mostrar(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        v.add(content);
        content.setSize(700,300);
        this.setContentPane(v);
        this.setLocationRelativeTo(null);
        this.setSize(700,400);
        this.setVisible(true);
        this.pack();
	}
	public void mostrarpt() {
		//this.content.first();
	}
	public void conexionDB() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/oaxataxi";
			conexion = DriverManager.getConnection(url,"postgres","Pacomegoma12");
			if (conexion!=null) {
				System.out.println("Conexion exitosa");
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
				break;
			case 3:
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
