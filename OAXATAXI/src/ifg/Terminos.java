package ifg;

import javax.swing.*;
import javax.swing.event.*;

import oaxataxiDesktop.Main;
import recursos.Conexion;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta es la clase donde se enuncian los terminos y las condiciones del uso del
 * sistema, asi como la creacion del usuario principal o Administrador
 * 
 * @author Davisito
 *
 */
public class Terminos extends JFrame implements ActionListener, ChangeListener {

	private JLabel etiquetaTerminos;
	private JCheckBox checkAceptar;
	private JButton botonContinuar, botonNoacepto;
	private JScrollPane scrollpane1;
	private JTextArea areaTerminos;
	String nombre = "";
	String password = "";
	String verifi = "";
	String verific = "";
	private Connection conexion = null;
	ResultSet resultado;
	ResultSet resultado2;
	Statement sentencia;
	Statement sentencia2;
	Statement sentencia3;

	/**
	 * Constructor de la clase Terminos, en el cual se crean todos los componentes de la ventana
	 */
	public Terminos() {
		setLayout(null);
		setTitle("Terminos y condiciones");
		Bienvenida ventanaBienvenida = new Bienvenida();
		nombre = ventanaBienvenida.nom;
		password = ventanaBienvenida.pass;

		etiquetaTerminos = new JLabel("TÉRMINOS Y CONDICIONES");
		etiquetaTerminos.setBounds(215, 5, 200, 30);
		etiquetaTerminos.setFont(new Font("Andale Mono", 1, 14));
		etiquetaTerminos.setForeground(new Color(0, 0, 0));
		add(etiquetaTerminos);

		areaTerminos = new JTextArea();
		areaTerminos.setEditable(false);
		areaTerminos.setFont(new Font("Andale Mono", 0, 9));
		areaTerminos.setText("\n\n          TÉRMINOS Y CONDICIONES"
				+ "\n\n            A.  La cuenta creada por el Usuario es personal e intransferible."
				+ "\n            B. El Usuario se obliga a notificar inmediatamente cualquier uso indebido de su cuenta. \n"
				+ "\n\n            Sólo recopilará información personal para procesamiento y uso en el sistema,"
				+ "\n            solo si usted voluntariamente decide ingresar la información o da su consentimiento  "
				+ "\n            expreso por medio del presente. Al hacerlo, usted acepta los siguientes términos de uso."
				+ "\n\n           Nosotros almacenaremos su información de forma segura y, por lo tanto, tomaremos todas"
				+ "\n           las medidas de precaución para proteger su información en contra de pérdida, abuso o cambios."
				+ "\n          ");
		scrollpane1 = new JScrollPane(areaTerminos);
		scrollpane1.setBounds(10, 40, 575, 200);
		add(scrollpane1);

		checkAceptar = new JCheckBox("Yo " + nombre + " Acepto");
		checkAceptar.setBounds(10, 250, 300, 30);
		checkAceptar.addChangeListener(this);
		add(checkAceptar);

		botonContinuar = new JButton("Continuar");
		botonContinuar.setBounds(10, 290, 100, 30);
		botonContinuar.addActionListener(this);
		botonContinuar.setEnabled(false);
		add(botonContinuar);

		botonNoacepto = new JButton("No Acepto");
		botonNoacepto.setBounds(120, 290, 100, 30);
		botonNoacepto.addActionListener(this);
		botonNoacepto.setEnabled(true);
		add(botonNoacepto);

	}

	/**Listener que detecta el cambio de el check box, el cual tiene dos estados
	 * cuando el check box esta seleccionado el boton "Continuar" se habilita y el boton  "no acepto" se deshabilita
	 * y cuando no esta seleccionado, el boton "Continuar" se deshabilita y el boton  "no acepto" se habilita
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		if (checkAceptar.isSelected() == true) {
			botonContinuar.setEnabled(true);
			botonNoacepto.setEnabled(false);
		} else {
			botonContinuar.setEnabled(false);
			botonNoacepto.setEnabled(true);
		}
	}

	/**
	 * Listener que es invocado cuando ocurre una accion en alguno de los botones
	 * si se ejecuta una accion en el boton "Continuar", se creara una conexion a la base de datos
	 * y se creara un nuevo usuario con los datos que el usuario ingreso anteriormente
	 * y si la accion se ejecuta en el boton "no acepto" se abrira la ventana anterior 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonContinuar) {
			String value = "";
			Conexion c = new Conexion();

			try {
				conexion = c.conexionDB();
				sentencia = conexion.createStatement();
				sentencia2 = conexion.createStatement();
				sentencia3 = conexion.createStatement();
				resultado = sentencia3.executeQuery("select * from oaxataxi.Vefiricar_Existencia('" + nombre + "');");

				while (resultado.next()) {
					verifi = resultado.getString("vefiricar_existencia");
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			 // sentencia para crear un nuevo usuario heredando del rol administrador
			
			value = "create user " + nombre + " login password " + "'" + password + "';" + "grant role_admin to "
					+ nombre + ";";
			if (verifi.equals("f")) {
				try {
					sentencia.executeUpdate(value);
					resultado2 = sentencia3.executeQuery(
							"select * from oaxataxi.cambiar_contraseña('" + nombre + "','" + password + "');");
					while (resultado2.next()) {
						verific = resultado2.getString("cambiar_contraseña");
					}
					
					 //se sobreescribe lo que tiene el archivo comprobacion para que ya no se vuelva a ejecutar
					 
					String sFichero = "src\\comprobacion.txt";
					BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
					bw.write("segundo");
					bw.close();
					JOptionPane.showMessageDialog(null, "Agregado correctamente ");
					Login l = new Login();
					this.setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "El usuario YA EXISTE ");
			}

		} else if (e.getSource() == botonNoacepto) {
			Bienvenida ventanabienvenida = new Bienvenida();
			ventanabienvenida.setBounds(0, 0, 350, 450);
			ventanabienvenida.setVisible(true);
			ventanabienvenida.setResizable(false);
			ventanabienvenida.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}

}