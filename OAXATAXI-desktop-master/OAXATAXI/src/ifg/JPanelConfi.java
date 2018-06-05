package ifg;

import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JPanelConfi extends JFrame {

	JTextField cuadroUsuario;
	JLabel contra,contra2,usuario,fondo,titulo;
	JPasswordField cuadroContra,cuadroContra2;
	
	
	public void crearPanelC() {
		
		ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo2.png"));
		fondo = new JLabel();
		fondo.setIcon(f);
		
		//Cajas
		cuadroUsuario = new JTextField();
		cuadroUsuario.setBounds(240, 100, 100, 25);
		fondo.add(cuadroUsuario);
		
		cuadroContra = new JPasswordField();
		cuadroContra.setBounds(240, 150, 100, 25);
		fondo.add(cuadroContra);
		
		cuadroContra2 = new JPasswordField();
		cuadroContra2.setBounds(240, 200, 100, 25);
		fondo.add(cuadroContra2);
		
		//Textos
		titulo = new JLabel();
		titulo.setFont(new Font("Andale Mono", 1, 16));
		titulo.setText("Cambiar contrasena");
		titulo.setBounds(120, 20, 200, 25);
		fondo.add(titulo);
		
		usuario = new JLabel();
		usuario.setFont(new Font("Andale Mono", 1, 16));
		usuario.setText("Usuario:");
		usuario.setBounds(40, 100, 100, 25);
		fondo.add(usuario);
		
		contra = new JLabel();
		contra.setFont(new Font("Andale Mono", 1, 16));
		contra.setText("Nueva contrasena:");
		contra.setBounds(40, 150, 170, 25);
		fondo.add(contra);
		
		contra2 = new JLabel();
		contra2.setFont(new Font("Andale Mono", 1, 16));
		contra2.setText("Repita la contrasena:");
		contra2.setBounds(40, 200, 170, 25);
		fondo.add(contra2);
		
		
		
		
		
		this.add(fondo);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
//	public static void main(String []args) {
//		JPanelConfi a = new JPanelConfi();
//		a.crearPanel();
//		a.setVisible(true);
//		a.setLocationRelativeTo(null);
//		
//	}
}
