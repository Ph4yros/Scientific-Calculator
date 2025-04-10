
package com.mycompany.ui.panels;

import java.awt.*;
import javax.swing.*;
import com.mycompany.buttons.CustomButtonForCalcUi;
import com.mycompany.CalcBackend.CalcButtonListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcNumbersPanel extends javax.swing.JPanel implements ActionListener{
    
    String selectedButton;
    JTextField display = new JTextField();
    private CalcButtonListener buttonListener;

    public CalcNumbersPanel() {
        initComponents();
        
        setBackground(new Color(45, 45, 45));
        
        setLayout(new BorderLayout(10,10));

        
        display.setEditable(false);
        display.setBackground(Color.LIGHT_GRAY);
        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(530, 80)); 
        add(display, BorderLayout.NORTH);


        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        buttonPanel.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0,0, 5, 5);

        String[] buttons = {"%","√","C","←","7", "8", "9","÷", "4", "5", "6","-","1", "2", "3","x", ".","0","00","+","Formüller", "Değişken","="};
        int x = 0, y = 0;
        for (String text : buttons) {
            
            CustomButtonForCalcUi button = new CustomButtonForCalcUi(text);
            button.addActionListener(this);
  
            if (text.equals("=")){
                gbc.gridwidth = 2; 
                gbc.gridx = x;
                gbc.gridy = y;
                buttonPanel.add(button, gbc);
            }else{
                gbc.gridwidth = 1; 
                gbc.gridx = x;
                gbc.gridy = y;
                buttonPanel.add(button, gbc);
            }
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            
            x++;
            if (x > 3) { 
                x = 0;
                y++;
            }
        }




        add(buttonPanel, BorderLayout.CENTER);
        buttonListener = new CalcButtonListener(this);
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new CalcNumbersPanel());
        frame.setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        selectedButton = e.getActionCommand();
        
        buttonListener.handleButtonPress(selectedButton);        
    }   
   
    public String getCommand(){
        return selectedButton;
    }
    
    public String getDisplayText(){
        return display.getText();
    }
    
    public void addDisplayText(String text){
        String currentText = display.getText();
        
        display.setText(currentText + text);
    }
    
    public void cleanDisplayText(){
        display.setText("");
    }
    
    public String deleteLastOne(String str){
        StringBuilder sb = new StringBuilder(str);
        
        sb.deleteCharAt(str.length() - 1);
        
        return sb.toString();
        
    }
    
    public void deleteNumOnCalc(){
        String newString = deleteLastOne(display.getText());
        
        display.setText(newString);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
