/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
public class findMultipliers extends JFrame{
    JLabel label;
    JTextField textField;
    JButton button;
    public findMultipliers(){
        setTitle("Bölen Hesaplama");
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
            try {
                int number = Integer.parseInt(textField.getText());
                ArrayList<Integer> multipliers = multiplierCalc(number);
                JOptionPane.showMessageDialog(this, "Bölenler: " + multipliers);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Geçersiz giriş. Lütfen bir sayı girin.");
            }
        });
        
    }
    
     private ArrayList<Integer> multiplierCalc(int number) {
        int rNumber;
        
        ArrayList<Integer> multipliersList = new ArrayList<>();
        
        if(number>0){
            for (int i = 1; i <= number + 1; i++) {
                int remain = number % i;

                if (remain == 0) {
                    multipliersList.add(i);
                }
            }
        }else if(number < 0){
            rNumber = -number;
            
            for (int i = 1; i <= rNumber + 1; i++) {
                int remain = rNumber % i;

                if (remain == 0) {
                    multipliersList.add(i);
                }
            }
        }
        
        return multipliersList;
    }
}
