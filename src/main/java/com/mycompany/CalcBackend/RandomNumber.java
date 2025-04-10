package com.mycompany.CalcBackend;

import javax.swing.*;
import java.util.Random;

public class RandomNumber  extends JFrame{
    private String minInput;
    private  String maxInput;

    public RandomNumber(){
        
        setResizable(false);
        
        minInput = JOptionPane.showInputDialog(this, "Başlangıç değerini girin:","Başlangıç Değeri",JOptionPane.QUESTION_MESSAGE);
        
        if(minInput == null || minInput.trim().isEmpty()){
            return;
        }
        
        maxInput = JOptionPane.showInputDialog(this, "Bitiş değerini girin:","Bitiş Değeri",JOptionPane.QUESTION_MESSAGE);

        if(maxInput == null || maxInput.trim().isEmpty()){
            return;
        }
        
        generateNumber();
    }
    
    public void generateNumber(){
        try {
            int min = Integer.parseInt(minInput);
            int max = Integer.parseInt(maxInput);

            if (min > max) {
                JOptionPane.showMessageDialog(this, "Başlangıç değeri bitiş değerinden küçük olmalıdır!");
            } else {
                Random rand = new Random();
                int randomNum = rand.nextInt(max - min + 1) + min;
                
                JOptionPane.showMessageDialog(this, "Rastgele sayınız: " + randomNum);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı girin.","Sonuç",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new RandomNumber();
    }
}
