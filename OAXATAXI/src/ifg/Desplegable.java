package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Desplegable extends JPanel {
    private Button agregar, eliminar, ver, ocultar;
    public boolean flag=false;
    public Button getOcultar() {
		return ocultar;
		
	}
	public void setOcultar(Button ocultar) {
		this.ocultar = ocultar;
	}
	private JLabel fondo;
    public Desplegable() {
        this.setSize(250, 550);
        this.setVisible(true);
        this.setLayout(new BorderLayout());   
        crearComponentes();
    }
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == ocultar) {
    			bye();
    		}if (e.getSource() == agregar) {
    			new VentanaAgregar();
    		}if (e.getSource() == ver) {
    			new Database();
    		}
    	}
    }
    public void bye() {
    	this.setVisible(false);
    	this.flag = true;
    }
    public void crearComponentes() {
        agregar = new Button();
        agregar.setBounds(65,80,120,40);
        agregar.setText("Agregar");
        agregar.setForeground(Color.black);
        agregar.setColor1(new Color(255, 196, 0));
        agregar.setColor2(new Color(202, 147, 0));
        agregar.addMouseListener(new Click());
        this.add(agregar);
        
        eliminar = new Button();
        eliminar.setBounds(65,190,120,40);
        eliminar.setColor1(new Color(255, 196, 0));
        eliminar.setColor2(new Color(202, 147, 0));
        eliminar.setForeground(Color.black);
        eliminar.setText("Buscar");
        this.add(eliminar);
        
        ver = new Button();
        ver.setBounds(65,300,120,40);
        ver.setColor1(new Color(255, 196, 0));
        ver.setColor2(new Color(202, 147, 0));
        ver.setForeground(Color.black);
        ver.setText("Ver");
        ver.addMouseListener(new Click());
        this.add(ver);
        
        ocultar = new Button();
        ocultar.setBounds(65,410,120,40);
        ocultar.setColor1(new Color(255, 196, 0));
        ocultar.setColor2(new Color(202, 147, 0));
        ocultar.setForeground(Color.black);
        ocultar.setText("Ocultar");
        ocultar.addMouseListener(new Click());
        this.add(ocultar);
        
        ImageIcon fd = new ImageIcon(getClass().getResource("/img/fondoDesp.jpg"));
        fondo = new JLabel();
        fondo.setIcon(fd);
        fondo.setBounds(0,0,200,300);
        this.add(fondo);
        this.setBackground(Color.BLACK);
    }
}
