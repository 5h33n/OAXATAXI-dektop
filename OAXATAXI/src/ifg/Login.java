package ifg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Login extends JFrame {
    //Declaracion de componentes de la ventana
    private JLabel administrador, contrasena, olvidarContrasena;
    private JERoundTextField cajaAdministrador, cajaContrasena;
    private JButton pregunta, cerrar;
    private Button iniciar;
    private Color colorAdmin = new Color(250, 244, 194);
    private Color colorFondo = new Color(255,200,36);
    
    private class Click extends MouseAdapter{
    		public void mouseClicked(MouseEvent e) {
    			if (e.getSource() == cerrar) {
    				cerrar();
    			}
    			if (e.getSource() == iniciar) {
    				try {
    					Principal p = new Principal();
    					ocultar();
    				} catch (IOException ex) {
    					// TODO Auto-generated catch block
    					ex.printStackTrace();
    				}
    				
    			}
    		}
    }
    public void cerrar() {
    		System.exit(0);
    }
    public void ocultar() {
    		this.dispose();
    }
    public Login () {
    		crearComponentes();
        this.setUndecorated(true);
        this.setSize(450, 490);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());        
        this.setLocationRelativeTo(null);
    }
    public void crearComponentes() {
        this.setSize(400, 510);

        // Definicion de parametros de los componentes
        administrador = new JLabel("Administrador");
        contrasena = new JLabel("Contrase�a");
        olvidarContrasena = new JLabel("Olvide mi contrase�a");
        pregunta = new JButton("?");
        pregunta.setBackground(Color.blue);
        pregunta.setForeground(Color.white);
        cerrar = new JButton("X");
        cerrar.setBackground(Color.red);
        cerrar.setForeground(Color.white);
        cerrar.addMouseListener(new Click());
        iniciar = new Button(false);
        iniciar.addMouseListener(new Click());
        cajaAdministrador = new JERoundTextField();
        cajaContrasena = new JERoundTextField();
        // termina la definicion de parametros de los componentes

        this.setLayout(new BorderLayout());

        // Parte superior del panel Principal sera dividio en 2 secciones arriba y abajo -------1/1 y los el contenedor de ambos paneles sera el panel supPrin
        JPanel supPrin = new JPanel();
        supPrin.setLayout(new GridLayout(2,0));
        JPanel preguntaCerrar = new JPanel();// este sera el panel que estara arriba
        JPanel sPCent = new JPanel();
        sPCent.setBackground(colorFondo);
        JPanel pC = new JPanel();// este panel contendra los botones que seran puestos a la izquierda del panel preguntaCerrar
        pC.setLayout(new GridLayout(0,2));
        preguntaCerrar.setLayout(new BorderLayout());
        JPanel supD = new JPanel();
        supD.setBackground(colorFondo);
        // Paneles superior e inferior creados

        //Crear paneles exteriores sin funciones solo colores
        JPanel prinDer= new JPanel();
        prinDer.add(new JLabel("             "));
        prinDer.setBackground(colorFondo);
        JPanel prinIzq= new JPanel();
        prinIzq.add(new JLabel("             "));
        prinIzq.setBackground(colorFondo);
        JPanel prinInf= new JPanel();
        prinInf.add(new JPanelAdmin());
        prinInf.setBackground(colorFondo);
        //Creados los paneles exteriores

        //panel donde estaran los componentes
        JPanel supCP = new JPanel();
       supCP.setBackground(colorFondo);
        supCP.setLayout(new BorderLayout());
        
        JPanelRound centralPrincipal = new JPanelRound();
        supCP.add(centralPrincipal, BorderLayout.CENTER);
        
        centralPrincipal.setColorPrimario(colorAdmin);
        centralPrincipal.setColorSecundario(colorAdmin);
        centralPrincipal.setColorContorno(new Color(0, 0, 0));
        centralPrincipal.setLayout(null);
        
        centralPrincipal.add(administrador); 
        administrador.setFont(new Font("Verdana",0, 25));
        administrador.setForeground(Color.black);
        administrador.setBounds(95,50,200,30);
        
        centralPrincipal.add(cajaAdministrador);
        cajaAdministrador.setBounds(50,100,245,30);
        
        centralPrincipal.add(contrasena);
        contrasena.setFont(new Font("Verdana",0, 25));
        contrasena.setForeground(Color.black);
        contrasena.setBounds(95,170,200,30);
        
        centralPrincipal.add(cajaContrasena);
        cajaContrasena.setBounds(50,220,245,30);
        
        centralPrincipal.add(olvidarContrasena);
        olvidarContrasena.setFont(new Font("Verdana",0, 15));
        olvidarContrasena.setBounds(100,250,300,30);
        olvidarContrasena.setForeground(Color.blue);
        centralPrincipal.add(iniciar);
        
        JLabel in = new JLabel("iniciar");
        in.setFont(new Font("Verdana",0, 20));
        in.setBounds(170,350,100,40);
        centralPrincipal.add(in);
        centralPrincipal.add(iniciar);
        iniciar.setBounds(150,350,100,40);
        iniciar.setColor1(new Color(250, 244, 194));
        iniciar.setColor2(new Color(250, 244, 194));
        iniciar.setColor3(new Color(0,0,0));
        
        //termina panel de componentes
        
        // Añadir boton de pregunta y de cerrar
        pC.add(pregunta);
        pC.add(cerrar);
        preguntaCerrar.add(sPCent, BorderLayout.CENTER);
        preguntaCerrar.add(pC, BorderLayout.EAST);
        supPrin.add(preguntaCerrar);
        supPrin.add(supD);
        this.add(supPrin, BorderLayout.NORTH);
        this.add(prinDer, BorderLayout.WEST);
        this.add(prinIzq, BorderLayout.EAST);
        this.add(prinInf, BorderLayout.SOUTH);
        this.add(supCP, BorderLayout.CENTER);
        ((JComponent) this.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.black,2));
    }
}



