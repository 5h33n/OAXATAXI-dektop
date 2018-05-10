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
    JTextField cajaId;
	JTextField cajaNombre;
	JTextField cajaAm;
	JTextField cajaAp;
	JTextField cajaTel;
	
    String insert;
    JPanel sPanel;
    JComboBox cbPersonas;
    
    private Connection conexion=null;
    ResultSet resultado;
    Statement sentencia;
    public VentanaAgregar() {
        this.setUndecorated(false);
        this.setSize(399, 399);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
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
    			insert = "INSERT INTO oaxataxi.taxista( "
    		        +"    id_taxista, nombre, apaterno, amaterno, licencia, telefono, c_tel, "
    		        +"    foto, estado, comentarios, puntuacion)"
    		   +" VALUES ("+ cajaId.getText() +", '"+ cajaNombre.getText() +"', '"+ cajaAp.getText() +"', '"+ cajaAm.getText() +"', 'htt://temporalmente.ausente', '"+ cajaTel.getText() +"', '+52', "
    		      +"      'https://ausente', 'Recientemente agregado', '', NULL);";
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
    		    cajaId.setText("");
    		    cajaNombre.setText("");
    		    cajaAp.setText("");
    		    cajaAm.setText("");
    		    cajaTel.setText("");
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
        botAgregar.setColor1(new Color(135,142,251));
        botAgregar.setColor2(new Color(112,120,224));
        botAgregar.setBounds(140,404,120,30);
        botAgregar.addMouseListener(new Click());
        this.add(botAgregar);
        
        cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.setForeground(Color.black);
        cancelar.setColor1(new Color(135,142,251));
        cancelar.setColor2(new Color(112,120,224));
        cancelar.setBounds(320,404,120,30);
        cancelar.addMouseListener(new Click());
        this.add(cancelar);
              
        sPanel = new JPanel();       
        //sPanel.setLayout(new GridLayout(0,4));
        JScrollPane scrollPane = new JScrollPane(sPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 400,300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(400, 200));
        contentPane.add(scrollPane);
        contentPane.setBounds(100,90,400,300);
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
    	
    	
    	JLabel id = new JLabel("id_taxista");
    	JLabel nombre = new JLabel("Nombre");
    	JLabel ap = new JLabel("Apellido paterno");
    	JLabel am = new JLabel("Apellido materno");
    	JLabel tel = new JLabel("Telefono");
    	
    	
    	
    	 cajaId = new JTextField(20);
    	 cajaNombre = new JTextField(20);
    	 cajaAm = new JTextField(20);
    	 cajaAp = new JTextField(20);
    	 cajaTel = new JTextField(20);
    	
    	id.setFont(new Font("Arial", Font.BOLD, 13));
        id.setBounds(100,30,120,25);
        sPanel.add(id);
        cajaId.setBounds(220,30,100,25);
        sPanel.add(cajaId);
        
        
        nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setBounds(100,80,150,25);
        sPanel.add(nombre);
        cajaNombre.setBounds(220,80,100,25);
        sPanel.add(cajaNombre);
        
        ap.setFont(new Font("Arial", Font.BOLD, 13));
        ap.setBounds(100,120,150,25);
        sPanel.add(ap);
        cajaAp.setBounds(220,120,100,25);
        sPanel.add(cajaAp);
        
        am.setFont(new Font("Arial", Font.BOLD, 13));
        am.setBounds(100,170,150,30);
        sPanel.add(am);
        cajaAm.setBounds(220,170,100,25);
        sPanel.add(cajaAm);
        
        tel.setFont(new Font("Arial", Font.BOLD, 13));
        tel.setBounds(100,220,150,30);
        sPanel.add(tel);
        cajaTel.setBounds(220,220,100,25);
        sPanel.add(cajaTel);
        

    }
    public void agregarTaxi() {
    	sPanel.removeAll();
    	sPanel.repaint();
    	
    	JLabel id_taxi = new JLabel("Id del taxi");
    JLabel nPlacas = new JLabel("N�mero de placas");
    cajaid = new JTextField();
    cajaplacas = new JTextField();
    
	id_taxi.setFont(new Font("Arial", Font.BOLD, 13));
    id_taxi.setBounds(100,30,120,30);
    sPanel.add(id_taxi);
    nPlacas.setFont(new Font("Arial", Font.BOLD, 13));
    nPlacas.setBounds(100,90,150,30);
    sPanel.add(nPlacas);
    cajaid.setBounds(220,30,100,30);
    sPanel.add(cajaid);
    cajaplacas.setBounds(220,90,100,30);
    sPanel.add(cajaplacas);
    }
    public void conexionDB() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://localhost:5432/oaxataxi";
			conexion = DriverManager.getConnection(url,"postgres","Pacomegoma12");
			if (conexion!=null) {
				System.out.println("Conexion exitosa");
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
			JOptionPane.showMessageDialog(null, "Elemento correctamente agregado");
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
