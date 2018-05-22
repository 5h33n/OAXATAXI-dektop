package ifg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JPanel{
	private JPanel panelSuperior, panelMedio, panelInferior;
	private JButton nuevo, editar,eliminar,bg,bg2;
	private DefaultTableModel dtm;
	private JTable table=new JTable(dtm);
	JTextField[] fields;
	public JTextField[] getFields() {
		return fields;
	}
	public void setFields(JTextField[] fields) {
		this.fields = fields;
	}

	private static Object[][] a;
	private static String[] b;
	private int id;
	private int efe;
	private Database db;
	
	public DefaultTableModel getDtm() {
		return dtm;
	}
	public void setDtm(DefaultTableModel dtm) {
		this.dtm = dtm;
		table.setModel(dtm);
	}
	public PanelTabla(Object[][] a, String[] b,Database db) {	
			this.db = db;
		 	this.a =a;
		 	this.b =b;
		 	init();
	}
	public void init() {
		construyePanelSuperior(a,b);
	     //construyePanelMedio(a,b);
	     construyePanelInferior(a,b);
	     construyeVentana();	
	}
	public void construyePanelSuperior(Object[][] a,String[] b){
		panelSuperior =new JPanel();
		panelSuperior.setLayout(new GridLayout(0,4));
		int cuantos = b.length;
		fields = new JTextField[b.length];
		for(int i=0;i<cuantos;i++) {
			//String cadena= String.valueOf(a[0][i]);
			JLabel label = new JLabel(b[i]);
			fields[i] = new JTextField();	
			fields[i].setEditable(false);
			panelSuperior.add(label);
			panelSuperior.add(fields[i]);	
		}   	
	}
	
	
	
	public void construyePanelMedio(Object[][] a, String[] b){
		 panelMedio=new JPanel();    
	     nuevo =new JButton("Nuevo ");
	     editar=new JButton("Editar");
	     eliminar=new JButton("Eliminar ");
	     bg=new JButton("Guardar ");
	     bg2=new JButton("Guardar ");
	     nuevo.addMouseListener(new MyClickListener());
	     editar.addMouseListener(new MyClickListener());
	     eliminar.addMouseListener(new MyClickListener());
	     bg.addMouseListener(new MyClickListener());
	     bg2.addMouseListener(new MyClickListener());
	     panelMedio.add (nuevo, BorderLayout.WEST);
	     panelMedio.add (editar, BorderLayout.CENTER);
	     panelMedio.add (eliminar, BorderLayout.EAST);
	     panelMedio.add (bg, BorderLayout.EAST);
	     panelMedio.add (bg2, BorderLayout.EAST);
	     bg.setVisible(false);   
	     bg2.setVisible(false); 
	     editar.setVisible(false);
	}
	
	
	
	public void construyePanelInferior(Object[][] a, String[] b){
		 panelInferior=new JPanel();
	     panelInferior.setLayout(new BorderLayout());
	     dtm= new DefaultTableModel(a,b) {
	    	 public boolean isCellEditable(int row, int column) {
	    		 return false;
	    	 }
	     };
	     table = new JTable(dtm);
	     
	     table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    	 public void valueChanged(ListSelectionEvent e) {
	    		 if (e.getValueIsAdjusting())
	    			 	return;
	    		 int sRow = filaseleccionada();
	    		 for(int j=0;j<fields.length;j++) {
	    			 
	    			
	    			 if(table.getRowCount()!=0 && table.getSelectedRow()!=-1){
     			String cadena= String.valueOf(table.getValueAt(sRow, j));
     			fields[j].setText(cadena);
	    			 }
	    		 }
	    		 //editar.setVisible(true);
	    	 }
	    	 });
		 table.setPreferredScrollableViewportSize(new Dimension(800, 70));
		 JScrollPane scrollPane = new JScrollPane(table); 
         panelInferior.add(scrollPane);
         table.requestFocus();
	}
	/*
	String[] b2 = {"id_Taxista:","Nombre:","Apellidos:","Licencia enlace:","Telefono:","Foto enlace:","Estado:","Comentarios:","Puntuación:"};
	resultado = sentencia.executeQuery("SELECT * FROM oaxataxi.taxista");
	while ( resultado.next() ) {
		String id_taxista = resultado.getString("id_taxista");
		String nombre = resultado.getString("nombre");
		String apaterno = resultado.getString("apaterno");
		String amaterno = resultado.getString("amaterno");
		String licencia = resultado.getString("licencia");
		String telefono = resultado.getString("telefono");
		String c_tel = resultado.getString("c_tel");
		String foto = resultado.getString("foto");
		String estado = resultado.getString("estado");
		String comentarios = resultado.getString("comentarios");
		String puntuacion = resultado.getString("puntuacion");
			String [] modelo={id_taxista,nombre,apaterno+" "+amaterno,licencia,c_tel+telefono,foto,estado,comentarios,puntuacion};
			dtm2.addRow(modelo);
     }*/
	 public void agregaFila(DefaultTableModel dtm) {
      
		 Object[] newRow = new Object[fields.length+1];
		 id++;
		 
 		for(int j=0;j<fields.length;j++) {
 			newRow[j]= fields[j].getText();
 		}
 		 dtm.addRow(newRow);
 		 bg.setVisible(false);
 		for(int i=0;i<fields.length;i++) {
    		fields[i].setEditable(false);}
	}

	 public void eliminaFila(DefaultTableModel dtm) {
		 int fs= filaseleccionada();
		 dtm.removeRow(fs);
	 }
	 public int filaseleccionada() {
		 int filaseleccionada;
		 filaseleccionada = table.getSelectedRow();
		 //System.out.println(filaseleccionada);
 		return filaseleccionada;
	 }
	 public void guardaredit(DefaultTableModel dtm, int fs) {
		 //int fs= filaseleccionada();
		 for(int k=0;k<fields.length;k++) {
			 
			 
			 dtm.setValueAt(fields[k].getText(), fs, k);}
		 bg.setVisible(false);
		 for(int i=0;i<fields.length;i++) {
	    		fields[i].setEditable(false);}
	 }
	 private class MyClickListener extends MouseAdapter{
	        @Override
	        public void mouseClicked(MouseEvent event){
	        	if(  event.getSource() == nuevo){
	        		for(int i=0;i<fields.length;i++) {
	        			fields[i].setText("");
	        		    fields[i].setEditable(true);
	        		    editar.setVisible(false);
	        		}
	        		bg.setVisible(true);
	        	}
	        	if(  event.getSource() == editar){
	        		try {
	        			efe = table.getSelectedRow();
	        			if(table.getRowCount()!=0 && table.getSelectedRow()!=-1){
	        				
	        				for(int i=0;i<fields.length;i++) {
			        		    fields[i].setEditable(true);
			        		}
			        		bg2.setVisible(true);
	        			}
	        		}catch (Exception e) {
	        			JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento para editarlo");
	        		}
	        		
	        	}
	        	if(  event.getSource() == eliminar){
	        		try {
	        			//if(table.getRowCount()!=0 && table.getSelectedRow()!=-1){
	        				eliminaFila(dtm);
	        			//}
	        	
	        				if (table.getSelectedRow()<-1) {
	        					JOptionPane.showMessageDialog(null, "No hay elementos seleccionados para borrar");
	        				}
	        				if(! (table.getRowCount()!=0 && table.getSelectedRow()!=-1)){
	        				for(int j=0;j<fields.length;j++) {
	       	    			 
	            			fields[j].setText("");
	            			editar.setVisible(false);
	       	    			 }
	       	    		 }
	        		}catch (Exception e) {
	        		}
	        	}
	        	if(  event.getSource() == bg){	
	        		agregaFila(dtm);
	        		
	        		editar.setVisible(true);
	        	}
	        	if(  event.getSource() == bg2){	
	        		guardaredit(dtm,efe);  
	        		bg2.setVisible(false);
	        	}
	         }          
	 }
	
    public void construyeVentana(){    
	        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
	        this.add(panelSuperior);
	        //this.add(panelMedio);
	        this.add(panelInferior);
	    }

}
