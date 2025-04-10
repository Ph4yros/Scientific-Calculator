/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author EREN
 */
public class CustomButtonForFormulaForm extends JButton {
    private Insets padding = new Insets(10, 20, 10, 20);

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    /**
     * @param colorOver the colorOver to set
     */
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the colorOver
     */
    public Color getColorOver() {
        return colorOver;
    }

    /**
     * @return the colorClick
     */
    public Color getColorClick() {
        return colorClick;
    }

    /**
     * @param colorClick the colorClick to set
     */
    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    /**
     * @return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
        return borderColor;
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the over
     */
    public boolean isOver() {
        return over;
    }

    /**
     * @param over the over to set
     */
    public void setOver(boolean over) {
        this.over = over;
    }
    private int radius = 10;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private boolean over;
    public CustomButtonForFormulaForm(String s){
         super(s);
        setColor(new Color(63,182,242));
        colorOver = new Color(70,160,225);
        colorClick = new Color(45,160,230);
        borderColor = new Color(92,114,133);
        setContentAreaFilled(false); 
        setFocusPainted(false);
        setRolloverEnabled(false);
        setBorderPainted(false);  
        
        addMouseListener(new MouseAdapter(){
           @Override
           public void mouseEntered(MouseEvent e) {
               setBackground(colorOver);
               over = true;
           }

           @Override
           public void mouseExited(MouseEvent e) {
               setBackground(color);
               over = false;
           }

           @Override
           public void mousePressed(MouseEvent e) {
               setBackground(colorClick);
               
           }

           @Override
           public void mouseReleased(MouseEvent e) {
               over = false; 
               if (over){
                   setBackground(colorOver);
               }else{
                   setBackground(color);
               }
           }
           
           
       });
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
    
    if (borderColor != null) {
        g2.setColor(borderColor);
    } else {
        g2.setColor(Color.GRAY);
    }
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

    g2.setColor(getBackground());
    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

    g2.setFont(new Font("Arial", Font.BOLD, 16));
    FontMetrics fm = g2.getFontMetrics();
    g2.setColor(Color.BLACK);

    String text = getText();
    int maxWidth = getWidth() - 20;
    String[] lines = splitTextIntoLines(text, fm, maxWidth);

    int totalTextHeight = lines.length * fm.getHeight();
    int y = (getHeight() - totalTextHeight) / 2 + fm.getAscent();

    for (String line : lines) {
        int textWidth = fm.stringWidth(line);
        int x = (getWidth() - textWidth) / 2;
        g2.drawString(line, x, y);
        y += fm.getHeight();
    }
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getHeight();

        int width = textWidth + 40;
    int height = textHeight + 20;

        return new Dimension(width, height);
    }
    private String[] splitTextIntoLines(String text, FontMetrics fm, int maxWidth) {
    StringBuilder line = new StringBuilder();
    java.util.List<String> lines = new java.util.ArrayList<>();
    for (String word : text.split(" ")) {
        if (fm.stringWidth(line + word) > maxWidth) {
            lines.add(line.toString());
            line = new StringBuilder();
        }
        if (line.length() > 0) {
            line.append(" ");
        }
        line.append(word);
    }
    if (line.length() > 0) {
        lines.add(line.toString());
    }
    return lines.toArray(new String[0]);
}
    
    private Color getBackgroundColor() {
    return getModel().isPressed() ? Color.LIGHT_GRAY : new Color(165, 191, 204);
}   
}
