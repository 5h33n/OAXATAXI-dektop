package ifg;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import recursos.Conexion;

public class Database extends JFrame {
	public DefaultTableModel dtm1;
	public DefaultTableModel dtm2;
	public DefaultTableModel dtm3;
	public DefaultTableModel dtm4;
	Conexion c = new Conexion();
	Object[][] a1 = {};
	String[] b1 = {"id_Taxi:","Número de placas:","Modelo:","Foto:","Estado:","Comentarios:","Puntuación"};

	
	String[] b2 = {"id_Taxista:","Nombre:","Apellidos:","Licencia enlace:","Email:","Telefono:","Fecha de nacimiento:","Foto enlace:","Estado:","Comentarios:","Puntuación:"};
	String[] b3 = {"id_Usuario:","Nombre:","Apellidos:","Nickname","Foto enlace:","Estado","RFC:","Telefono:","Sexo:","Email:","Fecha de nacimiento:"};
	String[] b4 = {"id_viaje","id_taxista","Conductor","Placas","Usuario","Estado","Hora inicio","Hora final","Origen","Destino","Monto"};

	
	private Connection conexion=null;
    ResultSet resultado;
    JPanel content = new JPanel();
    JPanel v = new JPanel();
    Statement sentencia;
    JComboBox cbPersonas;
    PanelTabla pt,ptt,pu,pv;
    public JButton del = new JButton("Eliminar");
    public JButton act = new JButton("Editar");
    private JLabel titulo = new JLabel("Base de datos");
    public int anterior;
    
	public Database() {
		del.addMouseListener(new Click());
		act.addMouseListener(new Click());
		v.setLayout(new BoxLayout(v,BoxLayout.Y_AXIS));
		content.setLayout(new CardLayout());
		String [] unidades = {"Taxis","Taxistas","Usuarios","Viajes"};
        cbPersonas = new JComboBox(unidades);
        v.add(cbPersonas);
        cbPersonas.addItemListener( new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
            	CardLayout cl = (CardLayout)(content.getLayout());
            		String item = (String)e.getItem();
                cl.show(content, item);
                del.setEnabled(false);
                act.setEnabled(false);
            }
         });
        
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
        v.add(titulo);
		
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
		pt = new PanelTabla(a1,b1,this);
		ptt = new PanelTabla(a1,b2,this);
		pu = new PanelTabla(a1,b3,this);
		pv = new PanelTabla(a1,b4,this);
		//pt.setBounds(0,0,500,200);
		//ptt.setBounds(0,0,500,200);
		content.add(pt,"Taxis");
		content.add(ptt,"Taxistas");
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
        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout());
        del.setEnabled(false);
        act.setEnabled(false);
        botones.add(del);
        botones.add(act);
        v.add(botones);
        this.setContentPane(v);
        //this.setLocationRelativeTo(null);
        
        this.setSize(1200,400);
        this.setBounds(250,200,1200,500);
        this.setVisible(true);
        this.pack();
	}
	 private class Click extends MouseAdapter{
	    	public void mouseClicked(MouseEvent e) {
	    		if(e.getSource() == del && del.isEnabled()){
	    			
	    			int r = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar este elemento?","Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    			if(r==0) {
	    				borrarFila();
	    			}
	    			
		    	}if(e.getSource() == act && act.isEnabled()){
		    		if(cbPersonas.getSelectedItem().equals("Taxis")) {
		    			guardar(pt.getFields());
		    		}else {
		    			if(ptt.getFields()[2].getText().split(" ").length != 2 && act.getText().equals("Guardar")) {
		    				//System.out.println(ptt.getFields()[2].getText());
		    				JOptionPane.showMessageDialog(null, "El campo 'Apellidos' requiere dos apellidos separados por un espacio");
		    				ptt.getFields()[2].requestFocus();
		    				
		    			}else {
		    				guardar(ptt.getFields());
		    			}
		    			
		    		}
	    			
		    	}
	    	}
	  }
	 public void guardar(JTextField[] fields) {
		 if(act.getText().equals("Editar")) {
			 act.setText("Guardar");
			 if(cbPersonas.getSelectedItem().equals("Taxis")) {
				 for(int a=2;a<fields.length;a++) {
					 fields[a].setEditable(true);
				 }
			 }else if(cbPersonas.getSelectedItem().equals("Taxistas")) {
				 for(int a=1;a<fields.length;a++) {
					 fields[a].setEditable(true);
				 }
			 }
		 }else{
			 act.setText("Editar");
			 if(cbPersonas.getSelectedItem().equals("Taxis")) {
				 for(int a=2;a<fields.length;a++) {
					 fields[a].setEditable(false);
					 dtm1.setValueAt(fields[a].getText(), anterior, a);
				 }
				 String value="";
				 
				 value = "UPDATE oaxataxi.taxi\n" + 
				 		"   SET modelo='"+fields[2].getText()+"', foto='"+fields[3].getText()+"', estado='"+fields[4].getText()+"', comentarios='"+fields[5].getText()+"', puntuacion="+fields[6].getText()+"\n" + 
				 		" WHERE id_taxi="+fields[0].getText()+";";
				 guardanding(value);
			 }else if(cbPersonas.getSelectedItem().equals("Taxistas")) {
				 for(int a=1;a<fields.length;a++) {
					 fields[a].setEditable(false);
					 dtm2.setValueAt(fields[a].getText(), anterior, a);
				 }	
				 String value="";
				 String ctel="",tel="";
				 
				 //falta comprobacion de apellido y numero para generalizar
				 String[] apellidos = new String[2];
				 apellidos = fields[2].getText().split(" ");
				 ctel = fields[5].getText().substring(0,3);
				 tel = fields[5].getText().substring(3);
				 /*
				 for(int b=0;b<fields.length;b++) {
					 System.out.println(fields[b].getText());
				 }
				 */
				 value = "UPDATE oaxataxi.taxista\n" + 
				 		"   SET nombre='"+fields[1].getText()+"', apaterno='"+apellidos[0]+"', amaterno='"+apellidos[1]+"', licencia='"+fields[3].getText()+"', email='"+fields[4].getText()+"', telefono='"+tel+"', \n" + 
				 		"       c_tel='"+ctel+"', fecha_nacimiento='"+fields[6].getText()+"', foto='"+fields[7].getText()+"', estado='"+fields[8].getText()+"', comentarios='"+fields[9].getText()+"', puntuacion="+fields[10].getText()+"\n" + 
				 		" WHERE id_taxista="+fields[0].getText()+";";
				 guardanding(value);
			 }
		 }
	 }
	 public void guardanding(String value) {
		 try {
			 System.out.println(value);
			 conexion = c.conexionDB();
			 sentencia = conexion.createStatement();
			 sentencia.executeUpdate(value);
			 JOptionPane.showMessageDialog(null, "Valor guardado con éxito");
		 }catch (SQLException e) {
				e.printStackTrace();
		 }
	 }
	 public void borrarFila(){
		 for(int a=1;a<pv.getFields().length;a++) {
			 pv.getFields()[a].setText("");
		 }
		 int id;
		 id = Integer.parseInt(pv.getFields()[0].getText());
		 try {
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			String s = "delete from oaxataxi.taxista_viaje_taxi where id_viaje="+id+";";
			
		
			sentencia.executeUpdate(s);
			s = "delete from oaxataxi.viaje where id_viaje="+id+";";
			sentencia.executeUpdate(s);
			JOptionPane.showMessageDialog(null, "Valor eliminado correctamente");
			//sentencia.execute(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 dtm4.removeRow(pv.filaseleccionada());
 	}
    public void mostrar(int n) throws SQLException {
    	try {
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			switch(n) {
			case 0:
				
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.taxi order by taxi.id_taxi");
				while ( resultado.next() ) {
					String id_taxi = resultado.getString("id_taxi");
					String no_placas = resultado.getString("no_placas");
					String estado = resultado.getString("estado");
					String model = resultado.getString("modelo");
					String foto = resultado.getString("foto");
					String comentarios = resultado.getString("comentarios");
					String puntuacion = resultado.getString("puntuacion");
		    			String [] modelo={id_taxi,no_placas,model,foto,estado,comentarios,puntuacion};
		    			dtm1.addRow(modelo);
		         }
				break;
			case 1:
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.taxista order by taxista.id_taxista");
				while ( resultado.next() ) {
					String id_taxista = resultado.getString("id_taxista");
					String nombre = resultado.getString("nombre");
					String apaterno = resultado.getString("apaterno");
					String amaterno = resultado.getString("amaterno");
					String licencia = resultado.getString("licencia");
					String email = resultado.getString("email");
					String telefono = resultado.getString("telefono");
					String c_tel = resultado.getString("c_tel");
					String fecha_nacimiento = resultado.getString("fecha_nacimiento");
					String foto = resultado.getString("foto");
					String estado = resultado.getString("estado");
					String comentarios = resultado.getString("comentarios");
					String puntuacion = resultado.getString("puntuacion");
		    			String [] modelo={id_taxista,nombre,apaterno+" "+amaterno,licencia,email,c_tel+telefono,fecha_nacimiento,foto,estado,comentarios,puntuacion};
		    			dtm2.addRow(modelo);
		         }
				break;
			case 2:
				conexion = c.conexionDB();
				sentencia = conexion.createStatement();
				resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.usuario order by usuario.id_usuario");
				while ( resultado.next() ) {
					String id_usuario = resultado.getString("id_usuario");
					String nickname = resultado.getString("nickname");
					String nombre = resultado.getString("nombre");
		    		String apaterno = resultado.getString("apaterno");
		    		String amaterno = resultado.getString("amaterno");
		    		String foto = resultado.getString("rfc");
		    		String rfc = resultado.getString("rfc");
		    		String estado = resultado.getString("estado");
		    		String telefono = resultado.getString("telefono");
		    		String c_tel = resultado.getString("c_tel");
		    		String sexo = resultado.getString("sexo");
		    		String email = resultado.getString("email");
		    		String fecha_nacimiento = resultado.getString("fecha_nacimiento");
		    		String [] modelo={id_usuario,nombre,apaterno + " " + amaterno,nickname,foto,estado,rfc,c_tel + telefono,sexo,email,fecha_nacimiento};
		    		dtm3.addRow(modelo);
		    		
		         }
				
				break;
			case 3:
				conexion = c.conexionDB();
				sentencia = conexion.createStatement();
				resultado = sentencia.executeQuery("SELECT taxista_viaje_taxi.id_viaje,no_placas,nickname_u,hora_inicio,hora_final,origen,destino,viaje.estado,monto_pagado,taxista_viaje_taxi.id_taxista,nombre,apaterno FROM oaxataxi.taxista_viaje_taxi INNER JOIN oaxataxi.viaje ON taxista_viaje_taxi.id_viaje = viaje.id_viaje INNER JOIN oaxataxi.taxista ON taxista_viaje_taxi.id_taxista = taxista.id_taxista order by viaje.id_viaje");
				while ( resultado.next() ) {
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
		    		String [] modelo={id_viaje,id_taxista,nombre+" "+apaterno,no_placas,nickname,estado,hora_inicio,hora_final,origen,destino,"$"+monto_pagado};
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
