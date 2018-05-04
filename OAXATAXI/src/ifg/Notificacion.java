package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Notificacion extends JFrame {
    JLabel envNot = new JLabel("Enviar Notificación");
    JLabel adFoto = new JLabel("Adjuntar Foto");
    JLabel asunto = new JLabel("Asunto:");
    JComboBox cbPersonas = new JComboBox();
    JLabel foto = new JLabel();
    JTextArea desAsunto = new JTextArea();
    Button botAgregar,cancelar;
    public Notificacion() {
        this.setUndecorated(true);
        //this.setSize(399, 399);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocation(100,100);
        crearComponentes();
    }
    private class Click extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == cancelar) {
				ocultar();
			}
		}
}
public void ocultar() {
		this.dispose();
}
    public void crearComponentes()
    {
        this.setSize(400,400);
        
        JPanel sPanel = new JPanel();       
        sPanel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(sPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 340,320);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        contentPane.setBounds(30,30,340,320);
        this.add(contentPane);
        
        envNot.setFont(new Font("Arial", Font.BOLD, 30));
        envNot.setBounds(30,10,300,40);
        sPanel.add(envNot);
        
        String[] personas = {"Todos"};
        cbPersonas = new JComboBox(personas);
        cbPersonas.setBounds(170,70, 100, 30);
        sPanel.add(cbPersonas);
        
        adFoto.setFont(new Font("Arial", Font.BOLD, 16));
        adFoto.setBounds(50, 140, 150, 40);
        sPanel.add(adFoto);
        
        Button anadir = new Button();
        anadir.setText("Añadir");
        anadir.setFont(new Font("Arial", Font.BOLD, 12));
        anadir.setColor1(Color.white.brighter());
        anadir.setBounds(185,180,90,30);
        sPanel.add(anadir);
        
        ImageIcon fo = new ImageIcon(getClass().getResource("/img/fondo.png"));
        foto.setIcon(new ImageIcon("../foto base.jpg"));
        foto.setBounds(180,140,100,100);
        sPanel.add(foto);
        
        asunto.setFont(new Font("Arial", Font.BOLD, 16));
        asunto.setBounds(50, 240, 150,40);
        sPanel.add(asunto);
        
        botAgregar = new Button();
        botAgregar.setText("Agregar");
        botAgregar.setForeground(Color.black);
        botAgregar.setColor1(new Color(255, 196, 0));
        botAgregar.setColor2(new Color(202, 147, 0));
        botAgregar.setBounds(50,364,90,30);
        this.add(botAgregar);
        
        cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.setForeground(Color.black);
        cancelar.setColor1(new Color(255, 196, 0));
        cancelar.setColor2(new Color(202, 147, 0));
        cancelar.setBounds(220,364,90,30);
        cancelar.addMouseListener(new Click());
        this.add(cancelar);
        
        desAsunto.setBounds(50,280,200,100);
        sPanel.add(desAsunto);
        
        ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo2.png"));
        JLabel fondo = new JLabel();
        fondo.setIcon(f);
        fondo.setBounds(0,0,400,400);
        this.add(fondo);
    }
}
