package com.mycompany.CalcBackend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class primeFactor extends JFrame {
    private JFrame frame;
    String numberİnput;
    JLabel label;
    JTextField textField;
    JButton button;
    public primeFactor() {
        setTitle("Asal Çarpan Hesaplayıcı");
        setSize(360, 150);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        label = new JLabel("Sayı Giriniz: ");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        upperPanel.add(label);
        
        textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(200,30));
        upperPanel.add(textField);
        
        add(upperPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        button = new JButton("Hesapla");
        button.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(button);
        
        add(buttonPanel);
        
        
         button.addActionListener(e -> {
            dispose();
            try{
                numberİnput = textField.getText();
                if (numberİnput == null || numberİnput.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Sayı Giriniz", "Hata!", JOptionPane.WARNING_MESSAGE);
                return;
                }
                int numberİnputİnt = Integer.parseInt(numberİnput);

                        
                ArrayList<Integer> primeMultipliers = primeFactor(numberİnputİnt);

                  if(primeMultipliers.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Asal Çarpanı Bulunmamakta.");
                }else{
                    JOptionPane.showMessageDialog(frame, "Asal Çarpanlar: " + primeMultipliers);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Geçersiz giriş. Lütfen bir sayı girin.");
            }
            
        });
    }
    
    private ArrayList<Integer> multiplierCalc(int number) {
        int rNumber;
        
        ArrayList<Integer> multipliersList = new ArrayList<>();
        
        if(number>0){
            for (int i = 1; i <= number; i++) {
                int remain = number % i;

                if (remain == 0) {
                    multipliersList.add(i);
                }
            }
        }else if(number < 0){
            rNumber = -number;
            
            for (int i = 1; i <= rNumber; i++) {
                int remain = rNumber % i;

                if (remain == 0) {
                    multipliersList.add(i);
                }
            }
        }
        
        return multipliersList;
    }
    
    public ArrayList<Integer> primeFactor(int number) {
        ArrayList<Integer> allMultipliers;
    
        try {
            allMultipliers = multiplierCalc(number);
            if (allMultipliers == null) {
                return new ArrayList<>(); 
            }
        } catch (Exception e) {
            System.out.println("Hata: multiplierCalc başarısız oldu" + e.getMessage());
            return new ArrayList<>();
        }

        Iterator<Integer> iterator = allMultipliers.iterator();
        while (iterator.hasNext()) {
            int currentNumber = iterator.next();

            ArrayList<Integer> currentNumberMultipliers;
            try {
                currentNumberMultipliers = multiplierCalc(currentNumber);
                if (currentNumberMultipliers == null || currentNumberMultipliers.size() != 2) {
                    iterator.remove();
                }
            } catch (Exception e) {
                System.out.println("Hata: multiplierCalc çağrısı sırasında hata oluştu" + e.getMessage());
                iterator.remove(); 
            }
    }
    
    return allMultipliers;
    }   
    
}
