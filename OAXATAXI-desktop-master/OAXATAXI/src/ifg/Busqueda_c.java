package ifg;

import javax.swing.JPanel;

import oaxataxiDesktop.Taxi;
import oaxataxiDesktop.Taxista;
import oaxataxiDesktop.Usuario;
import oaxataxiDesktop.Viaje;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;

/**
 * @author Davisito
 *
 */
public class Busqueda_c extends JPanel {
	private JButton jb;
	private String x, y;
	static JFrame frame = new FrameInfo();
	private int a;

	/**
	 * Create the panel.
	 */
	public Busqueda_c(String titulo, String info, String info2, String info3) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 75, 75, 0 };
		gridBagLayout.rowHeights = new int[] { 35, 0, 0, 0, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel(titulo);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(info);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(info2);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(150, 100));

		JLabel lblNewLabel_3 = new JLabel(info3);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		x = titulo;
		y = info;
		addMouseListener(new Click());
	}

	/**
	 * @author Davisito
	 *
	 */
	private class Click extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

			// if (e.getSource() == jb) {
			
			frame.getContentPane().removeAll();
			if (x == "Taxi:") {

				try {
					if (frame.isVisible()) {
						frame.getContentPane().removeAll();
					}
					String[] id = new String[2];
					id = y.split(" ");
					frame.getContentPane().add(new Taxi(id[1]));
					frame.setVisible(true);
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if (x == "Usuario:") {

					try {
						if (frame.isVisible()) {
							frame.getContentPane().removeAll();
						}
						String[] id = new String[2];
						id = y.split(" ");
						frame.getContentPane().add(new Usuario(id[1]));
						frame.setVisible(true);

					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}
				} else {
					if (x == "Taxista:") {

						try {
							if (frame.isVisible()) {
								frame.getContentPane().removeAll();
							}
							String[] id = new String[2];
							id = y.split(" ");
							frame.getContentPane().add(new Taxista(id[1]));
							frame.setVisible(true);
						} catch (SQLException | ParseException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else {
						if (x == "Viaje:") {

							try {
								if (frame.isVisible()) {
									frame.getContentPane().removeAll();
								}
								String[] id = new String[2];
								id = y.split(" ");
								frame.getContentPane().add(new Viaje(id[1]));
								frame.setVisible(true);
							} catch (SQLException e1) {
								e1.printStackTrace();
							} 
						
					}

				}

			}
		}

	}
	}}

// }
