package ifg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CargarFoto extends JFrame {

	
	private JPanel contentPane;
	public static javax.swing.JFileChooser jfchCargarfoto ;
	

	/**
	 * Launch the application.
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

	/**
	 * Create the frame.
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

}
