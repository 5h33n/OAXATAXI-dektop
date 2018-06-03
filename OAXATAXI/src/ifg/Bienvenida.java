package ifg;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

public class Bienvenida extends JFrame implements ActionListener {

	private JTextField cajaNombreUsuario;
	private JLabel imagenTitulo, etiquetaPrimerInicio, etiquetaNombreUsuario, label4;
	private JButton botonCrear;
	public static String nom = "";
	public static String pass = "";
	private JLabel lblIngreseUnaContrasea;
	private JPasswordField cajaPass1;
	private JPasswordField cajaPass2;
	private int i = 0;
	private String passString2 = "";
	private String passString = "";
	private String auxPass = "";
	private static final String PATTERN_EMAIL = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";

	public Bienvenida() {
		getContentPane().setLayout(null);
		setTitle("oaxataxi");
		getContentPane().setBackground(new Color(255, 250, 205));
		// setIconImage(new
		// ImageIcon(getClass().getResource("img/icon.png")).getImage());

		ImageIcon imagen = new ImageIcon("img/titulob3.png");
		imagenTitulo = new JLabel(new ImageIcon(Bienvenida.class.getResource("/img/titulob3.png")));
		imagenTitulo.setBackground(new Color(0, 0, 0));
		imagenTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
		imagenTitulo.setText("");
		imagenTitulo.setBounds(10, 15, 325, 137);
		getContentPane().add(imagenTitulo);

		etiquetaPrimerInicio = new JLabel("primer inicio de sesi\u00F3n");
		etiquetaPrimerInicio.setBackground(new Color(0, 0, 0));
		etiquetaPrimerInicio.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaPrimerInicio.setBounds(10, 151, 325, 30);
		etiquetaPrimerInicio.setFont(new Font("Adobe Caslon Pro", Font.BOLD | Font.ITALIC, 18));
		etiquetaPrimerInicio.setForeground(new Color(0, 0, 0));
		getContentPane().add(etiquetaPrimerInicio);

		etiquetaNombreUsuario = new JLabel("Ingrese un nombre de usuario");
		etiquetaNombreUsuario.setBounds(45, 192, 200, 30);
		etiquetaNombreUsuario.setFont(new Font("Dialog", Font.BOLD, 12));
		etiquetaNombreUsuario.setForeground(new Color(0, 0, 0));
		getContentPane().add(etiquetaNombreUsuario);

		label4 = new JLabel("Este inicio de sesi\u00F3n solo ser\u00E1 una unica  vez ");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setBounds(51, 426, 274, 30);
		label4.setFont(new Font("Dialog", Font.BOLD, 12));
		label4.setForeground(new Color(0, 0, 0));
		getContentPane().add(label4);

		JLabel lblNewLabel = new JLabel("icon");
		// lblNewLabel.setIcon(new
		// ImageIcon(Bienvenida.class.getResource("/img/infous.png")));
		lblNewLabel.setBounds(37, 432, 17, 20);
		String path2 = "/img/infu.png";
		URL url2 = this.getClass().getResource(path2);
		ImageIcon fot = new ImageIcon(url2);
		Icon icono = new ImageIcon(
				fot.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);
		lblNewLabel.setText("");
		getContentPane().add(lblNewLabel);

		cajaNombreUsuario = new JTextField();
		cajaNombreUsuario.setBounds(45, 215, 255, 25);
		cajaNombreUsuario.setBackground(new Color(255, 255, 255));
		cajaNombreUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
		cajaNombreUsuario.setForeground(new Color(0, 0, 0));
		cajaNombreUsuario.setToolTipText("puedes ingresar lo que quieras no hay pedo");
		getContentPane().add(cajaNombreUsuario);

		botonCrear = new JButton("Crear");
		botonCrear.setBounds(127, 351, 100, 30);
		botonCrear.setBackground(new Color(255, 255, 255));
		botonCrear.setFont(new Font("Dialog", Font.BOLD, 14));
		botonCrear.setForeground(new Color(0, 0, 0));
		botonCrear.addActionListener(this);
		getContentPane().add(botonCrear);

		lblIngreseUnaContrasea = new JLabel("Ingrese una contrase\u00F1a");
		lblIngreseUnaContrasea.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIngreseUnaContrasea.setBounds(45, 251, 255, 14);
		getContentPane().add(lblIngreseUnaContrasea);

		JLabel lblNewLabel_1 = new JLabel("Vuelve a escribir la contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(45, 301, 255, 14);
		getContentPane().add(lblNewLabel_1);

		cajaPass1 = new JPasswordField();
		cajaPass1.setBounds(45, 264, 255, 25);
		cajaPass1.setToolTipText("puedes ingresar lo que quieras no hay pedo");
		getContentPane().add(cajaPass1);

		cajaPass2 = new JPasswordField();
		cajaPass2.setFont(new Font("Dialog", Font.BOLD, 12));
		cajaPass2.setBounds(45, 315, 255, 25);
		cajaPass2.setToolTipText("Las contraseñas deben coincidir");

		getContentPane().add(cajaPass2);

		cajaPass2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char[] passChar = cajaPass1.getPassword();
				passString = String.valueOf(passChar);				
				char caracterActual = e.getKeyChar();
				passString2 = passString2 + caracterActual;
				if (passString2.length() <= passString.length()) {
					if (passString.charAt(i) == passString2.charAt(i)) {
						auxPass = passString2;
						i++;
					} else {
						cajaPass2.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
						passString2 = passString2.substring(0, passString2.length() - 1);
						auxPass = passString2;
					}
					if (passString.equals(auxPass)) {
						cajaPass2.setText(passString2);
						cajaPass2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
						cajaPass1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
						// cajaPass2.setEditable(false);
						//cajaPass1.setEditable(false);
					}

				}

			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonCrear) {
			Pattern pattern = Pattern.compile(PATTERN_EMAIL);
			String pass2completa = String.valueOf(cajaPass2.getPassword());
			Matcher matcher = pattern.matcher(pass2completa);
			if (matcher.matches()) {
				nom = cajaNombreUsuario.getText().trim();
				pass = passString;
				if (nom.equals("")) {
					JOptionPane.showMessageDialog(null, "Debes ingresar tu nombre de usuario");
				} else {
					Terminos ventanalicencia = new Terminos();
					ventanalicencia.setBounds(0, 0, 600, 360);
					ventanalicencia.setVisible(true);
					ventanalicencia.setResizable(false);
					ventanalicencia.setLocationRelativeTo(null);
					this.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"La contraseña debe contener numeros, minusculas y al menos una mayuscula");
				// email.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			}
		}
	}

	public static void main(String args[]) {
		Bienvenida ventanabienvenida = new Bienvenida();
		ventanabienvenida.setBounds(0, 0, 350, 450);
		ventanabienvenida.setVisible(true);
		ventanabienvenida.setResizable(false);
		ventanabienvenida.setLocationRelativeTo(null);
	}
}
