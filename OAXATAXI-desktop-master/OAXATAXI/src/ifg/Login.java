package ifg;

import javax.swing.*;

import recursos.Conexion;
import recursos.ConexionServer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Clase para inciar sesión, hereda de JFrame para pintarse en pantalla y que el usuario pueda ingresar
 * sus credenciales.
 * @author Oscar Eduardo López Guzman (Sheen)
 *
 */
public class Login extends JFrame {
	// Declaracion de componentes de la ventana
	private JLabel administrador, contrasena, olvidarContrasena;
	private JERoundTextField cajaAdministrador;
	private JERoundTextFieldP cajaContrasena;
	private JButton pregunta, cerrar;
	private Button iniciar;
	String verifi = "";
	//Crea una instancia de Connection
	private Connection conexion = null;
	ResultSet resultado;
	Statement sentencia;
	private Color colorAdmin = new Color(250, 244, 194);
	private Color colorFondo = new Color(255, 200, 36);
	public static String passwor;
	public static String user;
	/**
	 * Clase embebida que permite crear nuevas instancias de MouseAdapter para capturar todos los eventos 
	 * en este caso, de clicks, de la clase
	 * @author sheen
	 *
	 */
	private class Click extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == cerrar) {
				cerrar();
			}
			if (e.getSource() == iniciar) {
				/*
				 * si se le da clic en el boton iniciar, obtiene el contenido del TextField del
				 * usuario y de la contraseña despues se hace una conexion a la base de datos y
				 * se verifica si el usuario y el password es correcto si es asi se inicia la
				 * pantalla principal y si no se manda un mensaje que la contrase?a es
				 * incorrecta
				 */
				
				
				String nombre = cajaAdministrador.getText();
				String password = cajaContrasena.getText();
				
				
				/**METODO PARA CONSULTAR DE FORMA LOCAL:
				verificarnombreLocal(nombre,password);
				*/
				
				/** METODO UTILIZADO PARA ACCEDER CON phpMyAdmin de Hostinger
				 * 
				 */
				ConexionServer con = new ConexionServer();
				con.Select("select * from taxi order by id_taxi",10);
				try {
					Principal p = new Principal();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ocultar();
			}
		}
	}
	
	/**
	 * Método utilizado para realizar la consulta del login de forma local en postgresql
	 * @param nombre de usuario
	 * @param password de usuario
	 */
	public void verificarnombreLocal(String nombre, String password) {
		Conexion c = new Conexion();
		try {
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery(
					"select * from oaxataxi.verificar_password('" + nombre + "','" + password + "');");
			while (resultado.next()) {
				verifi = resultado.getString("verificar_password");
			}
			if (verifi.equals("t")) {
				passwor=password;
				user=nombre;
				Principal p = new Principal();
				ocultar();
			} else {
				JOptionPane.showMessageDialog(null, "Los Datos son incorrectos ");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Método que cierra la aplicaición
	 */
	public void cerrar() {
		System.exit(0);
	}
	/**
	 * Método que oculta el frame actual
	 */
	public void ocultar() {
		this.dispose();
	}
	/**
	 * Constructor de Login, inicializa las caracteristicas del JFrame
	 */
	public Login() {
		crearComponentes();
		this.setUndecorated(true);
		this.setSize(440, 470);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
	}
	/**
	 * Método que pinta los elementos dentro del JFrame
	 */
	public void crearComponentes() {
		this.setSize(400, 510);
		// Definicion de parametros de los componentes
		administrador = new JLabel("Administrador");
		contrasena = new JLabel("Contraseña");
		olvidarContrasena = new JLabel("Olvide mi contraseña");
		pregunta = new JButton("?");
		pregunta.setBackground(Color.blue);
		pregunta.setForeground(Color.white);
		cerrar = new JButton("X");
		cerrar.setBackground(Color.red);
		cerrar.setForeground(Color.white);
		cerrar.addMouseListener(new Click());
		iniciar = new Button(false);
		iniciar.addMouseListener(new Click());
		cajaAdministrador = new JERoundTextField();
		cajaContrasena = new JERoundTextFieldP();
		// termina la definicion de parametros de los componentes

		this.setLayout(new BorderLayout());

		// Parte superior del panel Principal sera dividio en 2 secciones arriba y abajo
		// -------1/1 y los el contenedor de ambos paneles sera el panel supPrin
		JPanel supPrin = new JPanel();
		supPrin.setLayout(new GridLayout(2, 0));
		JPanel preguntaCerrar = new JPanel();// este sera el panel que estara arriba
		JPanel sPCent = new JPanel();
		sPCent.setBackground(colorFondo);
		JPanel pC = new JPanel();// este panel contendra los botones que seran puestos a la izquierda del panel
									// preguntaCerrar
		pC.setLayout(new GridLayout(0, 2));
		preguntaCerrar.setLayout(new BorderLayout());
		JPanel supD = new JPanel();
		supD.setBackground(colorFondo);
		// Paneles superior e inferior creados

		// Crear paneles exteriores sin funciones solo colores
		JPanel prinDer = new JPanel();
		prinDer.add(new JLabel("             "));
		prinDer.setBackground(colorFondo);
		JPanel prinIzq = new JPanel();
		prinIzq.add(new JLabel("             "));
		prinIzq.setBackground(colorFondo);
		JPanel prinInf = new JPanel();
		prinInf.add(new JPanelAdmin());
		prinInf.setBackground(colorFondo);
		// Creados los paneles exteriores

		// panel donde estaran los componentes
		JPanel supCP = new JPanel();
		supCP.setBackground(colorFondo);
		supCP.setLayout(new BorderLayout());

		
		/**
		 * Asignando características los botones y TextFields
		 */
		JPanelRound centralPrincipal = new JPanelRound();
		supCP.add(centralPrincipal, BorderLayout.CENTER);

		centralPrincipal.setColorPrimario(colorAdmin);
		centralPrincipal.setColorSecundario(colorAdmin);
		centralPrincipal.setColorContorno(new Color(0, 0, 0));
		centralPrincipal.setLayout(null);

		centralPrincipal.add(administrador);
		administrador.setFont(new Font("Verdana", 0, 25));
		administrador.setForeground(Color.black);
		administrador.setBounds(85, 50, 200, 30);

		centralPrincipal.add(cajaAdministrador);
		cajaAdministrador.setBounds(40, 100, 245, 30);
		cajaAdministrador.setFont(new Font("Verdana", 0, 20));

		centralPrincipal.add(contrasena);
		contrasena.setFont(new Font("Verdana", 0, 25));
		contrasena.setForeground(Color.black);
		contrasena.setBounds(85, 160, 200, 30);

		centralPrincipal.add(cajaContrasena);
		cajaContrasena.setBounds(40, 210, 245, 30);
		cajaContrasena.setFont(new Font("Verdana", 0, 20));

		centralPrincipal.add(olvidarContrasena);
		olvidarContrasena.setFont(new Font("Verdana", 0, 15));
		olvidarContrasena.setBounds(90, 250, 300, 30);
		olvidarContrasena.setForeground(Color.blue);
		centralPrincipal.add(iniciar);

		JLabel in = new JLabel("Iniciar");
		in.setFont(new Font("Verdana", 0, 20));
		in.setBounds(140, 300, 100, 40);
		centralPrincipal.add(in);
		centralPrincipal.add(iniciar);
		iniciar.setBounds(120, 300, 100, 40);
		iniciar.setColor1(new Color(250, 244, 194));
		iniciar.setColor2(new Color(250, 244, 194));
		iniciar.setColor3(new Color(0, 0, 0));

		// termina panel de componentes

		// Añadir boton de pregunta y de cerrar
		pC.add(pregunta);
		pC.add(cerrar);
		preguntaCerrar.add(sPCent, BorderLayout.CENTER);
		preguntaCerrar.add(pC, BorderLayout.EAST);
		supPrin.add(preguntaCerrar);
		supPrin.add(supD);
		this.add(supPrin, BorderLayout.NORTH);
		this.add(prinDer, BorderLayout.WEST);
		this.add(prinIzq, BorderLayout.EAST);
		this.add(prinInf, BorderLayout.SOUTH);
		this.add(supCP, BorderLayout.CENTER);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	/**
	 * Getter de User
	 * @return String de usuario
	 */
	public static String getUser(){ 
		return user; 
		} 
	/**
	 * getter de password
	 * @return String de contraseña
	 */
	public static String getPass(){ 
		return passwor; 
		} 

}

