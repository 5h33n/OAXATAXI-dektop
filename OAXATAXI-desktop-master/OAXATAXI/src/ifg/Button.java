package ifg;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ButtonModel;
import javax.swing.JButton;


public class Button extends JButton{

    private Color color1 = new Color(0x666f7f);
    private Color color2 = new Color(0x262d3d);
    private Color color3 = new Color(0x262d3d);
    private boolean forma;
    public Button(boolean forma) {
        super();
        this.forma = forma;
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        
    }
    
        
    protected void paintComponent(Graphics g) {
        Color c1,c2,c3;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel m = getModel();

        Paint oldPaint = g2.getPaint();
        if (m.isArmed()){
            c2=color1.brighter();
            c1=color2.brighter();
            c3=color3;
        }else{
            c1=color1.brighter();
            c2=color2.brighter();
            c3=color3.brighter();
        }
        if (!m.isEnabled()){
            c2=color1.brighter();
            c1=color2.brighter();
            c3=color3.brighter();
        }
        int numero=0;
        if (forma) {
        		Rectangle2D.Float rr2d = new Rectangle2D.Float(
            		0,0,getWidth(),getHeight());
        		g2.clip(rr2d);
        		numero =4;
        }else {
	        	RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
	                0,0,getWidth(),getHeight()-1,20,20);
	        g2.clip(r2d);
	        numero =18;
        }
        
        g2.setPaint(new GradientPaint(0.0f, 0.0f, c1,
                0.0f, getHeight(), c2));
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.setStroke(new BasicStroke(4f));
        g2.setPaint(new GradientPaint(0.0f, 0.0f, c3,
                0.0f, getHeight(), c3));
        g2.drawRoundRect(0, 0, getWidth() -2 , getHeight() -2 , numero, 18);

        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor3() {
        return color3;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }

}