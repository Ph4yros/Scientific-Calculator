
package com.mycompany.buttons;

import java.awt.*;
import javax.swing.*;


public class CustomButtonForCalcUi extends JButton {
    String ButtonText = new String();
    private int topMargin = 2;    
    private int bottomMargin = 2; 
    private int leftMargin = 3;   
    private int rightMargin = 3;
    
    public CustomButtonForCalcUi(String text) {
        super(text); 
        ButtonText = text;
        setContentAreaFilled(false);
        setRolloverEnabled(false);
        setBorderPainted(false);  
        setFont(new Font("Arial", Font.BOLD, 16));
    }
    
 
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        int width = getWidth() - leftMargin - rightMargin;
        int height = getHeight() - topMargin - bottomMargin;
        
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
        switch (ButtonText) {
            case "+":
            case "-":
            case "÷":
            case "x":
            case "=":
            case "←":
            case "C":
            case "√":
            case "%":
                g2.setColor(new Color(30, 136, 229));
                break;
            case "Formüller":
            case "Değişken":
                g2.setColor(new Color(200, 220, 240));
                break;
            default:
                g2.setColor(new Color(63, 182, 242));
                break;
        }
        
        if (getModel().isPressed()) {
            g2.setColor(new Color(45, 160, 230)); 
        }
        
        g2.fillRoundRect(leftMargin, topMargin, width, height, 16, 16);

        
        g2.setColor(Color.BLACK);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
         g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4);
    }
    
    
}
