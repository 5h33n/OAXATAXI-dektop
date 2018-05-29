package oaxataxiDesktop;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import recursos.Conexion;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Taxi extends JPanel {
	private JTextField modelo;
	private JTextField placas;
	private JTextField estado;
	private JTextField puntuacion;
	private JTextField taxactual;
	private String foto1, id_taxi, model, status, comentarios, puntuation, noplacas,nombre,aPaterno,aMaterno;
	private JButton editar, guardar;
	private int f;
	// VARIABLES DE DB
	private Connection conexion = null;
	ResultSet resultado,resultado2;
	Statement sentencia;
	Calendar calendario = new GregorianCalendar();
	
	public Taxi(String xd) throws SQLException, ParseException {
		setLayout(null);
		f =Integer.parseInt(xd);
		consultar(f);
		String[] modelado = { id_taxi, noplacas, model, foto1,status,  comentarios, puntuation, nombre + " "+aPaterno+" "+ aMaterno};
		
		JLabel lblNewLabel = new JLabel("Informaci\u00F3n del taxi");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblNewLabel.setBounds(45, 43, 354, 14);
		add(lblNewLabel);
		
		JLabel foto = new JLabel("foto");
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setForeground(Color.BLACK);
		foto.setBounds(45, 79, 95, 93);
		try {
			String ruta = modelado[3];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
			foto.setIcon(icono);
			foto.setText("");

		} catch (IOException e) {
			String image = "/img/sinfoto.jpg";  
			URL url = this.getClass().getResource(image);
			ImageIcon fot = new ImageIcon(url);
			Icon icono = new ImageIcon(
			
					fot.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
			foto.setIcon(icono);
			foto.setText("");
			System.out.println(e);			
		}
		add(foto);
		
		modelo = new JTextField();
		modelo.setEditable(false);
		modelo.setColumns(10);
		modelo.setBounds(251, 137, 136, 20);
		add(modelo);
		modelo.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
               
                if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z')&&(c!=' ')) {

                    e.consume();
                 
                	
                }
            }
        }
        );
		
		MaskFormatter formatter = new MaskFormatter("UUU-##-##");
		placas = new JFormattedTextField(formatter);
		placas.setText((String) null);
		placas.setEditable(false);
		placas.setColumns(10);
		placas.setBounds(251, 94, 136, 20);
		add(placas);
		
		JLabel plac = new JLabel("No_placas");
		plac.setBounds(169, 97, 72, 14);
		add(plac);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(169, 140, 72, 14);
		add(lblModelo);
		
		
		JLabel est = new JLabel("Estado");
		est.setBounds(45, 220, 46, 14);
		add(est);
		
		estado = new JTextField();
		estado.setEditable(false);
		estado.setBounds(128, 217, 128, 20);
		add(estado);
		estado.setColumns(10);
		estado.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            	if(estado.getText().length()== 20) {
            		e.consume();
            	}else {
                char c = e.getKeyChar();
                if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z')) {

                    e.consume();

                }}
            }
        }
        );
		
		JLabel lblPuntiacion = new JLabel("Puntuaci\u00F3n ");
		lblPuntiacion.setBounds(279, 220, 72, 14);
		add(lblPuntiacion);
		
		puntuacion = new JTextField();
		puntuacion.setEditable(false);
		puntuacion.setBounds(346, 217, 64, 20);
		add(puntuacion);
		puntuacion.setColumns(10);
		
		JLabel lblTaxistaactual = new JLabel("Taxista Actual");
		lblTaxistaactual.setBounds(45, 279, 95, 14);
		add(lblTaxistaactual);
		
		taxactual = new JTextField();
		taxactual.setEditable(false);
		taxactual.setBounds(128, 276, 128, 20);
		add(taxactual);
		taxactual.setColumns(10);
		
		JLabel lblComentarios = new JLabel("Comentarios:");
		lblComentarios.setBounds(45, 341, 95, 14);
		add(lblComentarios);
		
		JTextArea comentarios = new JTextArea();
		comentarios.setText((String) null);
		comentarios.setEditable(false);
		comentarios.setBounds(128, 347, 277, 110);
		add(comentarios);
		
		editar = new JButton("edi");
		editar.addMouseListener(new Click());
		editar.setBounds(402, 19, 33, 30);
		String path2 = "/img/editaru.png";  
		URL url2 = this.getClass().getResource(path2);
		ImageIcon fot = new ImageIcon(url2);
		Icon icono = new ImageIcon(
				fot.getImage().getScaledInstance(editar.getWidth(), editar.getHeight(), Image.SCALE_DEFAULT));
		editar.setIcon(icono);
		editar.setText("");
		add(editar);
		
		guardar = new JButton("gua");
		guardar.addMouseListener(new Click());
		guardar.setBounds(402, 19, 33, 30);
		String path = "/img/guardar2.png";  
		URL url = this.getClass().getResource(path);
		ImageIcon fot2 = new ImageIcon(url);
		Icon icono2 = new ImageIcon(
				fot2.getImage().getScaledInstance(guardar.getWidth(), guardar.getHeight(), Image.SCALE_DEFAULT));
		guardar.setIcon(icono2);
		guardar.setText("");
		guardar.setVisible(false);
		add(guardar);
		
		placas.setText(modelado[1]);
		modelo.setText(modelado[2]);
		estado.setText(modelado[4]);
		comentarios.setText(modelado[5]);
		puntuacion.setText(modelado[6]);
		taxactual.setText(modelado[7]);
		
		/*JFrame frame = new JFrame("Ventas");
		// frame.add(new TapJpan(a,b,e));
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);*/
       
	}
	

private class Click extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == editar) {
				editar.setVisible(false);
				guardar.setVisible(true);
				placas.setEditable(true);
				modelo.setEditable(true);
				estado.setEditable(true);
				//puntuacion.setEditable(true);
			}else if(e.getSource() == guardar) {
				 String value="";
				 value = "UPDATE oaxataxi.taxi\n" + 
				 		"   SET no_placas='"+placas.getText()+"', modelo='"+modelo.getText()+"', estado='"+estado.getText()+"' " + 
				 		" WHERE id_taxi="+ f +";";
				 guardanding(value);
				// System.out.println(value);
				 guardar.setVisible(false);
				 editar.setVisible(true);
				placas.setEditable(false);
				modelo.setEditable(false);
				estado.setEditable(false);
			  //puntuacion.setEditable(false);*/
			}
		}
	}

public void consultar(int xd) throws SQLException {
	try {
		Conexion c = new Conexion();
		conexion = c.conexionDB();
		sentencia = conexion.createStatement();
		//LocalTime justoAhora = LocalTime.now(); 	
		int hora, minutos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		String hour = String.valueOf(hora);
		String minute = String.valueOf(minutos);
		if(minutos<10) {
			 minute = String.valueOf("0"+minutos);
		}
		
		String ahorita = "'"+hour +":" +minute+"'";
		resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxi" + " WHERE id_taxi=" + xd + "");
		while (resultado.next()) {
			id_taxi = resultado.getString("id_taxi");
			noplacas = resultado.getString("no_placas");
			model = resultado.getString("modelo");
			foto1 = resultado.getString("foto");
			status = resultado.getString("estado");
			comentarios = resultado.getString("comentarios");
			puntuation = resultado.getString("puntuacion");

		}
		resultado2 =sentencia.executeQuery("SELECT  taxista.nombre, taxista.aPaterno,taxista.aMaterno from oaxataxi.taxista" + 
		" inner join oaxataxi.taxista_taxi on taxista.id_taxista = taxista_taxi.id_taxista and  taxista_taxi.hora_i <= "+ ahorita+"and taxista_taxi.hora_f >= "
				+ ""+ahorita + "");
		while (resultado2.next()) {
			nombre = resultado2.getString("nombre");
			aPaterno = resultado2.getString("aPaterno");
			aMaterno = resultado2.getString("aMaterno");
		}
		

		resultado.close();
		sentencia.close();
		conexion.close();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} finally {
		if (sentencia != null) {
			sentencia.close();
		}
	}

}

	
	public void guardanding(String value) {
		 try {
			 Conexion c = new Conexion();
				conexion = c.conexionDB();
			 sentencia = conexion.createStatement();
			 System.out.println(value);
			 sentencia.executeUpdate(value);
			 JOptionPane.showMessageDialog(this, "Valor guardado con éxito");
		 }catch (SQLException e) {
				e.printStackTrace();
		 }
	 }

/*public static void main(String[] args) throws SQLException, ParseException {
	InfoTaxi it = new InfoTaxi();
}*/
}
