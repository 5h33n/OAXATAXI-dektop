package ifg;
import javax.swing.*;

import recursos.Conexion;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class VentanaAgregar extends JFrame {
    JLabel titulo = new JLabel("Agregar");
    JLabel descripcion = new JLabel("Seleccione el elemento que desea agregar:");
    JLabel agregar = new JLabel("Agregar nuevo");
    JLabel foto = new JLabel();
    Button botAgregar = new Button(true);
    Button cancelar = new Button(true);
    Button adFoto = new Button(true);
    ResultSet r;
    JTextField[] fields;
    JTextField cajamodel;
    JTextField cajaplacas;
    JTextField cajaemail;
	JTextField cajaNombre;
	JTextField cajaAm;
	JTextField cajaAp;
	JTextField cajaNac;
	JTextField cajaTel;
	
    String insert;
    JPanel sPanel;
    JComboBox cbPersonas;
    Conexion c = new Conexion();
    
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
        //this.setLocationRelativeTo(null);
        this.setBounds(332,120,300,300);
        crearComponentes();
    }
    private class Click extends MouseAdapter{
    	public void mouseClicked(MouseEvent e) {
    		if (e.getSource() == cancelar) {
    			bye();
    			
    		}if (e.getSource() == botAgregar && cbPersonas.getSelectedItem() == "Taxi"){
    			if (cajaplacas.getText().length() == 0 || cajamodel .getText().length() == 0) {
    				JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {
            	try {
        			conexion = c.conexionDB();
    				sentencia = conexion.createStatement();
        			r = sentencia.executeQuery("select * from oaxataxi.taxi where no_placas='"+cajaplacas.getText()+"';");
        			if(r.next()) {
        				JOptionPane.showMessageDialog(null,"ERROR no se añadió el elemento, duplicidad en datos primarios");
        			}else {
        				insert = "INSERT INTO oaxataxi.taxi(no_placas,modelo,foto,estado, comentarios, puntuacion) VALUES ('"+cajaplacas.getText().toLowerCase()+"','"+cajamodel.getText().toLowerCase()+"','htt://temporalmente.ausente', "+"'Agregado recientemente', '', NULL);";
    	    		    //System.out.println(insert);
    	
    	    		    try {
    	    				insertar(insert);
    	    			} catch (SQLException ex) {
    	    				// TODO Auto-generated catch block
    	    				ex.printStackTrace();
    	    			}
        			}
    			} catch (SQLException ex) {
    				// TODO Auto-generated catch block
    				ex.printStackTrace();
    			}
            		//if(cajaid)
	    			
	    		    cajamodel.setText("");
	    		    cajaplacas.setText("");
            }
    		}if (e.getSource() == botAgregar && cbPersonas.getSelectedItem() == "Taxista"){
    			if (cajaemail.getText().length() == 0 || cajaNombre.getText().length() == 0 || cajaAp.getText().length() == 0 || cajaAm.getText().length() == 0 || cajaTel.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {
            	
	    			insert = "INSERT INTO oaxataxi.taxista( "
	    		        +"    nombre, apaterno, amaterno, licencia, email, telefono, c_tel, "
	    		        +"    fecha_nacimiento, foto, estado, comentarios, puntuacion)"
	    		   +" VALUES ('"+ cajaNombre.getText().toLowerCase() +"', '"+ cajaAp.getText().toLowerCase() +"', '"+ cajaAm.getText().toLowerCase() +"', 'htt://temporalmente.ausente', '"+ cajaemail.getText() +"','"+ cajaTel.getText() +"', '+52', "
	    		      +"      '"+ cajaNac.getText().toLowerCase() +"','https://ausente', 'Recientemente agregado', '', NULL);";
	    		    try {
	    				conexion = c.conexionDB();
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
	    		    cajaNac.setText("");
	    		    cajaemail.setText("");
	    		    cajaNombre.setText("");
	    		    cajaAp.setText("");
	    		    cajaAm.setText("");
	    		    cajaTel.setText("");
            }
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
        titulo.setBounds(270,5,100,40);
        this.add(titulo);
        descripcion.setFont(new Font("Arial", Font.BOLD, 15));
        descripcion.setBounds(150,40,400,40);
        this.add(descripcion);
        
        botAgregar = new Button(true);
        botAgregar.setText("Agregar");
        botAgregar.setForeground(Color.black);
        botAgregar.setColor1(new Color(135,142,251));
        botAgregar.setColor2(new Color(112,120,224));
        botAgregar.setBounds(140,410,120,30);
        botAgregar.addMouseListener(new Click());
        this.add(botAgregar);
        
        cancelar = new Button(true);
        cancelar.setText("Cancelar");
        cancelar.setForeground(Color.black);
        cancelar.setColor1(new Color(135,142,251));
        cancelar.setColor2(new Color(112,120,224));
        cancelar.setBounds(320,410,120,30);
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
        contentPane.setBounds(100,100,400,300);
        this.add(contentPane);
        
        agregar.setFont(new Font("Arial", Font.BOLD, 13));
        agregar.setBounds(175,70,120,30);
        this.add(agregar);
        
        
        String [] unidades = {"","Taxista","Taxi"};
        cbPersonas = new JComboBox(unidades);
        cbPersonas.addMouseListener(new Click());
        cbPersonas.setBounds(330,70,100,30);
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
    	
    	
    	JLabel nombre = new JLabel("Nombres:");
    	JLabel ap = new JLabel("Apellido paterno:");
    	JLabel am = new JLabel("Apellido materno:");
    	JLabel email = new JLabel("Email:");
    	JLabel tel = new JLabel("Telefono");
    	JLabel nac = new JLabel("Fecha de nacimiento:");
    	
    	
    	
    	 
    	 cajaNombre = new JTextField(20);
    	 cajaAm = new JTextField(20);
    	 cajaAp = new JTextField(20);
    	 cajaemail = new JTextField(20);
    	 cajaTel = new JTextField(20);
    	 cajaNac = new JTextField(20);
    	
    	nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setBounds(100,30,120,25);
        sPanel.add(nombre);
        cajaNombre.setBounds(220,30,100,25);
        sPanel.add(cajaNombre);
        
        
        ap.setFont(new Font("Arial", Font.BOLD, 13));
        ap.setBounds(100,80,150,25);
        sPanel.add(ap);
        cajaAp.setBounds(220,80,100,25);
        sPanel.add(cajaAp);
        
        am.setFont(new Font("Arial", Font.BOLD, 13));
        am.setBounds(100,120,150,25);
        sPanel.add(am);
        cajaAm.setBounds(220,120,100,25);
        sPanel.add(cajaAm);
        
        email.setFont(new Font("Arial", Font.BOLD, 13));
        email.setBounds(100,170,150,30);
        sPanel.add(email);
        cajaemail.setBounds(220,170,100,25);
        sPanel.add(cajaemail);
        
        tel.setFont(new Font("Arial", Font.BOLD, 13));
        tel.setBounds(100,220,150,30);
        sPanel.add(tel);
        cajaTel.setBounds(220,220,100,25);
        sPanel.add(cajaTel);
        
        nac.setFont(new Font("Arial", Font.BOLD, 13));
        nac.setBounds(100,240,150,30);
        sPanel.add(nac);
        cajaNac.setBounds(220,240,100,25);
        sPanel.add(cajaNac);
        
        cajaNombre.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < 'a' || c > 'z')) && (c != 32) && (c < 'A' || c > 'Z')) {

                    e.consume();

                }
            }
        }
        );
        cajaAp.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z')) {

                    e.consume();

                }
            }
        }
        );
        cajaAm.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < 'a' || c > 'z')) && (c < 'A' || c > 'Z')) {

                    e.consume();

                }
            }
        }
        );
        cajaTel.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0' || c > '9'))) {

                    e.consume();

                }
            }
        }
        );


    }
    public void agregarTaxi() {
    	sPanel.removeAll();
    	sPanel.repaint();
    	
    	JLabel nPlacas = new JLabel("Número de placas:");
    JLabel model = new JLabel("Modelo:");
    cajaplacas = new JTextField();
    cajamodel = new JTextField();
  //Validacion de cajas
    cajaplacas.addKeyListener(
            new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (((c < 'a' || c > 'z')) && (c != '\b') && ('-' != c) && (c < '0' || c > '9')) {

                e.consume();

            }
        }
    }
    );

    
	nPlacas.setFont(new Font("Arial", Font.BOLD, 13));
    nPlacas.setBounds(100,30,120,30);
    sPanel.add(nPlacas);
    model.setFont(new Font("Arial", Font.BOLD, 13));
    model.setBounds(100,90,150,30);
    sPanel.add(model);
    cajaplacas.setBounds(220,30,100,30);
    sPanel.add(cajaplacas);
    cajamodel.setBounds(220,90,100,30);
    sPanel.add(cajamodel);
    }
    
    public void insertar (String s) throws SQLException {
    	try {
    		//System.out.println(s);
			conexion = c.conexionDB();
			//sentencia.ex
			sentencia = conexion.createStatement();
			
			sentencia.executeUpdate(s);
	         //resultado.close();
	         sentencia.close();
	         conexion.close();
	         JOptionPane.showMessageDialog(null, "Elemento correctamente agregado");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,"ERROR no se añadió el elemento, duplicidad en datos primarios");
	    } finally {
	        if (sentencia != null) { sentencia.close(); }
	        
	    }
    }
}
