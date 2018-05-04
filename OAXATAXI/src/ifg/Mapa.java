package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Mapa extends JFrame {
    JButton cerrar = new JButton();
    public Mapa() {
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocation(100,100);
        crearComponentes();
    }
    
    public void crearComponentes() {
        this.setSize(800,600);
        
        ImageIcon c = new ImageIcon(getClass().getResource("/img/cerrar.png"));
        cerrar.setIcon(c);
        cerrar.setBounds(745,0,55,45);
        cerrar.addMouseListener(new Click());
        this.add(cerrar);
        
        JPanel sPanel = new JPanel();
        sPanel.setBounds(55,45, 690,480);
        this.add(sPanel);
        
        ImageIcon r = new ImageIcon(getClass().getResource("/img/rojo.png"));
        JLabel rojo = new JLabel();
        rojo.setIcon(r);
        rojo.setBounds(80,550,35,35);
        this.add(rojo);
        
        JLabel red = new JLabel("Emergencia");
        red.setFont(new Font("Arial", Font.BOLD, 25));
        red.setBounds(125,550,150,35);///////////////////////////////////
        this.add(red);
        
        ImageIcon m = new ImageIcon(getClass().getResource("/img/marron.png"));
        JLabel marron = new JLabel();
        marron.setIcon(m);
        marron.setBounds(300,550,35,35);
        this.add(marron);
        
        JLabel maron = new JLabel("En viaje");
        maron.setFont(new Font("Arial", Font.BOLD, 25));///////////////////
        maron.setBounds(345,550,150,35);
        this.add(maron);
        
        ImageIcon v = new ImageIcon(getClass().getResource("/img/verde.png"));
        JLabel verde = new JLabel();
        verde.setIcon(v);
        verde.setBounds(520,550,35,35);
        this.add(verde);
        
        JLabel green = new JLabel("Disponible");
        green.setFont(new Font("Arial", Font.BOLD, 25));////////////////////////
        green.setBounds(565,550,150,35);
        this.add(green);
        
        ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo3.png"));
        JLabel fondo = new JLabel();
        fondo.setIcon(f);
        fondo.setBounds(0,0,800,600);
        this.add(fondo);
    }
    private class Click extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == cerrar) {
				ocultar();
			}
		}
}
public void ocultar() {
		this.dispose();
}
}
