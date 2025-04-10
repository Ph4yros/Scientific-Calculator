/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author EREN
 */
public class primeNumber extends JFrame {
    String numberİnput;
    boolean booleanResult;
    JLabel label;
    JTextField textField;
    JButton button;
    public primeNumber(){
        setTitle("Asal Sayı Mı?");
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
        
        button = new JButton("Kontrol Et");
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

                booleanResult = primeFactorControl(numberİnputİnt);

                if(booleanResult == true){
                JOptionPane.showMessageDialog(this, "Bu Bir Asal Sayıdır.");
                }else{
                int nearestBigNumber = nearestBigPrimeNumber(numberİnputİnt);
                int nearestlittleNumber = nearestLittlePrimeNumber(numberİnputİnt);

                JOptionPane.showMessageDialog(this, "Bu Bir Asal Sayı Değildir.\n" + "En Yakın Büyük Asal Sayı: " + nearestBigNumber+ "\n" + "En Yakın Küçük Asal Sayı: " +nearestlittleNumber);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Geçersiz giriş. Lütfen bir sayı girin.");
            }
            
        });
        

        
        
    }
    
    public boolean primeFactorControl(int number){
        boolean result;
        int remain;
        ArrayList<Integer> multipliersList = new ArrayList<>();
        
        for(int i = 2; i<number;i++){
            remain = number % i;
            
            if (remain == 0){
                multipliersList.add(i);
            }
        }
        
        if(multipliersList.isEmpty()){
            result = true;
        }else{
            result = false;
        }
        
        return result;
    }
    
    public int nearestBigPrimeNumber(int number){
         int increaseNumber = number;
         while(true){
             boolean result;
             
             increaseNumber += 1;
             
             result = primeFactorControl(increaseNumber);
             
             if(result == true){
                 break;
             }
         }
         
         return increaseNumber;
    }
    
    public int nearestLittlePrimeNumber(int number){
         int decreaseNumber = number;
         while(true){
             boolean result;
             
             decreaseNumber -= 1;
             
             result = primeFactorControl(decreaseNumber);
             
             if(result == true){
                 break;
             }
         }
         
         return decreaseNumber;
    }
}
