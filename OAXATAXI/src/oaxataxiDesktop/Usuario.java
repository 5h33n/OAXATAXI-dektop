package oaxataxiDesktop;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import ifg.CargarFoto;
import recursos.Conexion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DropMode;

public class Usuario extends JPanel {
	private JTextField apellidos;
	private JTextField nombre;
	private JTextField nickname;
	private JTextField tel;
	private JTextField estado;
	private JTextField ctel;
	private JTextField rfc;
	private JTextField sexo;
	private JTextField fechanac;
	private JTextField email;
	private JPanel cambiarf;
	File fichero;
	private String foto1, id_usuario, nomb, apaterno, amaterno, nicknam, rf, tele, c_tel, sex, correo, fecha_nacimiento,
			status;
	private JButton editar, guardar;
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private int f;
	private JLabel label_1;
	private JFormattedTextField telef;
	// VARIABLES DE DB
	private Connection conexion = null;
	ResultSet resultado;
	Statement sentencia;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public Usuario(String xd) throws SQLException, ParseException {
		setLayout(null);
		f = Integer.parseInt(xd);
		consultar(f);
		String[] modelo = { nicknam, nomb, apaterno + " " + amaterno, c_tel, tele, rf, foto1, sex, correo,
				fecha_nacimiento, status };

		JLabel lblInformacinDelUsuario = new JLabel("Informaci\u00F3n del Usuario");
		lblInformacinDelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelUsuario.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblInformacinDelUsuario.setBounds(55, 47, 354, 14);
		add(lblInformacinDelUsuario);

		label_1 = new JLabel("foto");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(31, 74, 95, 93);
		try {
			String ruta = modelo[6];
			URL url = new URL(ruta);
			Image image = ImageIO.read(url);
			ImageIcon fot = new ImageIcon(image);
			Icon icono = new ImageIcon(
					fot.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
			label_1.setIcon(icono);
			label_1.setText("");

		} catch (IOException e) {
			String image = "/img/sinfoto.jpg";
			URL url = this.getClass().getResource(image);
			ImageIcon fot = new ImageIcon(url);
			Icon icono = new ImageIcon(

					fot.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
			label_1.setIcon(icono);
			label_1.setText("");
			System.out.println(e);

		}
		add(label_1);

		JLabel lblNewLabel = new JLabel("Cambiar Foto");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(62, 172, 64, 20);
		add(lblNewLabel);

		cambiarf = new JPanel();
		// cambiarf.setHorizontalAlignment(SwingConstants.TRAILING);
		// cambiarf.setIcon(null);
		cambiarf.setBackground(new Color(220, 220, 220));
		cambiarf.setBounds(31, 171, 95, 20);
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

		apellidos = new JTextField();
		apellidos.setEditable(false);
		apellidos.setColumns(10);
		apellidos.setBounds(225, 168, 184, 20);
		add(apellidos);
		apellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z') && (c != ' ')) {

					e.consume();

				}
			}
		});

		nombre = new JTextField();
		nombre.setText((String) null);
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setBounds(225, 131, 184, 20);
		add(nombre);
		nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z') && (c != ' ')) {

					e.consume();

				}
			}
		});

		JLabel nom = new JLabel("Nombre");
		nom.setBounds(166, 134, 46, 14);
		add(nom);
		JLabel ap = new JLabel("Apellidos");
		ap.setBounds(166, 171, 72, 14);
		add(ap);

		JLabel lblNickname = new JLabel("Usuario");
		lblNickname.setBounds(166, 95, 57, 14);
		add(lblNickname);

		nickname = new JTextField();
		nickname.setEditable(false);
		nickname.setBounds(225, 92, 184, 20);
		add(nickname);
		nickname.setColumns(10);

		MaskFormatter formatter = new MaskFormatter("(###) ###-####");

		JLabel guion = new JLabel("-");
		guion.setHorizontalAlignment(SwingConstants.CENTER);
		guion.setFont(new Font("Tahoma", Font.BOLD, 18));
		guion.setBounds(202, 311, 46, 14);
		add(guion);
		MaskFormatter formatter1 = new MaskFormatter("+##");
		ctel = new JFormattedTextField(formatter1);
		ctel.setText((String) null);
		ctel.setEditable(false);
		ctel.setColumns(10);
		ctel.setBounds(166, 308, 33, 20);
		add(ctel);

		JLabel label_2 = new JLabel("Telefono");
		label_2.setBounds(55, 311, 62, 14);
		add(label_2);

		JLabel lblRfc = new JLabel("RFC");
		lblRfc.setBounds(55, 361, 46, 14);
		add(lblRfc);

		MaskFormatter formatter2 = new MaskFormatter("UUUU#######AAA");
		rfc = new JFormattedTextField(formatter2);
		rfc.setEditable(false);
		rfc.setBounds(166, 358, 243, 20);
		add(rfc);
		rfc.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(276, 263, 46, 14);
		add(lblSexo);

		sexo = new JTextField();
		sexo.setEditable(false);
		sexo.setBounds(323, 260, 86, 20);
		add(sexo);
		sexo.setColumns(10);
		sexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (sexo.getText().length() == 10) {
					e.consume();
				} else {
					char c = e.getKeyChar();
					if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z')) {

						e.consume();

					}
				}
			}
		});

		JLabel fn = new JLabel("Fecha Nacimiento");
		fn.setBounds(55, 418, 123, 14);
		add(fn);

		fechanac = new JTextField();
		fechanac.setEditable(false);
		fechanac.setBounds(166, 415, 243, 20);
		add(fechanac);
		fechanac.setColumns(10);

		JLabel ema = new JLabel("E-mail");
		ema.setBounds(55, 217, 46, 14);
		add(ema);

		email = new JTextField();
		email.setEditable(false);
		email.setBounds(166, 214, 243, 20);
		add(email);
		email.setColumns(10);

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

		telef = new JFormattedTextField(formatter);
		telef.setBounds(248, 308, 161, 20);
		telef.setEditable(false);
		add(telef);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(55, 263, 46, 14);
		add(lblEstado);

		estado = new JTextField();
		estado.setEditable(false);
		estado.setBounds(166, 260, 86, 20);
		add(estado);
		estado.setColumns(10);

		nickname.setText(modelo[0]);
		nombre.setText(modelo[1]);
		apellidos.setText(modelo[2]);
		ctel.setText(modelo[3]);
		telef.setText(modelo[4]);
		rfc.setText(modelo[5]);
		sexo.setText(modelo[7]);
		email.setText(modelo[8]);
		fechanac.setText(modelo[9]);
		estado.setText(modelo[10]);

		/*
		 * JFrame frame = new JFrame("Ventas"); // frame.add(new TapJpan(a,b,e));
		 * frame.add(this); frame.pack();
		 * frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 * frame.setVisible(true);
		 */
	}

	public void consultar(int xd) throws SQLException {
		try {
			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			// int xd=Integer.parseInt(JOptionPane.showInputDialog("ID"));
			resultado = sentencia.executeQuery("SELECT * from oaxataxi.usuario" + " WHERE id_usuario=" + xd + "");
			while (resultado.next()) {
				// id_taxista = resultado.getString("id_taxista");
				nicknam = resultado.getString("nickname");
				nomb = resultado.getString("nombre");
				apaterno = resultado.getString("apaterno");
				amaterno = resultado.getString("amaterno");
				sex = resultado.getString("sexo");
				tele = resultado.getString("telefono");
				c_tel = resultado.getString("c_tel");
				foto1 = resultado.getString("foto");
				rf = resultado.getString("rfc");
				correo = resultado.getString("email");
				fecha_nacimiento = resultado.getString("fecha_nacimiento");
				status = resultado.getString("estado");

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
				nombre.setEditable(true);
				nickname.setEditable(true);
				apellidos.setEditable(true);
				ctel.setEditable(true);
				telef.setEditable(true);
				sexo.setEditable(true);
				email.setEditable(true);
				fechanac.setEditable(true);
				rfc.setEditable(true);
				estado.setEditable(true);
			} else if (e.getSource() == guardar) {

				Pattern pattern = Pattern.compile(PATTERN_EMAIL);
				Matcher matcher = pattern.matcher(email.getText());
				if (matcher.matches()) {
					String value = "";
					String[] apell = new String[2];
					apell = apellidos.getText().split(" ");
					String tels = telef.getText();
					String numero = "";
					for (int i = 0; i < tels.length(); i++) {
						char caracter = tels.charAt(i);
						if (isNumeric(caracter)) {
							numero += caracter;
						}
					}
					System.out.println(numero);
					System.out.println(apell.length);
					String rfc2 = rfc.getText().toUpperCase();
					rfc.setText(rfc2);
					value = "UPDATE oaxataxi.usuario\n" + "   SET nombre='" + nombre.getText() + "', apaterno='"
							+ apell[0] + "', amaterno='" + apell[1] + "',  telefono='" + numero + "', \n"
							+ "       c_tel='" + ctel.getText() + "' , nickname='" + nickname.getText() + "',email='"
							+ email.getText() + "',sexo='" + sexo.getText() + "'," + "fecha_nacimiento='"
							+ fechanac.getText() + "', rfc='" + rfc2 + "', " + "estado='" + estado.getText() + "'"
							+ " WHERE id_usuario=" + f + ";";
					email.setBorder(UIManager.getBorder("TextField.border"));
					guardanding(value);
					System.out.println(value);
					guardar.setVisible(false);
					editar.setVisible(true);
					nombre.setEditable(false);
					nickname.setEditable(false);
					apellidos.setEditable(false);
					ctel.setEditable(false);
					telef.setEditable(false);
					sexo.setEditable(false);
					email.setEditable(false);
					fechanac.setEditable(false);
					rfc.setEditable(false);
					estado.setEditable(false);

				} else {
					JOptionPane.showMessageDialog(null, "El correo es Invalido");
					email.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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

						Icon icono = new ImageIcon(icon.getImage().getScaledInstance(label_1.getWidth(),
								label_1.getHeight(), Image.SCALE_DEFAULT));

						label_1.setText(null);

						label_1.setIcon(icono);

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, "Error abriendo la                   imagen " + ex);

					}

				}
			}
		}
	}

	private boolean isNumeric(char caracter) {
		try {
			Integer.parseInt(String.valueOf(caracter));
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public void guardanding(String value) {
		try {
			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			sentencia.executeUpdate(value);
			JOptionPane.showMessageDialog(null, "Valor guardado con éxito");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/*
	 * public static void main(String[] args) throws SQLException, ParseException {
	 * InfoUsuario iu = new InfoUsuario(); }
	 */
}
