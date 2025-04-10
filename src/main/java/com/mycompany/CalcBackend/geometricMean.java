/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
public class geometricMean extends JFrame{  
    JTextArea textArea;
    JLabel label;
    ArrayList<Double> numberList = new ArrayList<>();
    double sum = 1;
    public geometricMean(){
        setTitle("Geometrik Ortalama Hesaplayıcı");
        setSize(360,400);
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
        
        JButton hesaplaButton = new JButton("Geometrik Ortalama Hesapla");
       
        hesaplaButton.addActionListener((ActionEvent e) -> {
            dispose();
            String[] numbers = cleanTextArea(textArea);
            
            
            double average = calcGeometric(numbers);
            JOptionPane.showMessageDialog(null, "Geometrik Ortalama: " + average);
        });
        
        add(hesaplaButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public String[] cleanTextArea(JTextArea textAreaFF){
        String inputText = textAreaFF.getText();
        String[] numbers = inputText.split("[,\\s]+");
        
        return numbers;
    }

    private double calcGeometric(String[] numbers) {
        double calcResult;
         for (String number : numbers){
            if(!number.isEmpty()){
                try{
                    double num = Double.parseDouble(number);
                    numberList.add(num);
                    sum  *= num; 
                }catch(NumberFormatException e){
                    break;
                }       
            }
        }
        calcResult =  Math.pow(sum,(1.0/numberList.size()));
        return calcResult;
    }
}
