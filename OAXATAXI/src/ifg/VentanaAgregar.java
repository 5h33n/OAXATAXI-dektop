package ifg;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class VentanaAgregar extends JFrame {
    JLabel titulo = new JLabel("Agregar");
    JLabel agregar = new JLabel("Agregar nuevo");
    JLabel foto = new JLabel();
    Button botAgregar = new Button();
    Button cancelar = new Button();
    Button adFoto = new Button();
    JTextField[] fields;
    JTextField cajaid;
    JTextField cajaplacas;
    String insert;
    JPanel sPanel;
    JComboBox cbPersonas;
    
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    public VentanaAgregar() {
        this.setUndecorated(true);
        this.setSize(399, 399);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocationRelativeTo(null);
        crearComponentes();
    }
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == cancelar) {
    			bye();
    		}if (e.getSource() == botAgregar && cbPersonas.getSelectedItem() == "Taxi"){
    			insert = "INSERT INTO oaxataxi.taxi(id_taxi, no_placas, estado, comentarios, puntuacion)VALUES ("+cajaid.getText()+", '"+cajaplacas.getText()+"', "+"'Agregado recientemente', '', NULL);";
    		    try {
    				conexionDB();
    			} catch (SQLException ex) {
    				// TODO Auto-generated catch block
    				ex.printStackTrace();
    			}
    		    try {
    				insertar(insert);
    			} catch (SQLException ex) {
    				// TODO Auto-generated catch block
    				ex.printStackTrace();
    			}
    		    cajaid.setText("");
    		    cajaplacas.setText("");
    		}if (e.getSource() == botAgregar && cbPersonas.getSelectedItem() == "Taxista"){
    			/*insert = "INSERT INTO oaxataxi.taxi(id_taxi, no_placas, estado, comentarios, puntuacion)VALUES ("+id_taxi+", '"+nPlacas+"', "+"'Agregado recientemente', '', NULL);";
    		    try {
    				conexionDB();
    			} catch (SQLException ex) {
    				// TODO Auto-generated catch block
    				ex.printStackTrace();
    			}
    		    try {
    				insertar(insert);
    			} catch (SQLException ex) {
    				// TODO Auto-generated catch block
    				ex.printStackTrace();
    			}*/
    		}
    	}
    }
    public void bye() {
    	this.dispose();
    }
    public void crearComponentes()
    {
        this.setSize(600,500);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(270,10,100,40);
        this.add(titulo);
        
        botAgregar = new Button();
        botAgregar.setText("Agregar");
        botAgregar.setForeground(Color.black);
        botAgregar.setColor1(new Color(255, 196, 0));
        botAgregar.setColor2(new Color(202, 147, 0));
        botAgregar.setBounds(140,404,120,30);
        botAgregar.addMouseListener(new Click());
        this.add(botAgregar);
        
        cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.setForeground(Color.black);
        cancelar.setColor1(new Color(255, 196, 0));
        cancelar.setColor2(new Color(202, 147, 0));
        cancelar.setBounds(320,404,120,30);
        cancelar.addMouseListener(new Click());
        this.add(cancelar);
              
        sPanel = new JPanel();       
        sPanel.setLayout(new GridLayout(0,4));
        JScrollPane scrollPane = new JScrollPane(sPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 0, 400,300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        contentPane.setBounds(60,90,700,300);
        this.add(contentPane);
        
        agregar.setFont(new Font("Arial", Font.BOLD, 13));
        agregar.setBounds(175,50,120,30);
        this.add(agregar);
        
        
        String [] unidades = {"","Taxista","Taxi"};
        cbPersonas = new JComboBox(unidades);
        cbPersonas.addMouseListener(new Click());
        cbPersonas.setBounds(330,50,100,30);
        this.add(cbPersonas);
        cbPersonas.addItemListener( new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
            		if ( e.getStateChange() == ItemEvent.SELECTED ) {
            			if(e.getItem() == "Taxista") {
            				agregarTaxista();
            			}else if(e.getItem() == "Taxi") {
            				agregarTaxi();
            			}else {
            				sPanel.removeAll();
            				sPanel.repaint();
            			}
                }
            }

         });
        
        
        
        
        ImageIcon f = new ImageIcon(getClass().getResource("/img/fondo2.png"));
        JLabel fondo = new JLabel();
        fondo.setIcon(f);
        fondo.setBounds(0,0,700,500);
        this.add(fondo);
    }
    public void agregarTaxista() {
    sPanel.removeAll();
    	sPanel.repaint();
    	JLabel nombre = new JLabel("Nombre");
    JLabel nacimiento = new JLabel("Fecha de Nacimiento");
    JTextField cajaNombre = new JTextField(20);
    	nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setBounds(25,60,120,30);
        sPanel.add(nombre);
        
        nacimiento.setFont(new Font("Arial", Font.BOLD, 13));
        nacimiento.setBounds(25,115,150,30);
        sPanel.add(nacimiento);
        
        foto.setIcon(new ImageIcon("../img/rojo.png"));
        foto.setBounds(35,170,100,120);
        sPanel.add(foto);
        
        adFoto.setText("Agregar foto...");
        adFoto.setForeground(Color.black);
        adFoto.setColor1(new Color(255, 196, 0));
        adFoto.setColor2(new Color(202, 147, 0));
        adFoto.setBounds(200,230,140,30);
        sPanel.add(adFoto);
        cajaNombre.setBounds(190,60,100,30);
        sPanel.add(cajaNombre);
        JTextField cajaNac = new JTextField();
        cajaNac.setBounds(190,115,100,30);
        sPanel.add(cajaNac);
    }
    public void agregarTaxi() {
    	String[] b = {"id_taxi","No. de placas",};
    	sPanel.removeAll();
    	sPanel.repaint();
    	JLabel id_taxi = new JLabel("Id del taxi");
        JLabel nPlacas = new JLabel("Número de placas");
    cajaid = new JTextField();
    cajaplacas = new JTextField();
    
	id_taxi.setFont(new Font("Arial", Font.BOLD, 13));
    nPlacas.setBounds(25,60,120,30);
    sPanel.add(id_taxi);
    nPlacas.setFont(new Font("Arial", Font.BOLD, 13));
    nPlacas.setBounds(25,115,150,30);
    sPanel.add(nPlacas);
    cajaid.setBounds(190,60,100,30);
    sPanel.add(cajaid);
    cajaplacas.setBounds(190,115,100,30);
    sPanel.add(cajaplacas);
    }
    public void conexionDB() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/oaxataxi";
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
    public void insertar (String s) throws SQLException {
    	try {
			conexionDB();
			sentencia = conexion.createStatement();
			System.out.println(s);
			sentencia.executeQuery(s);
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
