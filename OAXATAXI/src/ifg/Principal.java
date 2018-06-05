package ifg;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import recursos.Conexion;
import recursos.ConexionServer;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JFrame principal, en el se concentra la mayoría de los elementos visibles
 * @author sheen
 *
 */
public class Principal extends JFrame implements Runnable {
    
	//COMPONENTES DE DISEÑO
    private JButton cerrar,notif, alerta,minimizar,cont;
    private JLabel titulo = new JLabel();
    private JButton admin = new JButton();
    private JLabel viajes = new JLabel("Viajes del día");
    private Button verMapa, notificar,options;
    private JButton ver,agregar;
    private DefaultTableModel dtm;
    private JTable table = new JTable(dtm);
    private String hora, minutos, segundos, ampm;
    private JDateChooser dateChooser;
    
    //HILO DEL RELOJ
    private Thread h1;
    
    //COMPONENTES DE OTRAS CLASES
    Desplegable des;
    AyudaAdmin ayuda;
    Principal p;
    JScrollPane scrollPane;
    JLabel lbHora = new JLabel();
    private JLabel fondo;
    
    //VARIABLES DE DB
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    
    /**
     * Constructos principal, define las caracteristicas del JFrame
     * @throws IOException
     */
    public Principal() throws IOException{
    		//Se llama a los componentes
    		crearComponentes();
        this.setUndecorated(true);
        this.setVisible(true);
        this.addMouseListener(new Click());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocationRelativeTo(null);
        //Se inicizaliza el reloj
        h1 = new Thread(this);
        h1.start();
    }

    /**
     * Se crean los componentes con sus respectivas posiciones e instancias de MouseAdapter
     */
    public void crearComponentes() {
        this.setSize(1200, 580);
        this.setLayout(null);  
        
        
        //Se agrega la ventana desplegable de busqueda
        try {
			des = new Desplegable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        des.setLocation(0, 30);
        des.setVisible(false);
        //Focuslistener de la ventana desplegable para comprobar cuando desaparece
        des.addFocusListener(new FocusListener() {
        	   
        	   public void focusLost(FocusEvent e) {
        	      des.setVisible(false);
        	   }
        	   public void focusGained(FocusEvent e) {
        		  des.setVisible(true);
				
        	   }
        	});
        des.addMouseListener(new Click());
        this.add(des);
        
      //Panel de ayuda
        ayuda = new AyudaAdmin(this);
        ayuda.setLocation(1000, 30);
        ayuda.setVisible(false);
        ayuda.addFocusListener(new FocusListener() {
        	   
        	   public void focusLost(FocusEvent e) {
        	      ayuda.setVisible(false);
        	   }
        	   public void focusGained(FocusEvent e) {
        		  ayuda.setVisible(true);
				
        	   }
        	});
        ayuda.addMouseListener(new Click());
        this.add(ayuda);
        
        
        
        //Reloj analógico
        ClockAnalogBuf reloj = new ClockAnalogBuf();
        reloj.setBackground(new Color(250, 244, 194));
        reloj.setBounds(890,50,150,150);
        //this.add(reloj);
        
        //Botones
        alerta = new JButton();
        notif = new JButton();
        cerrar = new JButton();
        cerrar.addMouseListener(new Click());
        cerrar.setBorder(BorderFactory.createLineBorder(Color.black,1));
        minimizar = new JButton();
        minimizar.setBorder(BorderFactory.createLineBorder(Color.black,1));
        alerta.setBorder(BorderFactory.createLineBorder(Color.black,1));
        notif.setBorder(BorderFactory.createLineBorder(Color.black,1));
        minimizar.addMouseListener(new Click());
        JLabel barra = new JLabel();
        
        ImageIcon b = new ImageIcon(getClass().getResource("/img/barra.jpg"));
        barra.setIcon(b);
        barra.setBounds(0,0,911,30);
        this.add(barra);
        
        ImageIcon al = new ImageIcon(getClass().getResource("/img/alerta.png"));
        alerta.setIcon(al);
        alerta.setBounds(911,0,43,30);
        this.add(alerta);
        
        ImageIcon n = new ImageIcon(getClass().getResource("/img/notificacion.png"));
        notif.setIcon(n);
        notif.setBounds(954,0,46,30);
        this.add(notif);
        
        
      //Se agregan elementos visuales, como imagenes y fields
        ImageIcon c= new ImageIcon(getClass().getResource("/img/minimizar.png"));
        minimizar.setIcon(c);
        minimizar.setBounds(1128,0,36,30);
        this.add(minimizar);
        
        ImageIcon ce= new ImageIcon(getClass().getResource("/img/cerrar.png"));
        cerrar.setIcon(ce);
        cerrar.setBounds(1164,0,36,30);
        this.add(cerrar); 
        
        //titulo.setFont(new Font("Arial", Font.BOLD, 40));
        ImageIcon ti = new ImageIcon(getClass().getResource("/img/oaxa.png"));
        titulo.setIcon(ti);
        titulo.setBounds(250,60,500,150);
        titulo.setSize(600, 100);
        this.add(titulo);
        
        dateChooser = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        dateChooser.setBounds(570,194,130,25);
        this.add(dateChooser);
        cont = new JButton();
        cont.setBounds(700,194,100,25);
        cont.setText("Ver");
        cont.addMouseListener(new Click());
        this.add(cont);
        viajes.setFont(new Font("Segoe UI Black", Font.BOLD, 23));
        viajes.setBounds(95,180,400,50);
        this.add(viajes);
        
        verMapa = new Button(true);
        verMapa.setFont(new Font("Arial", Font.BOLD, 25));
        verMapa.setForeground(Color.black);
        verMapa.setText("Ver mapa");
        verMapa.setColor1(new Color(255, 196, 0));
        verMapa.setColor2(new Color(202, 147, 0));
        verMapa.setBounds(875,260,195,40);
        verMapa.addMouseListener(new Click());
        this.add(verMapa);
            
        notificar = new Button(true);
        notificar.setText("Notificar");
        notificar.setFont(new Font("Arial", Font.BOLD, 25));
        notificar.setForeground(Color.black);
        notificar.setColor1(new Color(255, 196, 0));
        notificar.setColor2(new Color(202, 147, 0));
        notificar.setBounds(875,315,195,40);
        notificar.addMouseListener(new Click());
        this.add(notificar);
        
        
        
        //SE AGREGA LA TABLA PRINCIPAL QUE MUESTRA LOS VIAJES DEL DÍA
        String [] d = {"id_viaje","id_taxista","Conductor","Placas","Usuario","Estado","Hora inicio","Hora final","Origen","Destino","Monto"};
        JPanel t = new JPanel();
        t.setLayout(new BoxLayout(t,BoxLayout.Y_AXIS));
        //t.add(new JButton("puta madre"));
	    dtm= new DefaultTableModel(null,d) {
	    	 public boolean isCellEditable(int row, int column) {
	    		 return false;
	    	 }
	     };
	     table = new JTable(dtm);
		 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		 scrollPane = new JScrollPane(table); 
		 scrollPane.addMouseListener(new Click());
        t.add(scrollPane);
        t.addMouseListener(new Click());
        table.requestFocus();
        table.addMouseListener(new Click());
        this.add(t);
        Calendar cal= Calendar.getInstance();
        //MOSTRAR DE MANERA LOCAL VIAJES DEL DIA
        /*
        try {
        	
        	
			mostrar(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
        
        
        
        //MOSTRAR DESDE EL SERVIDOR LOS VIAJES DEL DIA
        mostrarDeServidor(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
        t.setBounds(95,220,700,200);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        lbHora.setFont(new Font("Arial", Font.BOLD, 25));
        lbHora.setBounds(900,100,200,250);
        this.add(lbHora);
        
        ImageIcon l = new ImageIcon(getClass().getResource("/img/logo.png"));
        ImageIcon lo = new ImageIcon(l.getImage().getScaledInstance(140, 120, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(lo);
        logo.setBounds(900,390,140,120);
        this.add(logo);
        
        ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo.png"));
        fondo = new JLabel();
        fondo.setIcon(f);
        fondo.setBounds(0,45,1200,580);
        this.add(fondo);
        
        admin = new ifg.Button(true);
        admin.setForeground(Color.black);
        ImageIcon a = new ImageIcon(getClass().getResource("/img/admin.png"));
        admin.setIcon(a);
        admin.setBounds(1000,0,128,30);
        this.add(admin);
        admin.addMouseListener(new Click());
        this.add(admin);
        
        
        options = new ifg.Button(true);
        options.setForeground(Color.black);
        options.setColor1(new Color(255, 196, 0));
        options.setColor2(new Color(202, 147, 0));
        ImageIcon tie = new ImageIcon(getClass().getResource("/img/lupa.png"));
        options.setIcon(tie);
        options.setBounds(0,30,70,55);
        options.setBorder(null);
        options.addMouseListener(new Click());
        this.add(options);
        
        
        ver = new JButton();
        ver.setForeground(Color.black);
        //ver.setColor1(new Color(255, 196, 0));
        //ver.setColor2(new Color(202, 147, 0));
        ver.setBounds(500,450,150,35);
        ver.setText("Todos los registros");
        ver.addMouseListener(new Click());
        this.add(ver);
        agregar = new JButton();
        agregar.setForeground(Color.black);
        //agregar.setColor1(new Color(255, 196, 0));
        //agregar.setColor2(new Color(202, 147, 0));
        agregar.setText("Agregar Elementos");
        agregar.setBounds(280,450,150,35);
        //agregar.setBorder(null);
        agregar.addMouseListener(new Click());
        this.add(agregar);
    }
    /**
     * Método que calcula la hora y le otorga un formato
     */
    public void calcular(){
        Calendar calendario = new GregorianCalendar();
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
    /**
     * Clase Click que hereda de MouseAdapter, controla todos los clicks del JFrame principal
     * @author sheen
     *
     */
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == cerrar) {
    			cerrar();
    		}
    		else if (e.getSource() == minimizar) {
    			minimizar();
    			des.setVisible(false);
    			ayuda.setVisible(false);
    		}
    		else if(e.getSource() == admin) {
    			
    			ayuda.setVisible(true);
    		}
    		else if (e.getSource() == verMapa) {
    			Mapa m = new Mapa();
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    		}
    		else if (e.getSource() == notificar) {
    			Notificacion n = new Notificacion();
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    		}else if (e.getSource() == options) {
    			//options.setVisible(false);
    			des.setVisible(true);
    			ayuda.setVisible(false);
    			
    		}else if (e.getSource() == ver) {
    			
    			
    			new Database();
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    		}else if (e.getSource() == agregar) {
    			//options.setVisible(false);
    			new VentanaAgregar();
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    			
    		}
    		else if (e.getSource() == table || e.getSource() == scrollPane) {
    			//options.setVisible(false);
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    			
    		}else if(e.getSource() == cont) {
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			if(dateChooser.getDate()==null) {
					JOptionPane.showMessageDialog(null, "La fecha seleccionada es incorrecta");
					dateChooser.setDate(null);
				}else {
					mostrarDeServidor(dateChooser.getDate().getDate(),dateChooser.getDate().getMonth(),dateChooser.getDate().getYear());
				}
    			/*
    			try {
						if(dateChooser.getDate()==null) {
							JOptionPane.showMessageDialog(null, "La fecha seleccionada es incorrecta");
							dateChooser.setDate(null);
						}else {
							mostrar(dateChooser.getDate().getDate(),dateChooser.getDate().getMonth(),dateChooser.getDate().getYear());
						}	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				*/
    		}else {
    			des.setVisible(false);
    			ayuda.setVisible(false);
    			
    			
    		}
    		
    	}
    }
    /**
     * Metodo para cerrar la aplicación
     */
    public void cerrar() {
    		System.exit(0);
    }
    /**
     * Método para minimizar la aplicación
     */
    public void minimizar() {
		this.setExtendedState(ICONIFIED);
    }
    /**
     * Método run del reloj
     */
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
            this.remove(lbHora);
            this.add(lbHora);
            this.add(fondo);
        }
    }
    
    public void mostrarDeServidor(int d, int m, int y) {
    		dtm.setRowCount(0);
    		if(y<2000) {
				y=y-100;
				y=y+2000;
		}
    		ConexionServer con = new ConexionServer();
    		String consulta = "SELECT taxista_viaje_taxi.id_viaje,no_placas,nickname_u,hora_inicio,hora_final,origen,destino,viaje.estado,monto_pagado,taxista_viaje_taxi.id_taxista,nombre,apaterno FROM taxista_viaje_taxi INNER JOIN viaje ON taxista_viaje_taxi.id_viaje = viaje.id_viaje INNER JOIN taxista ON taxista_viaje_taxi.id_taxista = taxista.id_taxista where viaje.fecha ='"+y+"-"+(m+1)+"-"+d+"'";
    		String resultado[] = con.Select(consulta, 20);
    		System.out.println(resultado[0]);
    		if(resultado[0].equals("0 resultdcs ")) {
    			String modelo[] = {"Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes","Sin viajes"};
    			dtm.addRow(modelo);
    		}else {
    			for(int i = 0;i<resultado.length;i++) {
        			String modelo[] = resultado[i].split("<>");
        			dtm.addRow(modelo);
            }
    		}
    		
    }

    /**
     * Muestra los viajes del día indicado DE MANERA LOCAL
     * @param d = día
     * @param m = mes
     * @param y = año
     * @throws SQLException
     */
    public void mostrar(int d, int m, int y) throws SQLException {
    	try {
    			
    			dtm.setRowCount(0);
    			if(y<2000) {
    				y=y-100;
    				y=y+2000;
    			}
    			Conexion c = new Conexion();
			conexion = c.conexionDB();
			sentencia = conexion.createStatement();
			String s = "SELECT taxista_viaje_taxi.id_viaje,no_placas,nickname_u,hora_inicio,hora_final,origen,destino,viaje.estado,monto_pagado,taxista_viaje_taxi.id_taxista,nombre,apaterno FROM oaxataxi.taxista_viaje_taxi INNER JOIN oaxataxi.viaje ON taxista_viaje_taxi.id_viaje = viaje.id_viaje INNER JOIN oaxataxi.taxista ON taxista_viaje_taxi.id_taxista = taxista.id_taxista where viaje.fecha ='"+y+"-"+(m+1)+"-"+d+"'";
			resultado = sentencia.executeQuery(s);
			while ( resultado.next() ) {
				String no_placas = resultado.getString("no_placas");
				String id_viaje = resultado.getString("id_viaje");
	    		String nickname = resultado.getString("nickname_u");
	    		String hora_inicio = resultado.getString("hora_inicio");
	    		String hora_final = resultado.getString("hora_final");
	    		String origen = resultado.getString("origen");
	    		String destino = resultado.getString("destino");
	    		String estado = resultado.getString("estado");
	    		String monto_pagado = resultado.getString("monto_pagado");
	    		String id_taxista = resultado.getString("id_taxista");
	    		String nombre = resultado.getString("nombre");
	    		String apaterno = resultado.getString("apaterno");
	    		String [] modelo={id_viaje,id_taxista,nombre+" "+apaterno,no_placas,nickname,estado,hora_inicio,hora_final,origen,destino,"$"+monto_pagado};
	    		dtm.addRow(modelo);
	    		
	         }
			
	         resultado.close();
	         sentencia.close();
	         conexion.close();
	    
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	    } finally {
	        if (sentencia != null) { sentencia.close(); }
	    }
    }
}
