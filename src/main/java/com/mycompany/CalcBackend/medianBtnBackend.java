/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
public class medianBtnBackend extends JFrame{
   JTextArea textArea;
   JLabel label;
    
    public medianBtnBackend(){
        setTitle("Medyan Hesaplayıcı");
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
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton hesaplaButton = new JButton("Medyanı Hesapla");
       
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
            
            double median = calcMedian(numbersDoubleArray);
            JOptionPane.showMessageDialog(null, "Medyan: " + median);
        });
        
        add(hesaplaButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public String[] cleanTextArea(JTextArea textAreaFF){
        String inputText = textAreaFF.getText();
        String[] numbers = inputText.split("[,\\s]+");
        
        return numbers;
    }
    
    public double calcMedian(double[] numbers){
        int length = numbers.length;
        if(length % 2 == 1){
            return numbers[length/2];
        }else{
            return (numbers[(length/2)-1] + numbers[length/2]) /2.0;
        }
    }

}
