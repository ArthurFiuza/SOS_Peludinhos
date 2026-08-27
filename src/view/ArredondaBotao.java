package view;

import javax.swing.JButton;
import java.awt.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ArredondaBotao extends JButton {

    private int radius;

    public ArredondaBotao(String label, int radius) {
        super(label);
        this.radius = radius;
        setContentAreaFilled(false); // 
        setFocusPainted(false);
        setBorder(new EmptyBorder(10, 20, 10, 20)); // 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }

       
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

      
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();

        g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        g2.dispose();
    }
}
