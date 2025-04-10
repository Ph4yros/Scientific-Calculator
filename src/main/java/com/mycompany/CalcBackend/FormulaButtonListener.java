/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import com.mycompany.ui.panels.CalcNumbersPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author EREN
 */
public class FormulaButtonListener {

    private JFrame frame;
    private CalcButtonListener clcbl;
    private CalcNumbersPanel clcnp;
    
    public FormulaButtonListener(CalcButtonListener clcbl) {
        this.clcbl = clcbl;     
    }
    
    
    public void setVariable(BigDecimal newVariable) {
        clcbl.setVariable(newVariable);
    }





    public void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Faktöriyel Hesaplama" -> handleFactorialCalculation();
            case "Binom Hesaplama" -> handleBinomialCalculation();
            case "Üslü Sayı Hesaplama" -> handleExponentialNumber();
            case "İki Nokta Arasındaki Mesafeyi Hesaplama" -> handleDistanceBetweenTwoPoints();
            case "İki Noktanın Orta Noktasını Hesaplama" -> handleMidpointOfTwoPoints();
            case "Kosinüs Teoremi" -> handleCosinusTheorem();
            case "Permütasyon Hesaplama" -> handlePermutationCalculation();
            case "Kombinasyon Hesaplama" -> handleCombinationCalculation();
            case "Öklid Teoremi" -> handleEuclidTheorem();
            case "Üçgen G noktası bulma(kordinatlarla)" -> handleGPointOfTriangle();
            case "Pisagor Teoremi" -> handlePisagorTheorem();
            case "Sinüs Hesaplama" -> handleSinCalculation();
            case "Kosinüs Hesaplama" -> handleCosCalculation();
            case "Tanjant Hesaplama" -> handleTanCalculation();
            case "Kotanjant Hesaplama" -> handleCotCalculation();
            case "Sekant Hesaplama" -> handleSecCalculation();
            case "Kosekant Hesaplama" -> handleCscCalculation();
            case "Hiperbolik Sinüs Hesaplama" -> handleSinhCalculation();
            case "Hiperbolik Kosinüs Hesaplama" -> handleCoshCalculation();
            case "Hiperbolik Tanjant Hesaplama" -> handleTanhCalculation();
            case "Hiperbolik Kotanjant Hesaplama" -> handleCothCalculation();
            case "Hiperbolik Sekant Hesaplama" -> handleSechCalculation();
            case "Hiperbolik Kosekant Hesaplama" -> handleCschCalculation();
            case "Çokgen İç Açı Hesabı" -> handlePolygonAngleCalc();
            case "Rasyonel-Ondalık Çevirici" -> handleTransitionCalc();
            default -> {
            }
        }

    }

    public void handleFactorialCalculation() {
        String numberInput = JOptionPane.showInputDialog(frame, "Faktöriyelini hesaplamak istediğiniz sayıyı girin:");
        
        if (numberInput == null) {
            return; 
        }
        try {
            int number = Integer.parseInt(numberInput);
            if (number < 0) {
                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                BigInteger result = factorial(number);
                setVariable(new BigDecimal(result));
                JOptionPane.showMessageDialog(frame, number + "! = " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
    public void handleBinomialCalculation() {
        String nInput = JOptionPane.showInputDialog(frame, "n (Toplam eleman sayısı) değerini girin:");
        
        if (nInput == null || nInput.trim().isEmpty()) {
        return;
        }
        String kInput = JOptionPane.showInputDialog(frame, "k (Seçilen eleman sayısı) değerini girin:");
        if (kInput == null || kInput.trim().isEmpty()) {
        return;
    }

        try {
            int n = Integer.parseInt(nInput);
            int k = Integer.parseInt(kInput);

            if (n < 0 || k < 0 || k > n) {
                JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen n >= k ve her ikisini de pozitif girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                BigInteger binomialResult = binomial(n, k);
                setVariable(new BigDecimal(binomialResult));
                JOptionPane.showMessageDialog(frame, "C(" + n + ", " + k + ") = " + binomialResult, "Binom Sonucu", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BigInteger binomial(int n, int k) {
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        return numerator.divide(denominator);
    }
    public void handleExponentialNumber() {
    	String base = JOptionPane.showInputDialog(frame,"taban değerini girin: ");
        if(base==null || base.trim().isEmpty()){
            return;
        }
    	String exponent = JOptionPane.showInputDialog(frame,"üs değerini girin: ");
    	 if(exponent==null || exponent.trim().isEmpty()){
            return;
        }
        
    	try {
    		int baseInt = Integer.parseInt(base);
    		int exponentInt = Integer.parseInt(exponent);
    		
    		BigDecimal result = exponential(baseInt,exponentInt);
    		setVariable(result);
    		JOptionPane.showMessageDialog(frame,"Sonuç: " + result.toString(),"Hesaplama Sonucu",JOptionPane.INFORMATION_MESSAGE);
    		
    	}
    	catch(NumberFormatException ex) {
    		JOptionPane.showMessageDialog(frame,"Geçersiz giriş. Lütfen bir tam sayı girin.","Hata",JOptionPane.ERROR_MESSAGE);
    	}
    }
    private BigDecimal exponential(int base, int exponent) {
    	BigDecimal baseBig = BigDecimal.valueOf(base);
    	return baseBig.pow(exponent);
    }
    public void handleDistanceBetweenTwoPoints() {
    	 String firstPointMessage = "Birinci Nokta için X ve Y değerlerini girin";
         int[] firstPoint = getCoordinatesFromUser(firstPointMessage);
         if(firstPoint==null){
             return;
         }

         String secondPointMessage = "İkinci Nokta için X ve Y değerlerini girin";
         int[] secondPoint = getCoordinatesFromUser(secondPointMessage);
         if(firstPoint==null){
             return;
         }

         if (firstPoint != null && secondPoint != null) {
             double distance = calculateDistance(firstPoint[0], firstPoint[1], secondPoint[0], secondPoint[1]);

             JOptionPane.showMessageDialog(frame,
                     "Birinci Nokta X: " + firstPoint[0] + ", Y: " + firstPoint[1] + "\n" +
                     "İkinci Nokta X: " + secondPoint[0] + ", Y: " + secondPoint[1] + "\n" +
                     "Mesafe: " + distance,
                     "Nokta Bilgileri",
                     JOptionPane.INFORMATION_MESSAGE);
         }

    }
    private int[] getCoordinatesFromUser(String message) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));  

        JTextField xField = new JTextField(10);
        JTextField yField = new JTextField(10);

        JLabel xLabel = new JLabel("X Koordinatı:");
        JLabel yLabel = new JLabel("Y Koordinatı:");

        panel.add(xLabel);
        panel.add(xField);
        panel.add(yLabel);
        panel.add(yField);

        int option = JOptionPane.showConfirmDialog(frame, panel, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int x = Integer.parseInt(xField.getText().trim());
                int y = Integer.parseInt(yField.getText().trim());

                return new int[] {x, y};  
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Geçersiz giriş! Lütfen geçerli bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;
    }
    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public void handleMidpointOfTwoPoints() {
    	 String firstPointMessage = "Birinci Nokta için X ve Y değerlerini girin";
    	 int[] firstPoint = getCoordinatesFromUser(firstPointMessage);
         if(firstPoint == null){
             return;
         }
    	 
    	 String secondPointMessage = "İkinci Nokta için X ve Y değerlerini girin";
    	 int[] secondPoint = getCoordinatesFromUser(secondPointMessage);
    	 
    	 if (firstPoint != null && secondPoint != null) {
    	        double midX = (firstPoint[0] + secondPoint[0]) / 2.0;
    	        double midY = (firstPoint[1] + secondPoint[1]) / 2.0;

    	        JOptionPane.showMessageDialog(frame,
    	                "Birinci Nokta X: " + firstPoint[0] + ", Y: " + firstPoint[1] + "\n" +
    	                "İkinci Nokta X: " + secondPoint[0] + ", Y: " + secondPoint[1] + "\n" +
    	                "Orta Nokta X: " + midX + ", Y: " + midY,
    	                "Orta Nokta Bilgisi",
    	                JOptionPane.INFORMATION_MESSAGE);
    	    }

    }
    public void handleCosinusTheorem() {
        JRadioButton edgeFor3 = new JRadioButton("3 Kenarı Bilinen");
        JRadioButton edgeAngleFor2 = new JRadioButton("2 Kenarı ve Arasındaki Açı Bilinen");
        
        ButtonGroup group = new ButtonGroup();
        group.add(edgeFor3);
        group.add(edgeAngleFor2);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(edgeFor3);
        panel.add(edgeAngleFor2);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Bir Seçim Yapın", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (edgeFor3.isSelected()) {
                handleThreeEdges();
            } else if (edgeAngleFor2.isSelected()) {
                handleTwoEdgesAndAngle();
            }
        }
    }

    private void handleThreeEdges() {
        String aInput = JOptionPane.showInputDialog(frame, "a kenarı (A köşesinin karşısı): ");
        if(aInput == null || aInput.trim().isEmpty()){
            return;
        }
        String bInput = JOptionPane.showInputDialog(frame, "b kenarı (B köşesinin karşısı): ");
        if(bInput == null || bInput.trim().isEmpty()){
            return;
        }
        String cInput = JOptionPane.showInputDialog(frame, "c kenarı (C köşesinin karşısı): ");
        if(cInput == null || cInput.trim().isEmpty()){
            return;
        }

        try {
            int aInt = Integer.parseInt(aInput);
            int bInt = Integer.parseInt(bInput);
            int cInt = Integer.parseInt(cInput);

            double cosA = ((Math.pow(aInt, 2) - Math.pow(bInt, 2) - Math.pow(cInt, 2)) / (-2.0 * bInt * cInt));
            double cosB = ((Math.pow(bInt, 2) - Math.pow(aInt, 2) - Math.pow(cInt, 2)) / (-2.0 * aInt * cInt));
            double cosC = ((Math.pow(cInt, 2) - Math.pow(aInt, 2) - Math.pow(bInt, 2)) / (-2.0 * aInt * bInt));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JRadioButton radioCosA = new JRadioButton("CosA: " + cosA);
            JRadioButton radioCosB = new JRadioButton("CosB: " + cosB);
            JRadioButton radioCosC = new JRadioButton("CosC: " + cosC);

            ButtonGroup group = new ButtonGroup();
            group.add(radioCosA);
            group.add(radioCosB);
            group.add(radioCosC);

            panel.add(radioCosA);
            panel.add(radioCosB);
            panel.add(radioCosC);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Sonuçları Seçin", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                if (radioCosA.isSelected()) {
                    setVariable(new BigDecimal(cosA));
                } else if (radioCosB.isSelected()) {
                    setVariable(new BigDecimal(cosB));
                } else if (radioCosC.isSelected()) {
                    setVariable(new BigDecimal(cosC));
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleTwoEdgesAndAngle() {
        String aInput = JOptionPane.showInputDialog(frame, "a kenarı: ");
        if(aInput == null || aInput.trim().isEmpty()){
            return;
        }
        String bInput = JOptionPane.showInputDialog(frame, "b kenarı: ");
        if(bInput == null || bInput.trim().isEmpty()){
            return;
        }
        String angleInput = JOptionPane.showInputDialog(frame, "Aradaki açı (derece): ");
        if(angleInput == null || angleInput.trim().isEmpty()){
            return;
        }

        try {
            int aInt = Integer.parseInt(aInput);
            int bInt = Integer.parseInt(bInput);
            double angle = Math.toRadians(Double.parseDouble(angleInput));

            double cSquared = Math.pow(aInt, 2) + Math.pow(bInt, 2) - 2 * aInt * bInt * Math.cos(angle);
            double c = Math.sqrt(cSquared);

            JOptionPane.showMessageDialog(frame, "Hesaplanan 3. Kenar (c): " + c, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    	 
    	
    public void handlePermutationCalculation() {
    	String nInput = JOptionPane.showInputDialog(frame, "n (Toplam eleman sayısı) değerini girin:");
        if(nInput == null || nInput.trim().isEmpty()){
            return;
        }
    	String rInput = JOptionPane.showInputDialog(frame, "r (Seçilen eleman sayısı) değerini girin:");
    	if(rInput == null || rInput.trim().isEmpty()){
            return;
        }
    	try {
            int n = Integer.parseInt(nInput);
            int r = Integer.parseInt(rInput);

            if (n < 0 || r < 0 || r > n) {
                JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen n >= r ve her ikisini de pozitif girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                BigInteger permutationalResult = permutation(n, r);
                setVariable(new BigDecimal(permutationalResult));
                JOptionPane.showMessageDialog(frame, "P(" + n + ", " + r + ") = " + permutationalResult, "Permütasyon Sonucu", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    private BigInteger permutation(int n,int r) {
    	BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(n-r);
        return numerator.divide(denominator);
    }
    
    public void handleCombinationCalculation() {
    	String nInput = JOptionPane.showInputDialog(frame, "n (Toplam eleman sayısı) değerini girin:");
        if(nInput == null || nInput.trim().isEmpty()){
            return;
        }
    	String rInput = JOptionPane.showInputDialog(frame, "r (Seçilen eleman sayısı) değerini girin:");
        if(rInput == null || nInput.trim().isEmpty()){
            return;
        }
    	
    	try {
            int n = Integer.parseInt(nInput);
            int r = Integer.parseInt(rInput);

            if (n < 0 || r < 0 || r > n) {
                JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen n >= r ve her ikisini de pozitif girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                BigInteger combinationalResult = combination(n, r);
                setVariable(new BigDecimal(combinationalResult));
                JOptionPane.showMessageDialog(frame, "P(" + n + ", " + r + ") = " + combinationalResult, "Kombinasyon Sonucu", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    private BigInteger combination(int n, int r) {
    	BigInteger numerator = factorial(n);
    	BigInteger denominator = factorial(r).multiply(factorial(n-r));
    	return numerator.divide(denominator);
    	
    }
    public void handleEuclidTheorem() {
    	String imgPath = "C:\\Users\\EREN\\eclipse-workspace\\MathTubitakYedek\\img\\oklid.jpeg";

    	ImageIcon imageIcon = new ImageIcon(imgPath);
    	    
    	int newWidth = 500;
    	int newHeight = 150;
    	    
    	Image originalImage = imageIcon.getImage();
    	    
    	Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    	    
    	ImageIcon resizedIcon = new ImageIcon(resizedImage);
    	   
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	    
    	JLabel imageLabel = new JLabel(resizedIcon);
    	panel.add(imageLabel);
        
        JRadioButton forHeight = new JRadioButton("h² = p . k");
        JRadioButton forBEdge = new JRadioButton("b² = k . a");
        JRadioButton forCEdge = new JRadioButton("c² = p . a");
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(forHeight);
        group.add(forBEdge);
        group.add(forCEdge);
        
        panel.add(forHeight);
        panel.add(forBEdge);
        panel.add(forCEdge);
        
        
        int result = JOptionPane.showConfirmDialog(frame, panel, "Bir Seçim Yapın", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (forHeight.isSelected()) {
                handleEuclidHeight();
            } else if (forBEdge.isSelected()) {
                handleEuclidBEdge();
            }else if(forCEdge.isSelected()) {
            	handleCEdge();
            }
        }

    }
    public void handleEuclidHeight() {
    	JPanel panel = new JPanel();
    	
    	JCheckBox height = new JCheckBox("h");
    	JCheckBox p = new JCheckBox("p");
    	JCheckBox k = new JCheckBox("k");
    	
    	
    	panel.add(height);
    	panel.add(p);
    	panel.add(k);
    	
    	int result = JOptionPane.showConfirmDialog(frame, panel, "Bilinen Değerler", JOptionPane.OK_CANCEL_OPTION);
    	
    	if (result == JOptionPane.OK_OPTION){
    		if(height.isSelected() && p.isSelected() && !(k.isSelected())) {
    			String hInput = JOptionPane.showInputDialog(frame, "h değeri:");
                        if(hInput == null || hInput.trim().isEmpty()){
                            return;
                        }
    			String pInput = JOptionPane.showInputDialog(frame, "p değeri:");
    			if(pInput == null || pInput.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int hInputInt = Integer.parseInt(hInput);
    	            int pInputInt = Integer.parseInt(pInput);
    	            
    	            if (hInputInt < 0 || pInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(hInputInt, 2)/pInputInt;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "k =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else if(height.isSelected() && k.isSelected() && !(p.isSelected())) {
    			String hInput2 = JOptionPane.showInputDialog(frame, "h değeri:");
                        if(hInput2 == null || hInput2.trim().isEmpty()){
                            return;
                        }
    			String kInput2 = JOptionPane.showInputDialog(frame, "k değeri:");
                        if(kInput2 == null || kInput2.trim().isEmpty()){
                            return;
                        }
    			
    			try {
    	            int hInputInt2 = Integer.parseInt(hInput2);
    	            int kInputInt2 = Integer.parseInt(kInput2);
    	            
    	            if (hInputInt2 < 0 || kInputInt2 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(hInputInt2, 2)/kInputInt2;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "p =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    			
    		}else if(p.isSelected() && k.isSelected() && !(height.isSelected())) {
    			String pInput3 = JOptionPane.showInputDialog(frame, "p değeri:");
                        if(pInput3 == null || pInput3.trim().isEmpty()){
                            return;
                        }
    			String kInput3 = JOptionPane.showInputDialog(frame, "k değeri:");
    			if(kInput3 == null || kInput3.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int pInputInt3 = Integer.parseInt(pInput3);
    	            int kInputInt3 = Integer.parseInt(kInput3);
    	            
    	            if (pInputInt3 < 0 || kInputInt3 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = pInputInt3 * kInputInt3;
    	               setVariable(new BigDecimal(Math.sqrt(calcResult)));
    	               
    	               JOptionPane.showMessageDialog(frame, "h =" + Math.sqrt(calcResult), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else {
    			 JOptionPane.showMessageDialog(frame, "Düzgün Bir Seçim Yapınız.", "Hata", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    public void handleEuclidBEdge() {
    	JPanel panel = new JPanel();
    	
    	JCheckBox b = new JCheckBox("b");
    	JCheckBox k = new JCheckBox("k");
    	JCheckBox a = new JCheckBox("a");
    	
    	
    	panel.add(b);
    	panel.add(a);
    	panel.add(k);
    	
    	int result = JOptionPane.showConfirmDialog(frame, panel, "Bilinen Değerler", JOptionPane.OK_CANCEL_OPTION);
    	if (result == JOptionPane.OK_OPTION){
    		if(b.isSelected() && k.isSelected() && !(a.isSelected())) {
    			String bInput = JOptionPane.showInputDialog(frame, "b değeri:");
                        if(bInput == null || bInput.trim().isEmpty()){
                            return;
                        }
    			String kInput = JOptionPane.showInputDialog(frame, "k değeri:");
                        if(kInput == null || kInput.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int bInputInt = Integer.parseInt(bInput);
    	            int kInputInt = Integer.parseInt(kInput);
    	            
    	            if (bInputInt < 0 || kInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(bInputInt, 2)/kInputInt;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "a =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else if(b.isSelected() && a.isSelected() && !(k.isSelected())) {
    			String bInput2 = JOptionPane.showInputDialog(frame, "b değeri:");
                        if(bInput2 == null || bInput2.trim().isEmpty()){
                            return;
                        }
    			String aInput2 = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput2 == null || aInput2.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int bInputInt2 = Integer.parseInt(bInput2);
    	            int aInputInt2 = Integer.parseInt(aInput2);
    	            
    	            if (bInputInt2 < 0 || aInputInt2 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(bInputInt2, 2)/aInputInt2;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "k =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    			
    		}else if(a.isSelected() && k.isSelected() && !(b.isSelected())) {
    			String aInput3 = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput3 == null || aInput3.trim().isEmpty()){
                            return;
                        } 
    			String kInput3 = JOptionPane.showInputDialog(frame, "k değeri:");
                        if(kInput3 == null || kInput3.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int aInputInt3 = Integer.parseInt(aInput3);
    	            int kInputInt3 = Integer.parseInt(kInput3);
    	            
    	            if (aInputInt3 < 0 || kInputInt3 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = aInputInt3 * kInputInt3;
    	               setVariable(new BigDecimal(Math.sqrt(calcResult)));
    	               
    	               JOptionPane.showMessageDialog(frame, "b =" + Math.sqrt(calcResult), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else {
    			 JOptionPane.showMessageDialog(frame, "Düzgün Bir Seçim Yapınız.", "Hata", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    public void handleCEdge() {
    	JPanel panel = new JPanel();
    	
    	JCheckBox c = new JCheckBox("c");
    	JCheckBox p = new JCheckBox("p");
    	JCheckBox a = new JCheckBox("a");
    	
    	
    	panel.add(c);
    	panel.add(p);
    	panel.add(a);
    	int result = JOptionPane.showConfirmDialog(frame, panel, "Bilinen Değerler", JOptionPane.OK_CANCEL_OPTION);
    	if (result == JOptionPane.OK_OPTION){
    		if(c.isSelected() && p.isSelected() && !(a.isSelected())) {
    			String cInput = JOptionPane.showInputDialog(frame, "c değeri:");
                        if(cInput == null || cInput.trim().isEmpty()){
                            return;
                        }
    			String pInput = JOptionPane.showInputDialog(frame, "p değeri:");
                        if(pInput == null || pInput.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int cInputInt = Integer.parseInt(cInput);
    	            int pInputInt = Integer.parseInt(pInput);
    	            
    	            if (cInputInt < 0 || pInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(cInputInt, 2)/pInputInt;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "a =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else if(c.isSelected() && a.isSelected() && !(p.isSelected())) {
    			String cInput2 = JOptionPane.showInputDialog(frame, "c değeri:");
                        if(cInput2 == null || cInput2.trim().isEmpty()){
                            return;
                        }
    			String aInput2 = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput2 == null || aInput2.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int cInputInt2 = Integer.parseInt(cInput2);
    	            int aInputInt2 = Integer.parseInt(aInput2);
    	            
    	            if (cInputInt2 < 0 || aInputInt2 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.pow(cInputInt2, 2)/aInputInt2;
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "p =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    			
    		}else if(a.isSelected() && p.isSelected() && !(c.isSelected())) {
    			String aInput3 = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput3 == null || aInput3.trim().isEmpty()){
                            return;
                        }
    			String pInput3 = JOptionPane.showInputDialog(frame, "p değeri:");
                        if(pInput3 == null || pInput3.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int aInputInt3 = Integer.parseInt(aInput3);
    	            int pInputInt3 = Integer.parseInt(pInput3);
    	            
    	            if (aInputInt3 < 0 || pInputInt3 < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = aInputInt3 * pInputInt3;
    	               setVariable(new BigDecimal(Math.sqrt(calcResult)));
    	               
    	               JOptionPane.showMessageDialog(frame, "c =" + Math.sqrt(calcResult), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else {
    			 JOptionPane.showMessageDialog(frame, "Düzgün Bir Seçim Yapınız.", "Hata", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    public void handleGPointOfTriangle() {
    	String firstPointMessage = "Birinci köşe için X ve Y değerlerini girin";
        int[] firstPoint = getCoordinatesFromUser(firstPointMessage);
        if(firstPoint ==null){
            return;
        }
        String secondPointMessage = "İkinci köşe için X ve Y değerlerini girin";
        int[] secondPoint = getCoordinatesFromUser(secondPointMessage);
        if(secondPoint ==null){
            return;
        }
        String thirdPointMessage = "Üçüncü köşe için X ve Y değerlerini girin";
        int[] thirdPoint = getCoordinatesFromUser(thirdPointMessage);
        if(thirdPoint ==null){
            return;
        }
        

        if (firstPoint != null && secondPoint != null && thirdPoint != null) {
            double[] GPoint = calculateGPoint(firstPoint[0], firstPoint[1], secondPoint[0], secondPoint[1],thirdPoint[0],thirdPoint[1]);

            JOptionPane.showMessageDialog(frame,
                    "Birinci Nokta X: " + firstPoint[0] + ", Y: " + firstPoint[1] + "\n" +
                    "İkinci Nokta X: " + secondPoint[0] + ", Y: " + secondPoint[1] + "\n" +
                    "Üçüncü Nokta X: " + thirdPoint[0] + ", Y: " + thirdPoint[1] +"\n" +
                    "G Noktası Kordinatları: " + "X: "+ GPoint[0]+" " + "Y: "+ GPoint[1],
                    "Nokta Bilgileri",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private double[] calculateGPoint(int x1,int y1,int x2,int y2,int x3, int y3) {
    	double x = (x1+x2+x3) / 3;
    	double y = (y1 + y2 + y3) / 3;
    	double[] result = {x,y};
    	
    	return result;

    }
    public void handlePisagorTheorem() {
    	JPanel panel = new JPanel();
    	
    	JCheckBox a = new JCheckBox("a");
    	JCheckBox b = new JCheckBox("b");
    	JCheckBox c = new JCheckBox("c(hipotenüs)");
    	
    	panel.add(a);
    	panel.add(b);
    	panel.add(c);
    	
    	int result = JOptionPane.showConfirmDialog(frame, panel, "Bilinen Değerler", JOptionPane.OK_CANCEL_OPTION);
    	
    	if (result == JOptionPane.OK_OPTION) {
    		if(a.isSelected() && b.isSelected() && !(c.isSelected())) {
    			String aInput = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput == null||aInput.trim().isEmpty()){
                            return;
                        }
    			String bInput = JOptionPane.showInputDialog(frame, "b değeri:");
                        if(bInput == null||bInput.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int aInputInt = Integer.parseInt(aInput);
    	            int bInputInt = Integer.parseInt(bInput);
    	            
    	            if (aInputInt < 0 || bInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.sqrt(Math.pow(aInputInt, 2) + Math.pow(bInputInt, 2));
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "c =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else if(a.isSelected() && c.isSelected() && !(b.isSelected())) {
    			String aInput = JOptionPane.showInputDialog(frame, "a değeri:");
                        if(aInput == null||aInput.trim().isEmpty()){
                            return;
                        }
    			String cInput = JOptionPane.showInputDialog(frame, "c değeri:");
                        if(cInput == null||cInput.trim().isEmpty()){
                            return;
                        }
    			
    			try {
    	            int aInputInt = Integer.parseInt(aInput);
    	            int cInputInt = Integer.parseInt(cInput);
    	            
    	            if (aInputInt < 0 || cInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.sqrt(Math.pow(cInputInt, 2) - Math.pow(aInputInt, 2));
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "b =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else if(b.isSelected() && c.isSelected() && !(a.isSelected())) {
    			String bInput = JOptionPane.showInputDialog(frame, "b değeri:");
                        if(bInput == null||bInput.trim().isEmpty()){
                            return;
                        }
    			String cInput = JOptionPane.showInputDialog(frame, "c değeri:");
                        if(cInput == null||cInput.trim().isEmpty()){
                            return;
                        }
    			try {
    	            int bInputInt = Integer.parseInt(bInput);
    	            int cInputInt = Integer.parseInt(cInput);
    	            
    	            if (bInputInt < 0 || cInputInt < 0) {
    	                JOptionPane.showMessageDialog(frame, "Lütfen pozitif bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	               double calcResult = Math.sqrt(Math.pow(cInputInt, 2) - Math.pow(bInputInt, 2));
    	               setVariable(new BigDecimal(calcResult));
    	               
    	               JOptionPane.showMessageDialog(frame, "a =" + calcResult, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
    	            }
    	        } catch (NumberFormatException ex) {
    	            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir tam sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
    	        }
    		}else {
   			 JOptionPane.showMessageDialog(frame, "Düzgün Bir Seçim Yapınız.", "Hata", JOptionPane.ERROR_MESSAGE);
   		}
    	}
    }
    
    public void handleSinCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Sinüsünü hesaplamak istediğiniz açıyı (derece) girin:");
        
         if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double sin = Math.sin(angleRadians);
            setVariable(new BigDecimal(sin));
            JOptionPane.showMessageDialog(frame, "Sin(" + angleDegrees + "°) = " + sin, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCosCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Kosinüsünü hesaplamak istediğiniz açıyı (derece) girin:");
        
        if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double cos = Math.cos(angleRadians);
            setVariable(new BigDecimal(cos));
            JOptionPane.showMessageDialog(frame, "Cos(" + angleDegrees + "°) = " + cos, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleTanCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Tanjantını hesaplamak istediğiniz açıyı (derece) girin:");
         if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double tan = Math.tan(angleRadians);
            setVariable(new BigDecimal(tan));
            JOptionPane.showMessageDialog(frame, "Tan(" + angleDegrees + "°) = " + tan, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCotCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Kotanjantını hesaplamak istediğiniz açıyı (derece) girin:");
         if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double tan = Math.tan(angleRadians);
            double cot = (tan != 0) ? 1 / tan : Double.NaN;
            setVariable(new BigDecimal(cot));
            JOptionPane.showMessageDialog(frame, "Cot(" + angleDegrees + "°) = " + (Double.isNaN(cot) ? "Tanımsız" : cot), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleSecCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Sekantını hesaplamak istediğiniz açıyı (derece) girin:");
         if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double cos = Math.cos(angleRadians);
            double sec = (cos != 0) ? 1 / cos : Double.NaN;
            setVariable(new BigDecimal(sec));
            JOptionPane.showMessageDialog(frame, "Sec(" + angleDegrees + "°) = " + (Double.isNaN(sec) ? "Tanımsız" : sec), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCscCalculation() {
        String angleInput = JOptionPane.showInputDialog(frame, "Kosekantını hesaplamak istediğiniz açıyı (derece) girin:");
         if (angleInput == null || angleInput.trim().isEmpty()) {
            return;
         }

        try {
            double angleDegrees = Double.parseDouble(angleInput);
            double angleRadians = Math.toRadians(angleDegrees);
            double sin = Math.sin(angleRadians);
            double csc = (sin != 0) ? 1 / sin : Double.NaN;
            setVariable(new BigDecimal(csc));
            JOptionPane.showMessageDialog(frame, "Csc(" + angleDegrees + "°) = " + (Double.isNaN(csc) ? "Tanımsız" : csc), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleSinhCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik sinüsünü hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            double result = Math.sinh(value);
            setVariable(new BigDecimal(result));
            JOptionPane.showMessageDialog(frame, "Hiperbolik sinüs: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCoshCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik kosinüsünü hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            double result = Math.cosh(value);
            setVariable(new BigDecimal(result));
            JOptionPane.showMessageDialog(frame, "Hiperbolik kosinüs: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleTanhCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik tanjantını hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            double result = Math.tanh(value);
            setVariable(new BigDecimal(result));
            JOptionPane.showMessageDialog(frame, "Hiperbolik tanjant: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCothCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik kotanjantını hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            if (Math.sinh(value) != 0) {
                double result = 1 / Math.sinh(value);
                setVariable(new BigDecimal(result));
                JOptionPane.showMessageDialog(frame, "Hiperbolik kotanjant: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Hiperbolik kotanjant tanımsız.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleSechCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik sekantını hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            double result = 1 / Math.cosh(value);
            setVariable(new BigDecimal(result));
            JOptionPane.showMessageDialog(frame, "Hiperbolik sekant: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCschCalculation() {
        String input = JOptionPane.showInputDialog(frame, "Hiperbolik kosekantını hesaplamak istediğiniz sayıyı girin:");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

        try {
            double value = Double.parseDouble(input);
            if (Math.sinh(value) != 0) {
                double result = 1 / Math.sinh(value);
                setVariable(new BigDecimal(result));
                JOptionPane.showMessageDialog(frame, "Hiperbolik kosekant: " + result, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Hiperbolik kosekant tanımsız.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void handlePolygonAngleCalc() {
    	String input = JOptionPane.showInputDialog(frame, "n(Kenar Sayısını Giriniz: )");
         if (input == null || input.trim().isEmpty()) {
            return;
         }

    	
    	 try {
             int value = Integer.parseInt(input);
             int result = (value - 2) * 180;
             setVariable(new BigDecimal(result));
             
             JOptionPane.showMessageDialog(frame, "İç Açılar Toplamı: " + result + "°", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
         } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(frame, "Geçersiz giriş. Lütfen bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
         }
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void handleTransitionCalc(){
        String input = JOptionPane.showInputDialog(frame, "Sayıyı Giriniz(/ veya . kullanarak):");
        if (input == null || input.trim().isEmpty()) {
        return;
    }
    
    try {
        if (input.contains("/")) { 
            String[] parts = input.split("/");
            if (parts.length != 2) throw new NumberFormatException();
            
            BigDecimal numerator = new BigDecimal(parts[0].trim());
            BigDecimal denominator = new BigDecimal(parts[1].trim());
            
            if (denominator.equals(BigDecimal.ZERO)) {
                JOptionPane.showMessageDialog(frame, "Payda 0 olamaz!");
                return;
            }
            
            BigDecimal result = numerator.divide(denominator, 10, BigDecimal.ROUND_HALF_UP);
            JOptionPane.showMessageDialog(frame, "Ondalık Karşılığı: " + result.toPlainString());
        } else { 
            BigDecimal decimal = new BigDecimal(input.trim());
            BigInteger numerator = decimal.unscaledValue();
            BigInteger denominator = BigInteger.TEN.pow(decimal.scale());
            
            BigInteger gcd = numerator.gcd(denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
            
            String fraction = numerator + "/" + denominator;
            JPanel panel = new JPanel();
            panel.add(new JLabel("Kesir Karşılığı: " + fraction));
            JButton writeButton = new JButton("Hesap Makinesine Yazdır");
           final String resultText = numerator + "÷" + denominator;
            writeButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    CalcNumbersPanel clcnp = clcbl.getClcnp();
                    clcnp.addDisplayText(resultText);
                }
            });
            panel.add(writeButton);
            
            
            JOptionPane.showMessageDialog(frame, panel, "Hesaplama Sonucu", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(frame, "Geçerli bir sayı giriniz!");
    }
        
    }
}
