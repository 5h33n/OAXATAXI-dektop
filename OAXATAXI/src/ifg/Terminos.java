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
		areaTerminos.setText("\n\n          TÉRMINOS Y CONDICIONES" + "\n\n            A.  La cuenta creada por el Usuario es personal e intransferible."
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

	public void stateChanged(ChangeEvent e) {
		if (checkAceptar.isSelected() == true) {
			botonContinuar.setEnabled(true);
			botonNoacepto.setEnabled(false);
		} else {
			botonContinuar.setEnabled(false);
			botonNoacepto.setEnabled(true);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonContinuar) {
			String value = "";
			String value2 = "";
			Conexion c = new Conexion();
					
						try {
							conexion = c.conexionDB();
						    sentencia = conexion.createStatement();
					        sentencia2 = conexion.createStatement();
                            sentencia3 = conexion.createStatement();	
						    resultado = sentencia3.executeQuery(
						    		 "select * from oaxataxi.Vefiricar_Existencia('"+nombre+"');");
				
						while (resultado.next()) {
							 verifi = resultado.getString("vefiricar_existencia");
						}
						
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						value = "create user " + nombre + " login password " + "'" + password + "';"
								+ "grant role_admin to " + nombre + ";";
						if (verifi.equals("f")) {
							try {
								sentencia.executeUpdate(value);
								resultado2 = sentencia3.executeQuery("select * from oaxataxi.cambiar_contraseña('" + nombre + "','" + password + "');");
								while (resultado2.next()) {
									 verific = resultado2.getString("cambiar_contraseña");
								}
								//	sentencia2.executeQuery(value2);
								//System.out.println(value2);
								String sFichero="src\\comprobacion.txt";
								BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
								bw.write("segundo");
								bw.close();
								JOptionPane.showMessageDialog(null, "Agregado correctamente ");	
								Login l= new Login();
								this.setVisible(false);
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "El usuario YA EXISTE ");
						}
				/*	
					
					System.out.println(verifi);
					if (verifi.equals("f")) {
					System.out.println(value);
					try {
						sentencia.executeUpdate(value);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("simon");
					}else {
					System.out.println(value2);}*/
					
		}
/*
			System.out.println(verifi);
			if (verifi.equals("f")) {
				value = "create user " + nombre + " login password " + "'" + password + "';\r\n"
						+ "grant role_admin to " + nombre + ";";
				value2 = "select * from cambiar_contraseña('" + nombre + "','" + password + "');";
					try {
						System.out.println(value);
						sentencia.executeUpdate(value);
						sentencia2.executeQuery(value2);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Agregado correctamente ");
				// login l= new login();
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
		}*/
	}

	

	public static void main(String args[]) {
		Terminos ventanalicencia = new Terminos();
		ventanalicencia.setBounds(0, 0, 600, 360);
		ventanalicencia.setVisible(true);
		ventanalicencia.setResizable(false);
		ventanalicencia.setLocationRelativeTo(null);
	}
}