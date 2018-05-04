package ifg;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal extends JFrame implements Runnable {
    
    private JButton cerrar,notif, alerta;
    private JLabel titulo = new JLabel("OAXATAXI");
    private JLabel admin = new JLabel();
    private JLabel viajes = new JLabel("Viajes del día");
    private Button verMapa, notificar;
    private DefaultTableModel dtm;
    private JTable table = new JTable(dtm);
    private String hora, minutos, segundos, ampm;
    private Calendar calendario;
    private Thread h1;
    JLabel lbHora = new JLabel();
    private JLabel fondo;
    //VARIABLES DE DB
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    
    
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
        try {
			mostrar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void crearComponentes() {
        this.setSize(1200, 600);
        this.setLayout(null);  
        
        JPanelSuperior barraTitulo = new JPanelSuperior();
        
        this.add(barraTitulo);
        
        alerta = new JButton();
        notif = new JButton();
        cerrar = new JButton();
        cerrar.addMouseListener(new Click());
        JLabel barra = new JLabel();
        
        ImageIcon b = new ImageIcon(getClass().getResource("/img/barra.jpg"));
        barra.setIcon(b);
        barra.setBounds(0,0,823,45);
        this.add(barra);
        
        ImageIcon al = new ImageIcon(getClass().getResource("/img/alerta.png"));
        alerta.setIcon(al);
        alerta.setBounds(823,0,65,45);
        this.add(alerta);
        
        ImageIcon n = new ImageIcon(getClass().getResource("/img/notificacion.png"));
        notif.setIcon(n);
        notif.setBounds(888,0,65,45);
        this.add(notif);
        
        ImageIcon a = new ImageIcon(getClass().getResource("/img/admin.png"));
        admin.setIcon(a);
        admin.setBounds(953,0,192,45);
        this.add(admin);
        
        ImageIcon c= new ImageIcon(getClass().getResource("/img/cerrar.png"));
        cerrar.setIcon(c);
        cerrar.setBounds(1145,0,55,45);
        this.add(cerrar);  
        
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds(490,60,200,50);
        titulo.setSize(400, 50);
        this.add(titulo);
        
        viajes.setFont(new Font("Segoe UI Black", Font.BOLD, 23));
        viajes.setBounds(95,180,480,50);
        this.add(viajes);
        
        verMapa = new Button();
        verMapa.setFont(new Font("Arial", Font.BOLD, 25));
        verMapa.setForeground(Color.black);
        verMapa.setText("Ver mapa");
        verMapa.setColor1(new Color(255, 196, 0));
        verMapa.setColor2(new Color(202, 147, 0));
        verMapa.setBounds(875,220,195,50);
        verMapa.addMouseListener(new Click());
        this.add(verMapa);
            
        notificar = new Button();
        notificar.setText("Notificar");
        notificar.setFont(new Font("Arial", Font.BOLD, 25));
        notificar.setForeground(Color.black);
        notificar.setColor1(new Color(255, 196, 0));
        notificar.setColor2(new Color(202, 147, 0));
        notificar.setBounds(875,285,195,50);
        notificar.addMouseListener(new Click());
        this.add(notificar);
        
        JPanel sPanel = new JPanel();
        String [][] matriz = {};
        String [] vector = {"id","nombre"};
        dtm = new DefaultTableModel(matriz,vector);
        sPanel =new JPanel();
	     sPanel.setLayout(new BorderLayout());
	     dtm= new DefaultTableModel(matriz,vector) {
	    	 public boolean isCellEditable(int row, int column) {
	    		 return false;
	    	 }
	     };
	     table = new JTable(dtm);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 JScrollPane scrollPane = new JScrollPane(table); 
        sPanel.add(scrollPane);
        table.requestFocus();
        
        /*
        for (int i = 0; i < 10; i++) {
            sPanel.add(new JButton("Hello-" + i));
        }
        */
        //JScrollPane scrollPane = new JScrollPane(sPanel);
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
        
        ImageIcon l = new ImageIcon(getClass().getResource("/img/logo.png"));
        ImageIcon lo = new ImageIcon(l.getImage().getScaledInstance(180, 120, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(lo);
        logo.setBounds(870,400,280,200);
        this.add(logo);
        
        ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo.png"));
        fondo = new JLabel();
        fondo.setIcon(f);
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
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == cerrar) {
    			cerrar();
    		}
    		if (e.getSource() == verMapa) {
    			Mapa m = new Mapa();
    		}
    		if (e.getSource() == notificar) {
    			Notificacion n = new Notificacion();
    		}
    	}
    }
    public void cerrar() {
    	System.exit(0);
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
    //conexion bd
    public void conexionDB() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/postgres";
			conexion = DriverManager.getConnection(url,"postgres","Pacomegoma12");
			if (conexion!=null) {
				System.out.println("Conexion exitosa alv");
			}else {
				JOptionPane.showMessageDialog(null,"Conexion fallida alv");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void siguiente() {
    	try {
    		String id_viaje = resultado.getString("id_viaje");
    		String hinicio = resultado.getString("hora_inicio");
    		String hfinal = resultado.getString("hora_final");
    		String origen = resultado.getString("origen");
    		String destino = resultado.getString("destino");
    		String cobrado = resultado.getString("monto_pagado");
    		
    		String [] modelo={id_viaje,hinicio,hfinal,origen,destino,cobrado};
    		dtm.addRow(modelo);
    	}catch(Exception e) {
    		//
    	}
    }
    public void mostrar() throws SQLException {
    	try {
			conexionDB();
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery("SELECT * FROM OAXATAXI.VIAJE");
			siguiente();
			table = new JTable(dtm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	    } finally {
	        if (sentencia != null) { sentencia.close(); }
	    }
    }
}
