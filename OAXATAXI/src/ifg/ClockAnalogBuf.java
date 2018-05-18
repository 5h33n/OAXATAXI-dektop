package ifg;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
 
public class ClockAnalogBuf extends JPanel {
 
    Clock clockFace;
    JLabel fondo;
    
    public ClockAnalogBuf() {
    
        Container content = new Container();
        this.setLayout(new BorderLayout());
        clockFace = new Clock();
        this.add(clockFace, BorderLayout.CENTER);
 
        //this.setTitle("Analog Clock");
        //this.pack();
    
        clockFace.start();
    }
}
 
 
 
class Clock extends JPanel {
 
    private int hours   = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int millis  = 0;
    JLabel fondo;
    private static final int   spacing = 10;
    private static final float twoPi = (float)(2.0 * Math.PI);
    private static final float threePi = (float)(3.0 * Math.PI);
    private static final float radPerSecMin = (float)(Math.PI / 30.0);
 
    private int size;      
    private int centerX;   
    private int centerY;    
    private BufferedImage clockImage;
    private javax.swing.Timer t;
 
 
    public Clock() {
    	
        this.setPreferredSize(new Dimension(300,300));
        this.setBackground(Color.white);
        this.setForeground(Color.black);
 
        t = new javax.swing.Timer(1000,
              new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      update();
                  }
              });
        
    }
 
 
    public void update() {
 
        this.repaint();
    }
 
    public void start() {
        t.start(); 
    }
    public void stop() {
        t.stop();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
 
 
        int w = getWidth();
        int h = getHeight();
        size = ((w<h) ? w : h) - 2*spacing;
        centerX = size/2 + spacing;
        centerY = size/2 + spacing;
 
 
        if (clockImage == null
                || clockImage.getWidth() != w
                || clockImage.getHeight() != h) {
 
            clockImage = (BufferedImage)(this.createImage(w, h));
            Graphics2D gc = clockImage.createGraphics();
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            drawClockFace(gc);
        }
 
 
        Calendar now = Calendar.getInstance();
        hours   = now.get(Calendar.HOUR);
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
        millis  = now.get(Calendar.MILLISECOND);
 
 
        g2.drawImage(clockImage, null, 0, 0);
 
        drawClockHands(g);
    }
 
    private void drawClockHands(Graphics g) {
        int secondRadius = size/2;
        int minuteRadius = secondRadius * 3/4;
        int hourRadius   = secondRadius/2;
 
 
        float fseconds = seconds + (float)millis/1000;
        float secondAngle = threePi - (radPerSecMin * fseconds);
        drawRadius(g, centerX, centerY, secondAngle, 0, secondRadius);
 
 
        float fminutes = (float)(minutes + fseconds/60.0);
        float minuteAngle = threePi - (radPerSecMin * fminutes);
        drawRadius(g, centerX, centerY, minuteAngle, 0, minuteRadius);
 
        float fhours = (float)(hours + fminutes/60.0);
        float hourAngle = threePi - (5 * radPerSecMin * fhours);
        drawRadius(g, centerX, centerY, hourAngle, 0, hourRadius);
    }
    private void drawClockFace(Graphics g) {
 
        g.setColor(Color.yellow);
        g.fillOval(spacing, spacing, size, size);
        g.setColor(Color.black);
        g.drawOval(spacing, spacing, size, size);
 
 
        for (int sec = 0; sec<60; sec++) {
            int ticStart;
            if (sec%5 == 0) {
                ticStart = size/2-10;
            } else {
                ticStart = size/2-5;
            }
            drawRadius(g, centerX, centerY, radPerSecMin*sec, ticStart , size/2);
        }
    }
    private void drawRadius(Graphics g, int x, int y, double angle,
                    int minRadius, int maxRadius) {
        float sine   = (float)Math.sin(angle);
        float cosine = (float)Math.cos(angle);
 
        int dxmin = (int)(minRadius * sine);
        int dymin = (int)(minRadius * cosine);
 
        int dxmax = (int)(maxRadius * sine);
        int dymax = (int)(maxRadius * cosine);
        g.drawLine(x+dxmin, y+dymin, x+dxmax, y+dymax);
    }
 
}