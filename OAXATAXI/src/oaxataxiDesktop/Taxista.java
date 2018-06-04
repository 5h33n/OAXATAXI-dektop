package oaxataxiDesktop;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import ifg.CargarFoto;
import recursos.Conexion;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 * Clase donde se mostrara la informacion de cada uno de los perfiles de los
 * taxistas
 * 
 * @author Davisito
 *
 */
public class Taxista extends JPanel {
	private JTextField nom;
	private JTextField ape;
	private JTextField ctel;
	private JTextField tel;
	private JTextField pun;
	private String foto1, id_taxista, nombre, apaterno, amaterno, licencia, telefono, c_tel, estado, comentarios,
			puntuacion, email1, fecha_nac;
	private JButton editar, guardar;
	private int f;
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// VARIABLES DE DB
	private Connection conexion = null;
	ResultSet resultado;
	Statement sentencia;
	private JTextField email;
	private JPanel cambiarf;
	private JLabel foto;
	File fichero;
	private JTextField fechanac;

	/**
	 * Constructor de la clase Taxista en el cual se definen y crean todos sus
	 * componentes
	 * 
	 * @param xd
	 *            String recibido de la clase Busqueda_c que servira como id para
	 *            las consultas
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IOException
	 */
	public Taxista(String xd) throws SQLException, ParseException, IOException {
		setLayout(null);
		f = Integer.parseInt(xd);
		consultar(f);
		String[] modelo = { id_taxista, nombre, apaterno + " " + amaterno, licencia, telefono, c_tel, foto1, estado,
				comentarios, puntuacion, email1, fecha_nac };

		JLabel lblInformacinDelTaxista = new JLabel("Informaci\u00F3n del taxista");
		lblInformacinDelTaxista.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelTaxista.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblInformacinDelTaxista.setBounds(114, 26, 221, 14);
		add(lblInformacinDelTaxista);

		foto = new JLabel("foto");
		foto.setForeground(new Color(0, 0, 0));
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setBounds(47, 60, 95, 93);
		try {

			String ruta = modelo[6];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
			foto.setIcon(icono);
			foto.setText("");

		} catch (IllegalArgumentException | IOException e) {
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

		JLabel lblNewLabel = new JLabel("Cambiar Foto");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(76, 155, 64, 20);
		add(lblNewLabel);

		cambiarf = new JPanel();
		// cambiarf.setHorizontalAlignment(SwingConstants.TRAILING);
		// cambiarf.setIcon(null);
		cambiarf.setBackground(new Color(220, 220, 220));

		cambiarf.setBounds(47, 155, 95, 20);
		// (31, 171, 95, 20)
		// (47, 60, 95, 93)
		add(cambiarf);
		cambiarf.setLayout(null);
		cambiarf.addMouseListener(new Click());

		JLabel cambiof = new JLabel("fo");
		cambiof.setBounds(0, 0, 26, 20);
		cambiarf.add(cambiof);
		cambiof.setBackground(new Color(255, 255, 255));
		String imag = "/img/cambiarfotito.png";
		URL url3 = this.getClass().getResource(imag);
		ImageIcon fotu = new ImageIcon(url3);
		Icon iconu = new ImageIcon(
				fotu.getImage().getScaledInstance(cambiof.getWidth(), cambiof.getHeight(), Image.SCALE_DEFAULT));
		cambiof.setIcon(iconu);
		cambiof.setText("");

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(170, 88, 46, 14);
		add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(170, 125, 72, 14);
		add(lblApellidos);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(47, 200, 62, 14);
		add(lblTelefono);

		JLabel lblPuntuacion = new JLabel("Puntuacion");
		lblPuntuacion.setBounds(252, 200, 73, 14);
		add(lblPuntuacion);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setBounds(47, 306, 83, 14);

		add(lblLicencia);

		JLabel fotolic = new JLabel("foto licencia");
		fotolic.setHorizontalAlignment(SwingConstants.CENTER);
		fotolic.setBounds(134, 303, 277, 93);
		try {
			String ruta = modelo[3];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(fotolic.getWidth(), fotolic.getHeight(), Image.SCALE_DEFAULT));
			fotolic.setIcon(icono);
			fotolic.setText("");

		} catch (IOException e) {
			// poner la foto de una licencia sin datos

			String image = "/img/licencia.jpg";
			URL url = this.getClass().getResource(image);
			ImageIcon fot = new ImageIcon(url);
			Icon icono = new ImageIcon(

					fot.getImage().getScaledInstance(fotolic.getWidth(), fotolic.getHeight(), Image.SCALE_DEFAULT));
			fotolic.setIcon(icono);
			fotolic.setText("");
			System.out.println(e);
		}
		add(fotolic);

		JLabel lblComentarios = new JLabel("Comentarios:");
		lblComentarios.setBounds(47, 412, 83, 14);
		add(lblComentarios);

		nom = new JTextField();
		nom.setEditable(false);
		nom.setBounds(252, 85, 147, 20);
		add(nom);
		nom.addKeyListener(new KeyAdapter() {
			/**
			 * Listener que detecta cada vez que se escribe un caracter y solo acepta
			 * mayusculas, minusculas y espacios
			 * 
			 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
			 */

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z') && (c != ' ')) {

					e.consume();

				}
			}
		});
		nom.setColumns(10);

		ape = new JTextField();
		ape.setEditable(false);
		ape.setBounds(252, 122, 147, 20);
		add(ape);
		ape.addKeyListener(new KeyAdapter() {
			/**
			 * Listener que detecta cada vez que se escribe un caracter y solo acepta
			 * mayusculas, minusculas y espacios
			 * 
			 * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z') && (c != ' ')) {

					e.consume();

				}
			}
		});
		ape.setColumns(10);

		MaskFormatter formatter1 = new MaskFormatter("+##");
		ctel = new JFormattedTextField(formatter1);
		ctel.setEditable(false);
		ctel.setBounds(100, 197, 33, 20);
		add(ctel);
		ctel.setColumns(10);

		MaskFormatter formatter = new MaskFormatter("(###) ###-####");
		tel = new JFormattedTextField(formatter);
		tel.setEditable(false);
		tel.setBounds(156, 197, 86, 20);
		add(tel);
		tel.setColumns(10);

		pun = new JTextField();
		pun.setEditable(false);
		pun.setBounds(316, 197, 83, 20);
		add(pun);
		pun.setColumns(10);

		editar = new JButton("edi");
		editar.addMouseListener(new Click());

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

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(134, 407, 277, 65);
		textArea.setText(modelo[8]);
		add(textArea);

		JLabel guion = new JLabel("-");
		guion.setFont(new Font("Tahoma", Font.BOLD, 18));
		guion.setHorizontalAlignment(SwingConstants.CENTER);
		guion.setBounds(124, 200, 46, 14);
		add(guion);

		JLabel label = new JLabel("E-mail");
		label.setBounds(47, 236, 46, 14);
		add(label);

		email = new JTextField();
		email.setText((String) null);
		email.setEditable(false);
		email.setColumns(10);
		email.setBounds(156, 233, 245, 20);
		add(email);

		fechanac = new JTextField();
		fechanac.setText((String) null);
		fechanac.setEditable(false);
		fechanac.setColumns(10);
		fechanac.setBounds(156, 272, 243, 20);
		add(fechanac);

		JLabel label_1 = new JLabel("Fecha Nacimiento");
		label_1.setBounds(47, 265, 123, 30);
		add(label_1);

		nom.setText(modelo[1]);
		ape.setText(modelo[2]);
		ctel.setText(modelo[5]);
		tel.setText(modelo[4]);

		pun.setText(modelo[9]);
		email.setText(modelo[10]);
		fechanac.setText(modelo[11]);

	}

	/**
	 * Método para la consulta de los datos del taxista segun el id
	 * 
	 * @param xd
	 *            Int que se recibre del constructor y que servira como id para la
	 *            consulta en la base de datos
	 * @throws SQLException
	 */
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
				email1 = resultado.getString("email");
				fecha_nac = resultado.getString("fecha_nacimiento");

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

	/**
	 * Clase heredada de MouseAdapter, que funciona como un Listener para detectar
	 * cuando el usuario de un clic sobre los botones, si da clic en el boton editar
	 * todos los JTextfield se pondran editar, y cuando le de clic en guardar se
	 * realizara un UPDATE en la base de datos con la nueva informacion no sin antes
	 * comprobar si el email esta escrito de forma correcta
	 * 
	 * @author Davisito
	 *
	 */
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
			} else if (e.getSource() == guardar) {
				Pattern pattern = Pattern.compile(PATTERN_EMAIL);
				Matcher matcher = pattern.matcher(email.getText());
				if (matcher.matches()) {
					String value = "";
					String[] apell = new String[2];
					String tels = tel.getText();
					String numero = "";
					for (int i = 0; i < tels.length(); i++) {
						char caracter = tels.charAt(i);
						if (isNumeric(caracter)) {
							numero += caracter;
						}
					}
					apell = ape.getText().split(" ");
					value = "UPDATE oaxataxi.taxista\n" + "   SET nombre='" + nom.getText() + "', apaterno='" + apell[0]
							+ "', amaterno='" + apell[1] + "',  telefono='" + numero + "', \n" + "       c_tel='"
							+ ctel.getText() + "',email='" + email.getText() + "',fecha_nacimiento='"
							+ fechanac.getText() + "'" + " WHERE id_taxista=" + f + ";";
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
				} else {
					alerta();
				}

			} else if (e.getSource() == cambiarf) {

				int resultado;

				CargarFoto ventana = new CargarFoto();

				FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");

				ventana.jfchCargarfoto.setFileFilter(filtro);

				resultado = ventana.jfchCargarfoto.showOpenDialog(null);

				if (JFileChooser.APPROVE_OPTION == resultado) {

					fichero = ventana.jfchCargarfoto.getSelectedFile();

					try {

						ImageIcon icon = new ImageIcon(fichero.toString());

						Icon icono = new ImageIcon(icon.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(),
								Image.SCALE_DEFAULT));

						foto.setText(null);

						foto.setIcon(icono);

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, "Error abriendo la                   imagen " + ex);

					}

				}
			}
		}
	}

	/**
	 * Método que manda un mensaje, se invoca cuando la direccion de correo no esta
	 * escrita de la forma correcta
	 */
	public void alerta() {
		JOptionPane.showMessageDialog(this, "El correo es Invalido");
	}

	/**
	 * Método que ejecuta una sentencia de tipo UPDATE con los datos que el usuario
	 * edito
	 * 
	 * @param value
	 *            String recibido al momento de dar clic en el boton guardar (
	 *            consulta UPDATE)
	 */
	public void guardanding(String value) {
		try {
			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			sentencia.executeUpdate(value);
			JOptionPane.showMessageDialog(this, "Valor guardado con Ã©xito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para comprobar si un caracter es numero
	 * 
	 * @param caracter
	 *            CHAR recibido para comprobar si es numero o no, esto es al momento
	 *            de ejecutarse el método KeyTyped
	 * @return regresa true si el caracter ingresado es numero y false si no lo es
	 */
	private boolean isNumeric(char caracter) {
		try {
			Integer.parseInt(String.valueOf(caracter));
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
