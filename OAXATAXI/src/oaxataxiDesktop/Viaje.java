package oaxataxiDesktop;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import recursos.Conexion;

import javax.swing.JTextField;
import java.awt.Color;

/**
 * Clase donde se mostrara la informacion de cada uno de los viajes realizados
 * 
 * @author Davisito
 */
public class Viaje extends JPanel {
	private JTextField cid;
	private JTextField chinicio;
	private JTextField chfinal;
	private JTextField cusuario;
	private JTextField cfecha;
	private JTextField corigen;
	private JTextField cdestino;
	private JTextField ctaxista;
	private JTextField cplacas;
	private String id_viaje, nickname_u, hora_inicio, hora_final, fecha, origen, destino, monto_pagado, tnombre,
			tplacas, aPaterno, aMaterno;
	private int f;
	// Variable BD
	private Connection conexion = null;
	ResultSet resultado, resultado2;
	Statement sentencia;

	/**
	 * Constructor de la clase Viaje donde se crearan todos los componentes de la
	 * ventana
	 * 
	 * @param xd
	 *            String que se recibe de la clase Busqueda_c y que se utilizara en
	 *            la base de datos para las consultas
	 * @throws SQLException
	 */
	public Viaje(String xd) throws SQLException {
		setLayout(null);
		f = Integer.parseInt(xd);
		consultar(f);
		String[] modelo = { id_viaje, nickname_u, hora_inicio, hora_final, origen, destino, monto_pagado,
				tnombre + " " + aPaterno + " " + aMaterno, tplacas, fecha };

		JLabel lblInformacinDelUsuario = new JLabel("Informaci\u00F3n del Viaje");
		lblInformacinDelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelUsuario.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblInformacinDelUsuario.setBounds(55, 47, 354, 14);
		add(lblInformacinDelUsuario);

		JLabel lblFolioViaje = new JLabel("Folio Viaje");
		lblFolioViaje.setBounds(40, 110, 76, 14);
		add(lblFolioViaje);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(40, 164, 46, 14);
		add(lblHora);

		cid = new JTextField();
		cid.setBounds(110, 107, 151, 20);
		add(cid);
		cid.setColumns(10);
		cid.setText(modelo[0]);
		cid.setEditable(false);

		chinicio = new JTextField();
		chinicio.setBounds(110, 161, 64, 20);
		add(chinicio);
		chinicio.setColumns(10);
		chinicio.setText(modelo[2]);
		chinicio.setEditable(false);

		chfinal = new JTextField();
		chfinal.setBounds(197, 161, 64, 20);
		add(chfinal);
		chfinal.setColumns(10);
		chfinal.setText(modelo[3]);
		chfinal.setEditable(false);

		JLabel label = new JLabel("-");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(163, 164, 46, 14);
		add(label);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(40, 262, 64, 14);
		add(lblUsuario);

		cusuario = new JTextField();
		cusuario.setBounds(110, 259, 285, 20);
		add(cusuario);
		cusuario.setColumns(10);
		cusuario.setText(modelo[1]);
		cusuario.setEditable(false);

		JLabel lblFecha = new JLabel("Fecha ");
		lblFecha.setBounds(40, 212, 58, 14);
		add(lblFecha);

		cfecha = new JTextField();
		cfecha.setBounds(110, 209, 151, 20);
		add(cfecha);
		cfecha.setColumns(10);
		cfecha.setText(modelo[9]);
		cfecha.setEditable(false);

		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setBounds(40, 315, 46, 14);
		add(lblRuta);

		corigen = new JTextField();
		corigen.setBounds(110, 312, 99, 20);
		add(corigen);
		corigen.setColumns(10);
		corigen.setText(modelo[4]);
		corigen.setEditable(false);

		cdestino = new JTextField();
		cdestino.setBounds(252, 312, 143, 20);
		add(cdestino);
		cdestino.setColumns(10);
		cdestino.setText(modelo[5]);
		cdestino.setEditable(false);

		JLabel lblA = new JLabel("a");
		lblA.setBounds(231, 315, 46, 14);
		add(lblA);

		JLabel lblTaxista = new JLabel("Taxista");
		lblTaxista.setBounds(40, 374, 64, 14);
		add(lblTaxista);

		ctaxista = new JTextField();
		ctaxista.setBounds(110, 371, 285, 20);
		add(ctaxista);
		ctaxista.setColumns(10);
		ctaxista.setText(modelo[7]);
		ctaxista.setEditable(false);

		JLabel lblPlacasTaxi = new JLabel("Placas Taxi");
		lblPlacasTaxi.setBounds(40, 425, 76, 14);
		add(lblPlacasTaxi);

		cplacas = new JTextField();
		cplacas.setBounds(110, 422, 105, 20);
		add(cplacas);
		cplacas.setColumns(10);
		cplacas.setText(modelo[8]);
		cplacas.setEditable(false);

		JLabel lblNewLabel = new JLabel("MONTO:");
		lblNewLabel.setBounds(322, 110, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Garamond", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(322, 135, 46, 43);
		add(lblNewLabel_1);

		JLabel cmonto = new JLabel("500");
		cmonto.setForeground(Color.RED);
		cmonto.setFont(new Font("Garamond", Font.PLAIN, 18));
		cmonto.setBounds(346, 135, 63, 43);
		cmonto.setText(modelo[6]);
		add(cmonto);
	}

	/**
	 * Método que realiza una consulta de los datos del viaje y sus resultados lo
	 * asigna a un modelo el cual sera utilizado para rellenar los JtextField de la
	 * ventana
	 * 
	 * @param xd
	 *            String que se recibe del constructor y que sera utilizado como id
	 *            para realizar la consulta de los datos
	 * @throws SQLException
	 */
	public void consultar(int xd) throws SQLException {
		try {
			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();

			resultado = sentencia.executeQuery("SELECT * from oaxataxi.viaje" + " WHERE id_viaje=" + xd + "");
			while (resultado.next()) {
				id_viaje = resultado.getString("id_viaje");
				nickname_u = resultado.getString("nickname_u");
				hora_inicio = resultado.getString("hora_inicio");
				hora_final = resultado.getString("hora_final");
				origen = resultado.getString("origen");
				destino = resultado.getString("destino");
				fecha = resultado.getString("fecha");
				monto_pagado = resultado.getString("monto_pagado");
			}
			resultado2 = sentencia.executeQuery(
					"SELECT  taxista.nombre, taxista.aPaterno, taxista.aMaterno, taxista_viaje_taxi.no_placas from oaxataxi.taxista"
							+ " inner join oaxataxi.taxista_viaje_taxi on oaxataxi.taxista.id_taxista = " + xd + "");
			while (resultado2.next()) {
				tnombre = resultado2.getString("nombre");
				aPaterno = resultado2.getString("aPaterno");
				aMaterno = resultado2.getString("aMaterno");
				tplacas = resultado2.getString("no_placas");
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

}
