package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Desplegable extends JFrame
{
    private Button agregar, eliminar, ver, ocultar;
    private JFrame principal = new JFrame();
    private JLabel fondo;
    public Desplegable()
    {
        principal.setUndecorated(true);
        principal.setSize(200, 300);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        principal.setLayout(new BorderLayout());        
        principal.setLocationRelativeTo(null);
        crearComponentes();
    }
    
    public void crearComponentes()
    {
        agregar = new Button();
        agregar.setBounds(50,20,100,30);
        agregar.setText("Agregar");
        agregar.setForeground(Color.black);
        agregar.setColor1(new Color(255, 196, 0));
        agregar.setColor2(new Color(202, 147, 0));
        principal.add(agregar);
        
        eliminar = new Button();
        eliminar.setBounds(50,90,100,30);
        eliminar.setColor1(new Color(255, 196, 0));
        eliminar.setColor2(new Color(202, 147, 0));
        eliminar.setForeground(Color.black);
        eliminar.setText("Eliminar");
        principal.add(eliminar);
        
        ver = new Button();
        ver.setBounds(50,160,100,30);
        ver.setColor1(new Color(255, 196, 0));
        ver.setColor2(new Color(202, 147, 0));
        ver.setForeground(Color.black);
        ver.setText("Ver");
        principal.add(ver);
        
        ocultar = new Button();
        ocultar.setBounds(50,240,100,30);
        ocultar.setColor1(new Color(255, 196, 0));
        ocultar.setColor2(new Color(202, 147, 0));
        ocultar.setForeground(Color.black);
        ocultar.setText("Ocultar");
        principal.add(ocultar);
        
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon("../fondoDesp.jpg"));
        fondo.setBounds(0,0,200,300);
        principal.add(fondo);
    }
}
