package oaxataxiDesktop;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import recursos.Conexion;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Taxista extends JPanel {
	private JTextField nom;
	private JTextField ape;
	private JTextField ctel;
	private JTextField tel;
	private JTextField pun;
	private String foto1, id_taxista, nombre, apaterno, amaterno, licencia, telefono, c_tel, estado, comentarios,
			puntuacion;
	private JButton editar, guardar;
	private int xd;
	
	// VARIABLES DE DB
	private Connection conexion = null;
	ResultSet resultado;
	Statement sentencia;
	private JTextField email;
	private JTextField fechanac;

	public Taxista() throws SQLException, ParseException, IOException {
		setLayout(null);
		 xd = Integer.parseInt(JOptionPane.showInputDialog("ID"));
		consultar(xd);
		String[] modelo = { id_taxista, nombre, apaterno + " " + amaterno, licencia, telefono, c_tel, foto1, estado,
				comentarios, puntuacion };

		JLabel lblInformacinDelTaxista = new JLabel("Informaci\u00F3n del taxista");
		lblInformacinDelTaxista.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelTaxista.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblInformacinDelTaxista.setBounds(114, 26, 221, 14);
		add(lblInformacinDelTaxista);

		JLabel foto = new JLabel("foto");
		foto.setForeground(new Color(0, 0, 0));
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setBounds(47, 60, 95, 93);
		/* try {
			
			String ruta = modelo[6];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
			foto.setIcon(icono);
			foto.setText("");
			

		} catch (IllegalArgumentException | IOException e) { 
		*/
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/sinfoto.jpg"));
		foto.setIcon(icono);
			foto.setText("");
			//System.out.println(e);
		/* } */
		add(foto);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(170, 71, 46, 14);
		add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(170, 114, 72, 14);
		add(lblApellidos);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(47, 164, 62, 14);
		add(lblTelefono);

		JLabel lblPuntuacion = new JLabel("Puntuacion");
		lblPuntuacion.setBounds(252, 164, 73, 14);
		add(lblPuntuacion);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setBounds(47, 297, 83, 14);

		add(lblLicencia);

		JLabel fotolic = new JLabel("foto licencia");
		fotolic.setHorizontalAlignment(SwingConstants.CENTER);
		fotolic.setBounds(134, 292, 277, 104);
		/* try {
			String ruta = modelo[3];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(fotolic.getWidth(), fotolic.getHeight(), Image.SCALE_DEFAULT));
			fotolic.setIcon(icono);
			fotolic.setText("");

		} catch (IOException e) { */
			// poner la foto de una licencia sin datos
			//
			//
			//
			ImageIcon iconol = new ImageIcon(getClass().getResource("/img/licencia.jpg"));fotolic.setIcon(iconol);
			fotolic.setIcon(iconol);
			fotolic.setText("");
			//System.out.println(e);
		/*} */
		add(fotolic);

		JLabel lblComentarios = new JLabel("Comentarios:");
		lblComentarios.setBounds(47, 412, 83, 14);
		add(lblComentarios);

		nom = new JTextField();
		nom.setEditable(false);
		nom.setBounds(252, 68, 136, 20);
		add(nom);
		nom.addKeyListener(
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
		nom.setColumns(10);

		ape = new JTextField();
		ape.setEditable(false);
		ape.setBounds(252, 111, 136, 20);
		add(ape);
		ape.addKeyListener(
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
		ape.setColumns(10);

		MaskFormatter formatter1 = new MaskFormatter("+##");
		ctel = new JFormattedTextField(formatter1);
		ctel.setEditable(false);
		ctel.setBounds(102, 164, 33, 20);
		add(ctel);
		ctel.setColumns(10);

		MaskFormatter formatter = new MaskFormatter("(###) ###-####");
		tel = new JFormattedTextField(formatter);
		tel.setEditable(false);
		tel.setBounds(156, 164, 86, 20);
		add(tel);
		tel.setColumns(10);

		pun = new JTextField();
		pun.setEditable(false);
		pun.setBounds(316, 164, 83, 20);
		add(pun);
		pun.setColumns(10);
		nom.setText(modelo[1]);
		ape.setText(modelo[2]);
		ctel.setText(modelo[5]);
		tel.setText(modelo[4]);

		pun.setText(modelo[9]);

		editar = new JButton("edi");
		editar.addMouseListener(new Click());
		
		editar.setBounds(402, 19, 33, 30);
		ImageIcon iconoe = new ImageIcon(getClass().getResource("/img/editaru.png"));editar.setIcon(iconoe);
		editar.setIcon(iconoe);
		editar.setText("");
		add(editar);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(134, 407, 277, 93);
		textArea.setText(modelo[8]);
		add(textArea);

		guardar = new JButton("gua");
		guardar.addMouseListener(new Click());
		guardar.setBounds(402, 19, 33, 30);
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/guardar2.png"));guardar.setIcon(icono2);
		guardar.setText("");
		guardar.setVisible(false);
		add(guardar);

		JLabel guion = new JLabel("-");
		guion.setFont(new Font("Tahoma", Font.BOLD, 18));
		guion.setHorizontalAlignment(SwingConstants.CENTER);
		guion.setBounds(119, 164, 46, 14);
		add(guion);
		
		JLabel label = new JLabel("E-mail");
		label.setBounds(47, 206, 46, 14);
		add(label);
		
		email = new JTextField();
		email.setText((String) null);
		email.setEditable(false);
		email.setColumns(10);
		email.setBounds(156, 203, 245, 20);
		add(email);
		
		fechanac = new JTextField();
		fechanac.setText((String) null);
		fechanac.setEditable(false);
		fechanac.setColumns(10);
		fechanac.setBounds(156, 244, 243, 20);
		add(fechanac);
		
		JLabel label_1 = new JLabel("Fecha de Nacimiento");
		label_1.setBounds(45, 247, 123, 14);
		add(label_1);
	/*	JFrame frame = new JFrame("Ventas");
		// frame.add(new TapJpan(a,b,e));
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);*/

	}

	public void consultar(int xd) throws SQLException {
		try {
			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			// int xd=Integer.parseInt(JOptionPane.showInputDialog("ID"));
			resultado = sentencia.executeQuery("SELECT * from oaxataxi.taxista" + " WHERE id_taxista=" + xd + "");
			while (resultado.next()) {
				id_taxista = resultado.getString("id_taxista");
				nombre = resultado.getString("nombre");
				apaterno = resultado.getString("apaterno");
				amaterno = resultado.getString("amaterno");
				licencia = resultado.getString("licencia");
				telefono = resultado.getString("telefono");
				c_tel = resultado.getString("c_tel");
				foto1 = resultado.getString("foto");
				estado = resultado.getString("estado");
				comentarios = resultado.getString("comentarios");
				puntuacion = resultado.getString("puntuacion");

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
	private class Click extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == editar) {
				editar.setVisible(false);
				guardar.setVisible(true);
				nom.setEditable(true);
				ape.setEditable(true);
				ctel.setEditable(true);
				tel.setEditable(true);
				email.setEditable(true);
				fechanac.setEditable(true);
			}else if(e.getSource() == guardar) {
				 String value="";
				 String[] apell = new String[2];
				 String tels = tel.getText();
					String numero = "";
					for (int i = 0; i < tels.length(); i++)
					{
						char caracter = tels.charAt(i);
						if(isNumeric(caracter))
						{
						numero += caracter;
						}
					}
					System.out.println(numero);
				 apell = ape.getText().split(" ");	
				 System.out.println(apell.length);
				 value = "UPDATE oaxataxi.taxista\n" + 
				 		"   SET nombre='"+nom.getText()+"', apaterno='"+apell[0]+"', amaterno='"+apell[1]+"',  telefono='"+ numero+"', \n" + 
				 		"       c_tel='"+ctel.getText()+"',c_tel='"+email.getText()+"',c_tel='"+fechanac.getText()+"'" + 
				 		" WHERE id_taxista="+xd+";";
				 guardanding(value);
				 System.out.println(value);
				 guardar.setVisible(false);
				 editar.setVisible(true);
				 nom.setEditable(false);
					ape.setEditable(false);
					ctel.setEditable(false);
					tel.setEditable(false);
					email.setEditable(false);
					fechanac.setEditable(false);
			}
		}
	}
	
	public void guardanding(String value) {
		 try {
			 Conexion c = new Conexion();
			 conexion = c.conexionDB();
			 sentencia = conexion.createStatement();
			 sentencia.executeUpdate(value);
			 JOptionPane.showMessageDialog(null, "Valor guardado con éxito");
		 }catch (SQLException e) {
				e.printStackTrace();
		 }
	 }
	private  boolean isNumeric(char caracter){
		try {
		Integer.parseInt(String.valueOf(caracter));
		return true;
		} catch (NumberFormatException ex){
		return false;
		}
		}

	/*public static void main(String[] args) throws SQLException, ParseException {
		InfoTaxista it = new InfoTaxista();
	}*/
}
