package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Mapa extends JFrame
{
    JFrame principal = new JFrame();
    JButton cerrar = new JButton();
    public Mapa()
    {
        principal.setUndecorated(true);
        principal.setSize(399, 399);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        principal.setLayout(new BorderLayout());        
        principal.setLocation(100,100);
        crearComponentes();
    }
    
    public void crearComponentes()
    {
        principal.setSize(800,600);
        
        cerrar.setIcon(new ImageIcon("../cerrar.PNG"));
        cerrar.setBounds(745,0,55,45);
        principal.add(cerrar);
        
        JPanel sPanel = new JPanel();
        sPanel.setBounds(55,45, 690,480);
        principal.add(sPanel);
        
        JLabel rojo = new JLabel();
        rojo.setIcon(new ImageIcon("../rojo.PNG"));
        rojo.setBounds(80,550,35,35);
        principal.add(rojo);
        
        JLabel red = new JLabel("Emergencia");
        red.setFont(new Font("Arial", Font.BOLD, 25));
        red.setBounds(125,550,150,35);///////////////////////////////////
        principal.add(red);
        
        JLabel marron = new JLabel();
        marron.setIcon(new ImageIcon("../marron.PNG"));
        marron.setBounds(300,550,35,35);
        principal.add(marron);
        
        JLabel maron = new JLabel("En viaje");
        maron.setFont(new Font("Arial", Font.BOLD, 25));///////////////////
        maron.setBounds(345,550,150,35);
        principal.add(maron);
        
        JLabel verde = new JLabel();
        verde.setIcon(new ImageIcon("../verde.PNG"));
        verde.setBounds(520,550,35,35);
        principal.add(verde);
        
        JLabel green = new JLabel("Disponible");
        green.setFont(new Font("Arial", Font.BOLD, 25));////////////////////////
        green.setBounds(565,550,150,35);
        principal.add(green);
        
        JLabel fondo = new JLabel();
        fondo.setIcon(new ImageIcon("../fondo3.PNG"));
        fondo.setBounds(0,0,800,600);
        principal.add(fondo);
    }
}
