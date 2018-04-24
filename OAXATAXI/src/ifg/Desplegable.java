package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Desplegable extends JFrame {
    private Button agregar, eliminar, ver, ocultar;
    private JLabel fondo;
    public Desplegable() {
        this.setUndecorated(true);
        this.setSize(200, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocationRelativeTo(null);
        crearComponentes();
    }
    
    public void crearComponentes() {
        agregar = new Button();
        agregar.setBounds(50,20,100,30);
        agregar.setText("Agregar");
        agregar.setForeground(Color.black);
        agregar.setColor1(new Color(255, 196, 0));
        agregar.setColor2(new Color(202, 147, 0));
        this.add(agregar);
        
        eliminar = new Button();
        eliminar.setBounds(50,90,100,30);
        eliminar.setColor1(new Color(255, 196, 0));
        eliminar.setColor2(new Color(202, 147, 0));
        eliminar.setForeground(Color.black);
        eliminar.setText("Eliminar");
        this.add(eliminar);
        
        ver = new Button();
        ver.setBounds(50,160,100,30);
        ver.setColor1(new Color(255, 196, 0));
        ver.setColor2(new Color(202, 147, 0));
        ver.setForeground(Color.black);
        ver.setText("Ver");
        this.add(ver);
        
        ocultar = new Button();
        ocultar.setBounds(50,240,100,30);
        ocultar.setColor1(new Color(255, 196, 0));
        ocultar.setColor2(new Color(202, 147, 0));
        ocultar.setForeground(Color.black);
        ocultar.setText("Ocultar");
        this.add(ocultar);
        
        ImageIcon fd = new ImageIcon(getClass().getResource("/img/fondoDesp.jpg"));
        fondo = new JLabel();
        fondo.setIcon(fd);
        fondo.setBounds(0,0,200,300);
        this.add(fondo);
    }
}
