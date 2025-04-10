/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author EREN
 */
public class modeBtnBackend extends JFrame{
    JTextArea textArea;
    JLabel label;
    
    public modeBtnBackend(){
        setTitle("Mod Hesaplayıcı");
        setSize(300,400);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        add(new JLabel("Sayı Dizisini Giriniz: "));
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        label = new JLabel("Sayı Dizisini Giriniz:");
        label.setFont(new Font("Arial",Font.BOLD,18));
        centerPanel.add(label , BorderLayout.NORTH);
        
        textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        
        
        JButton hesaplaButton = new JButton("Modu Hesapla");
       
        hesaplaButton.addActionListener((ActionEvent e) -> {
            dispose();
            String[] numbers = cleanTextArea(textArea);
            double[] numbersDoubleArray = new double[numbers.length];
            
            for (int i = 0; i < numbers.length; i++) {
                try {
                    numbersDoubleArray[i] = Double.parseDouble(numbers[i]);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Geçersiz sayı girişi: " + numbers[i]);
                    return;
                }
            }
            
            String mode = calculateMode(numbersDoubleArray);
            JOptionPane.showMessageDialog(null, mode);
        });
        
        add(hesaplaButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public String[] cleanTextArea(JTextArea textAreaFF){
        String inputText = textAreaFF.getText();
        String[] numbers = inputText.split("[,\\s]+");
        
        return numbers;
    }
    
    public String calculateMode(double[] numbers) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (double num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); 
        }

        int maxCount = 0;
        for (int count : frequencyMap.values()) {
            maxCount = Math.max(maxCount, count); 
        }

        ArrayList<Double> modes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        if (modes.size() > 1) {
            return "Birden Fazla Mod: " + modes.toString() + " (" + frequencyMap.get(modes.get(0)) + " kez)";
        } else {
            return "Mod: " + modes.get(0) + " (" + frequencyMap.get(modes.get(0)) + " kez)";
        }
    }
}
