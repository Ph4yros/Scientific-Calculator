/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author EREN
 */
public class Variance extends JFrame{
     JTextArea textArea;
     JLabel label;
     public Variance(){
        setTitle("Varyans Hesaplayıcı");
        setSize(350,400);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        label = new JLabel("Sayı Dizisini Giriniz:");
        label.setFont(new Font("Arial",Font.BOLD,18));
        centerPanel.add(label , BorderLayout.NORTH);
        
        
        textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        
        
        JRadioButton radioPopulasyon = new JRadioButton("Popülasyon Varyansı", true);
        JRadioButton radioOrneklem = new JRadioButton("Örneklem Varyansı");
        
        Font customFont = new Font("Arial", Font.BOLD, 13);
        
        radioOrneklem.setFont(customFont);
        radioPopulasyon.setFont(customFont);
        
        ButtonGroup group = new ButtonGroup();
        group.add(radioPopulasyon);
        group.add(radioOrneklem);   
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        
        JPanel radioPanel = new JPanel();
        radioPanel.add(radioPopulasyon);
        radioPanel.add(radioOrneklem);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton hesaplaButton = new JButton("Varyans Hesapla");
        hesaplaButton.setPreferredSize(new Dimension(200,40));
        buttonPanel.add(hesaplaButton);
                
        southPanel.add(radioPanel);
        southPanel.add(buttonPanel);
        
        add(southPanel,BorderLayout.SOUTH);
        
        hesaplaButton.addActionListener((ActionEvent e) -> {
            String input = textArea.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen sayı dizisi giriniz.");
                return;
            }
            
            try {
                double[] numbers = parseInput(input);
                boolean isSample = radioOrneklem.isSelected();
                double variance = calculateVariance(numbers, isSample);
                
                String mesaj = (isSample ? "Örneklem" : "Popülasyon") +
                        " varyansı: " + String.format("%.4f", variance);
                JOptionPane.showMessageDialog(null, mesaj);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lütfen geçerli sayılar giriniz ");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
     }
     
     static double[] parseInput(String input) throws NumberFormatException {
        String[] parts = input.split("[,\\s]+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("En az iki sayı girilmelidir.");
        }

        double[] numbers = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Double.parseDouble(parts[i].trim());
        }
        return numbers;
    }
     
      static double calculateMean(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }
        static double calculateVariance(double[] numbers, boolean isSample) {
        double mean = calculateMean(numbers);
        double sumSquaredDiffs = 0;

        for (double num : numbers) {
            sumSquaredDiffs += Math.pow(num - mean, 2);
        }

        int divisor = isSample ? numbers.length - 1 : numbers.length;

        if (divisor == 0) {
            throw new IllegalArgumentException("Örneklem varyansı için en az iki sayı gerekli.");
        }

        return sumSquaredDiffs / divisor;
    }
}
