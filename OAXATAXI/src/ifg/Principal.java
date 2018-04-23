package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;
public class Principal extends JFrame implements Runnable
{
    
    private JButton cerrar,notif, alerta;
    private JLabel titulo = new JLabel("OAXATAXI");
    private JLabel admin = new JLabel();
    private JLabel viajes = new JLabel("Viajes del día");
    private Button verMapa, notificar;
    private JTable tabla1 = new JTable();
    private String hora, minutos, segundos, ampm;
    private Calendar calendario;
    private Thread h1;
    JLabel lbHora = new JLabel();
    private JLabel fondo;
    public Principal() throws IOException
    {
    		crearComponentes();
        this.setUndecorated(true);
        //this.setSize(500, 535);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocationRelativeTo(null);
        
        h1 = new Thread(this);
        h1.start();
    }
    
    public void crearComponentes()
    {
        this.setSize(1200, 600);
        this.setLayout(null);  
        
        JPanelSuperior barraTitulo = new JPanelSuperior();
        
        this.add(barraTitulo);
        
        alerta = new JButton();
        notif = new JButton();
        cerrar = new JButton();
        
        JLabel barra = new JLabel();
        barra.setIcon(new ImageIcon("../barra.jpg"));
        barra.setBounds(0,0,823,45);
        this.add(barra);
        
        alerta.setIcon(new ImageIcon("../alerta.PNG"));
        alerta.setBounds(823,0,65,45);
        this.add(alerta);
        
        notif.setIcon(new ImageIcon("../notificacion.PNG"));
        notif.setBounds(888,0,65,45);
        this.add(notif);
        
        admin.setIcon(new ImageIcon("../admin.PNG"));
        admin.setBounds(953,0,192,45);
        this.add(admin);
        
        cerrar.setIcon(new ImageIcon("../cerrar.PNG"));
        cerrar.setBounds(1145,0,55,45);
        this.add(cerrar);  
        
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds(490,60,200,50);
        titulo.setSize(400, 50);
        this.add(titulo);
        
        viajes.setFont(new Font("Segoe UI Black", Font.BOLD, 23));
        viajes.setBounds(95,180,180,50);
        this.add(viajes);
        
        verMapa = new Button();
        verMapa.setFont(new Font("Arial", Font.BOLD, 25));
        verMapa.setForeground(Color.black);
        verMapa.setText("Ver mapa");
        verMapa.setColor1(new Color(255, 196, 0));
        verMapa.setColor2(new Color(202, 147, 0));
        verMapa.setBounds(875,220,195,50);
        this.add(verMapa);
            
        notificar = new Button();
        notificar.setText("Notificar");
        notificar.setFont(new Font("Arial", Font.BOLD, 25));
        notificar.setForeground(Color.black);
        notificar.setColor1(new Color(255, 196, 0));
        notificar.setColor2(new Color(202, 147, 0));
        notificar.setBounds(875,285,195,50);
        this.add(notificar);
        
        JPanel sPanel = new JPanel();
        /*
        for (int i = 0; i < 10; i++) {
            sPanel.add(new JButton("Hello-" + i));
        }
        */
        JScrollPane scrollPane = new JScrollPane(sPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 700, 200);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        contentPane.setBounds(95,220,700,200);
        this.add(contentPane);
        
        lbHora.setFont(new Font("Arial", Font.BOLD, 25));
        lbHora.setBounds(900,100,200,50);
        this.add(lbHora);
        
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("../logo.jpg"));
        logo.setBounds(850,400,280,100);
        this.add(logo);
        
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon("fondo.png"));
        fondo.setBounds(0,45,1200,550);
        this.add(fondo);
    }
    
    public void calcular()
    {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        if(ampm.equals("PM"))
        {
            int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
            hora = h>9?""+h:"0"+h;
        }
        else
        {
            hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }

    public void run()
    {
        Thread ct = Thread.currentThread();
        while(ct == h1)
        {
            calcular();
            lbHora.setText(hora + ":" + minutos + ":" + segundos + " " +ampm);
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {

            }
            /*remove(horax);
            horax.add(lbHora);
            add(horax, BorderLayout.NORTH);*/
            this.remove(lbHora);
            this.add(lbHora);
            this.add(fondo);
        }
    }
}
