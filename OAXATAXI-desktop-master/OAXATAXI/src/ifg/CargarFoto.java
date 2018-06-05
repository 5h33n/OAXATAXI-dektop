package ifg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Clase que funcionara como un explorador de archivos para cargar alguna foto de 
 * la pc del administrador, esta clase sera utilizada cuando se desee cambiar la foto 
 * de algun taxista o una cuenta de un taxi
 * @author Davisito
 *
 */
public class CargarFoto extends JFrame {

	
	private JPanel contentPane;
	public static javax.swing.JFileChooser jfchCargarfoto ;
	
	/**
	 * Constructor de la clase CargarFoto en el cual se crean los componentes 
	 * 
	 */
	public CargarFoto() {
		this.setAlwaysOnTop( true );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		jfchCargarfoto = new JFileChooser();
		jfchCargarfoto.setFileHidingEnabled(true);
		contentPane.add(jfchCargarfoto);
	}
	/**
	 * Método principal de la clase CargarFoto, en el cual se creeara la venta donde estara contenido el
	 * explorador de archivos
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CargarFoto frame = new CargarFoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
