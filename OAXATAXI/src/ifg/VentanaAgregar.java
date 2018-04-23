package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class VentanaAgregar extends JFrame
{
    private JFrame principal = new JFrame();
    JLabel titulo = new JLabel("Agregar");
    JLabel agregar = new JLabel("Agregar nuevo");
    JLabel nombre = new JLabel("Nombre");
    JLabel nacimiento = new JLabel("Fecha de Nacimiento");
    JLabel foto = new JLabel();
    Button botAgregar = new Button();
    Button cancelar = new Button();
    Button adFoto = new Button();
    JTextField cajaNombre = new JTextField(20);
    JComboBox cbPersonas;
    public VentanaAgregar()
    {
        principal.setUndecorated(true);
        principal.setSize(399, 399);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        principal.setLayout(new BorderLayout());        
        principal.setLocationRelativeTo(null);
        crearComponentes();
    }
    
    public void crearComponentes()
    {
        principal.setSize(400,400);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(150,10,100,40);
        principal.add(titulo);
        
        botAgregar = new Button();
        botAgregar.setText("Agregar");
        botAgregar.setForeground(Color.black);
        botAgregar.setColor1(new Color(255, 196, 0));
        botAgregar.setColor2(new Color(202, 147, 0));
        botAgregar.setBounds(50,364,90,30);
        principal.add(botAgregar);
        
        cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.setForeground(Color.black);
        cancelar.setColor1(new Color(255, 196, 0));
        cancelar.setColor2(new Color(202, 147, 0));
        cancelar.setBounds(220,364,90,30);
        principal.add(cancelar);
              
        JPanel sPanel = new JPanel();       
        sPanel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(sPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 340,300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        contentPane.setBounds(20,60,340,300);
        principal.add(contentPane);
        
        agregar.setFont(new Font("Arial", Font.BOLD, 13));
        agregar.setBounds(5,5,120,30);
        sPanel.add(agregar);
        
        nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setBounds(5,60,120,30);
        sPanel.add(nombre);
        
        nacimiento.setFont(new Font("Arial", Font.BOLD, 13));
        nacimiento.setBounds(5,115,150,30);
        sPanel.add(nacimiento);
        
        foto.setIcon(new ImageIcon("../foto base.jpg"));
        foto.setBounds(15,170,100,120);
        sPanel.add(foto);
        
        adFoto.setText("Agregar foto...");
        adFoto.setForeground(Color.black);
        adFoto.setColor1(new Color(255, 196, 0));
        adFoto.setColor2(new Color(202, 147, 0));
        adFoto.setBounds(150,230,120,30);
        sPanel.add(adFoto);
        
        String [] personas = {"Taxista","Usuario"};
        cbPersonas = new JComboBox(personas);
        cbPersonas.setBounds(190,5,100,30);
        sPanel.add(cbPersonas);
        
        cajaNombre.setBounds(190,60,100,30);
        sPanel.add(cajaNombre);
        
        JTextField cajaNac = new JTextField();
        cajaNac.setBounds(190,115,100,30);
        sPanel.add(cajaNac);
        
        JLabel fondo = new JLabel();
        fondo.setIcon(new ImageIcon("../fondo2.PNG"));
        fondo.setBounds(0,0,400,400);
        principal.add(fondo);
    }
}
