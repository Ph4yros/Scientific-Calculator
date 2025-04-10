/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.auxilaryforms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author EREN
 */
public class UnitConverter extends javax.swing.JFrame {

    /**
     * Creates new form UnitConverter
     */
    
    private final JComboBox<String> categoryComboBox;
    private final JComboBox<String> unitFromComboBox;
    private final JComboBox<String> unitToComboBox;
    private  JTextField inputField;
    private  JTextField resultField;
    private final JButton convertButton;
    private final JPanel mainPanel;
    private int xOffset = 0;
    private int yOffset = 0;

    public UnitConverter() {
        setUndecorated(true);
        initComponents();
        titleLabel.setText("<html><font color='white'>Birim Dönüştürücü</font></html>");
        
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),20,20));
        
         addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });

        
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - xOffset;
                int y = e.getYOnScreen() - yOffset;
                setLocation(x, y);
            }
        });
        
        setTitle("Birim Dönüştürücü");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // Kategoriler
        String[] categories = {"Uzunluk", "Ağırlık", "Sıcaklık", "Hacim", "Zaman","Alan","Hız Dönüşümleri","Basınç Dönüşümleri","Enerji Dönüşümleri","Veri Depolama Dönüşümleri","Açı Dönüşümleri","Dijital Ölçüler"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.addActionListener((ActionEvent e) -> {
            inputField.setText(""); 
            resultField.setText("");
            updateUnits();
        });

        unitFromComboBox = new JComboBox<>();
        unitToComboBox = new JComboBox<>();
        inputField = new JTextField();
        resultField = new JTextField(20);
        resultField.setEditable(false);

        convertButton = new JButton("Dönüştür");
        convertButton.addActionListener((ActionEvent e) -> {
            convertUnits();
        });
        
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(50,0,0,0));
        mainPanel.setLayout(new GridLayout(6, 2));
        mainPanel.setBackground(new Color(45,45,45));
        
        
        // Adding components to frame
        JLabel categoryLabel = new JLabel("Kategori:");
        categoryLabel.setFont(new Font("Arial",Font.BOLD,16));
        categoryLabel.setForeground(Color.WHITE);
        mainPanel.add(categoryLabel);
        mainPanel.add(categoryComboBox);
        JLabel select1Label = new JLabel("Birimi Seçin (Başlangıç):");
        select1Label.setFont(new Font("Arial",Font.BOLD,16));
        select1Label.setForeground(Color.WHITE);
        mainPanel.add(select1Label);
        JLabel select2Label = new JLabel("Birimi Seçin (Hedef):");
        select2Label.setFont(new Font("Arial",Font.BOLD,16));
        select2Label.setForeground(Color.WHITE);
        mainPanel.add(unitFromComboBox);
        mainPanel.add(select2Label);
        mainPanel.add(unitToComboBox);
        JLabel valueLabel = new JLabel("Değer:");
        valueLabel.setFont(new Font("Arial",Font.BOLD,16));
        valueLabel.setForeground(Color.WHITE);
        mainPanel.add(valueLabel);
        mainPanel.add(inputField);
        JLabel resultLabel = new JLabel("Sonuç:");
        resultLabel.setFont(new Font("Arial",Font.BOLD,16));
        resultLabel.setForeground(Color.WHITE);
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(new Color(45,45,45)); 
        resultPanel.add(resultLabel,BorderLayout.WEST);
        resultPanel.add(resultField,BorderLayout.EAST);
        mainPanel.add(resultPanel);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(convertButton);
        
        add(mainPanel,BorderLayout.CENTER);
        
        
        // Initialize the units
        updateUnits();
    }

     private void updateUnits() {
        String category = (String) categoryComboBox.getSelectedItem();
        String[] units = new String[0];

        switch (category) {
            case "Uzunluk" -> units = new String[]{"Metre", "Kilometre", "Mil", "Inch", "Centimetre"};
            case "Ağırlık" -> units = new String[]{"Kilogram", "Gram", "Ton", "Pound", "Ounce"};
            case "Sıcaklık" -> units = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
            case "Hacim" -> units = new String[]{"Litre", "Mililitre", "Galon", "Pint"};
            case "Zaman" -> units = new String[]{"Saniye", "Dakika", "Saat", "Gün", "Hafta", "Yıl"};
            case "Alan" -> units = new String[]{"Metrekare","Kilometrekare","Hektar","Dönüm","Acre","Square Foot"};
            case "Hız Dönüşümleri" -> units = new String[]{"m/s","km/h","mph","Knot"};
            case "Basınç Dönüşümleri" -> units = new String[]{"Pascal","Bar","Atm","mmHg"};
            case "Enerji Dönüşümleri" -> units = new String[]{"Joule","Kalori","kWh","eV","BTU"};
            case "Veri Depolama Dönüşümleri" -> units = new String[]{"Bit","Byte","Kilobyte","Megabyte","Gigabyte","Terabyte",};
            case "Açı Dönüşümleri" -> units = new String[]{"Derece","Radyan","Milyem","Grad"};
            case "Dijital Ölçüler" -> units = new String[] {"Piksel","Em","Santimetre"};
        }

        unitFromComboBox.setModel(new DefaultComboBoxModel<>(units));
        unitToComboBox.setModel(new DefaultComboBoxModel<>(units));
    }

    // Dönüştürme işlemi
    private void convertUnits() {
        try {
            String fromUnit = (String) unitFromComboBox.getSelectedItem();
            String toUnit = (String) unitToComboBox.getSelectedItem();
            double inputValue = Double.parseDouble(inputField.getText());

            double result = 0;

            if (fromUnit.equals("Metre") && toUnit.equals("Kilometre")) {
            result = inputValue / 1000;
            } else if (fromUnit.equals("Metre") && toUnit.equals("Mil")) {
                result = inputValue / 1609.344;
            } else if (fromUnit.equals("Metre") && toUnit.equals("Inch")) {
                result = inputValue * 39.3701;
            } else if (fromUnit.equals("Metre") && toUnit.equals("Centimetre")) {
                result = inputValue * 100;
            } 
            else if (fromUnit.equals("Kilometre") && toUnit.equals("Metre")) {
                result = inputValue * 1000;
            } else if (fromUnit.equals("Kilometre") && toUnit.equals("Mil")) {
                result = inputValue / 1.609344;
            } else if (fromUnit.equals("Kilometre") && toUnit.equals("Inch")) {
                result = inputValue * 39370.1;
            } else if (fromUnit.equals("Kilometre") && toUnit.equals("Centimetre")) {
                result = inputValue * 100000;
            }
            else if (fromUnit.equals("Mil") && toUnit.equals("Metre")) {
                result = inputValue * 1609.344;
            } else if (fromUnit.equals("Mil") && toUnit.equals("Kilometre")) {
                result = inputValue * 1.609344;
            } else if (fromUnit.equals("Mil") && toUnit.equals("Inch")) {
                result = inputValue * 63360;
            } else if (fromUnit.equals("Mil") && toUnit.equals("Centimetre")) {
                result = inputValue * 160934.4;
            }
            else if (fromUnit.equals("Inch") && toUnit.equals("Metre")) {
                result = inputValue / 39.3701;
            } else if (fromUnit.equals("Inch") && toUnit.equals("Kilometre")) {
                result = inputValue / 39370.1;
            } else if (fromUnit.equals("Inch") && toUnit.equals("Mil")) {
                result = inputValue / 63360;
            } else if (fromUnit.equals("Inch") && toUnit.equals("Centimetre")) {
                result = inputValue * 2.54;
            }
            else if (fromUnit.equals("Centimetre") && toUnit.equals("Metre")) {
                result = inputValue / 100;
            } else if (fromUnit.equals("Centimetre") && toUnit.equals("Kilometre")) {
                result = inputValue / 100000;
            } else if (fromUnit.equals("Centimetre") && toUnit.equals("Mil")) {
                result = inputValue / 160934.4;
            } else if (fromUnit.equals("Centimetre") && toUnit.equals("Inch")) {
                result = inputValue / 2.54;
            }

            else if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) {
                result = inputValue * 1000;
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Ton")) {
                result = inputValue / 1000;
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Pound")) {
                result = inputValue * 2.20462;
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Ounce")) {
                result = inputValue * 35.274;
            }
            else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) {
                result = inputValue / 1000;
            } else if (fromUnit.equals("Gram") && toUnit.equals("Ton")) {
                result = inputValue / 1e6;
            } else if (fromUnit.equals("Gram") && toUnit.equals("Pound")) {
                result = inputValue / 453.592;
            } else if (fromUnit.equals("Gram") && toUnit.equals("Ounce")) {
                result = inputValue / 28.3495;
            }
            else if (fromUnit.equals("Ton") && toUnit.equals("Kilogram")) {
                result = inputValue * 1000;
            } else if (fromUnit.equals("Ton") && toUnit.equals("Gram")) {
                result = inputValue * 1e6;
            } else if (fromUnit.equals("Ton") && toUnit.equals("Pound")) {
                result = inputValue * 2204.62;
            } else if (fromUnit.equals("Ton") && toUnit.equals("Ounce")) {
                result = inputValue * 35274;
            }
            else if (fromUnit.equals("Pound") && toUnit.equals("Kilogram")) {
                result = inputValue / 2.20462;
            } else if (fromUnit.equals("Pound") && toUnit.equals("Gram")) {
                result = inputValue * 453.592;
            } else if (fromUnit.equals("Pound") && toUnit.equals("Ton")) {
                result = inputValue / 2204.62;
            } else if (fromUnit.equals("Pound") && toUnit.equals("Ounce")) {
                result = inputValue * 16;
            }
            else if (fromUnit.equals("Ounce") && toUnit.equals("Kilogram")) {
                result = inputValue / 35.274;
            } else if (fromUnit.equals("Ounce") && toUnit.equals("Gram")) {
                result = inputValue * 28.3495;
            } else if (fromUnit.equals("Ounce") && toUnit.equals("Ton")) {
                result = inputValue / 35274;
            } else if (fromUnit.equals("Ounce") && toUnit.equals("Pound")) {
                result = inputValue / 16;
            }

            else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
                result = inputValue * 9/5 + 32;
            } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
                result = inputValue + 273.15;
            }
            else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
                result = (inputValue - 32) * 5/9;
            } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
                result = (inputValue - 32) * 5/9 + 273.15;
            }
            else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
                result = inputValue - 273.15;
            } else if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) {
                result = (inputValue - 273.15) * 9/5 + 32;
            }

            else if (fromUnit.equals("Litre") && toUnit.equals("Mililitre")) {
                result = inputValue * 1000;
            } else if (fromUnit.equals("Litre") && toUnit.equals("Galon")) {
                result = inputValue / 3.78541;
            } else if (fromUnit.equals("Litre") && toUnit.equals("Pint")) {
                result = inputValue * 2.11338;
            }
            else if (fromUnit.equals("Mililitre") && toUnit.equals("Litre")) {
                result = inputValue / 1000;
            } else if (fromUnit.equals("Mililitre") && toUnit.equals("Galon")) {
                result = inputValue / 3785.41;
            } else if (fromUnit.equals("Mililitre") && toUnit.equals("Pint")) {
                result = inputValue / 473.176;
            }
            else if (fromUnit.equals("Galon") && toUnit.equals("Litre")) {
                result = inputValue * 3.78541;
            } else if (fromUnit.equals("Galon") && toUnit.equals("Mililitre")) {
                result = inputValue * 3785.41;
            } else if (fromUnit.equals("Galon") && toUnit.equals("Pint")) {
                result = inputValue * 8;
            }
            else if (fromUnit.equals("Pint") && toUnit.equals("Litre")) {
                result = inputValue / 2.11338;
            } else if (fromUnit.equals("Pint") && toUnit.equals("Mililitre")) {
                result = inputValue * 473.176;
            } else if (fromUnit.equals("Pint") && toUnit.equals("Galon")) {
                result = inputValue / 8;
            }

            else if (fromUnit.equals("Saniye") && toUnit.equals("Dakika")) {
            result = inputValue / 60;
            } else if (fromUnit.equals("Saniye") && toUnit.equals("Saat")) {
                result = inputValue / 3600;
            } else if (fromUnit.equals("Saniye") && toUnit.equals("Gün")) {
                result = inputValue / 86400;
            } else if (fromUnit.equals("Saniye") && toUnit.equals("Hafta")) {
                result = inputValue / 604800;
            } else if (fromUnit.equals("Saniye") && toUnit.equals("Yıl")) {
                result = inputValue / 31536000;
            }
            else if (fromUnit.equals("Dakika") && toUnit.equals("Saniye")) {
                result = inputValue * 60;
            } else if (fromUnit.equals("Dakika") && toUnit.equals("Saat")) {
                result = inputValue / 60;
            } else if (fromUnit.equals("Dakika") && toUnit.equals("Gün")) {
                result = inputValue / 1440;
            } else if (fromUnit.equals("Dakika") && toUnit.equals("Hafta")) {
                result = inputValue / 10080;
            } else if (fromUnit.equals("Dakika") && toUnit.equals("Yıl")) {
                result = inputValue / 525600;
            }
            else if (fromUnit.equals("Saat") && toUnit.equals("Saniye")) {
                result = inputValue * 3600;
            } else if (fromUnit.equals("Saat") && toUnit.equals("Dakika")) {
                result = inputValue * 60;
            } else if (fromUnit.equals("Saat") && toUnit.equals("Gün")) {
                result = inputValue / 24;
            } else if (fromUnit.equals("Saat") && toUnit.equals("Hafta")) {
                result = inputValue / 168;
            } else if (fromUnit.equals("Saat") && toUnit.equals("Yıl")) {
                result = inputValue / 8760;
            }
            else if (fromUnit.equals("Gün") && toUnit.equals("Saniye")) {
                result = inputValue * 86400;
            } else if (fromUnit.equals("Gün") && toUnit.equals("Dakika")) {
                result = inputValue * 1440;
            } else if (fromUnit.equals("Gün") && toUnit.equals("Saat")) {
                result = inputValue * 24;
            } else if (fromUnit.equals("Gün") && toUnit.equals("Hafta")) {
                result = inputValue / 7;
            } else if (fromUnit.equals("Gün") && toUnit.equals("Yıl")) {
                result = inputValue / 365;
            }
            else if (fromUnit.equals("Hafta") && toUnit.equals("Saniye")) {
                result = inputValue * 604800;
            } else if (fromUnit.equals("Hafta") && toUnit.equals("Dakika")) {
                result = inputValue * 10080;
            } else if (fromUnit.equals("Hafta") && toUnit.equals("Saat")) {
                result = inputValue * 168;
            } else if (fromUnit.equals("Hafta") && toUnit.equals("Gün")) {
                result = inputValue * 7;
            } else if (fromUnit.equals("Hafta") && toUnit.equals("Yıl")) {
                result = inputValue / 52;
            }
            else if (fromUnit.equals("Yıl") && toUnit.equals("Saniye")) {
                result = inputValue * 31536000;
            } else if (fromUnit.equals("Yıl") && toUnit.equals("Dakika")) {
                result = inputValue * 525600;
            } else if (fromUnit.equals("Yıl") && toUnit.equals("Saat")) {
                result = inputValue * 8760;
            } else if (fromUnit.equals("Yıl") && toUnit.equals("Gün")) {
                result = inputValue * 365;
            } else if (fromUnit.equals("Yıl") && toUnit.equals("Hafta")) {
                result = inputValue * 52;
            }else if (fromUnit.equals("Metrekare") && toUnit.equals("Kilometrekare")) result = inputValue / 1000000;
            else if (fromUnit.equals("Metrekare") && toUnit.equals("Hektar")) result = inputValue / 10000;
            else if (fromUnit.equals("Metrekare") && toUnit.equals("Dönüm")) result = inputValue / 1000;
            else if (fromUnit.equals("Metrekare") && toUnit.equals("Acre")) result = inputValue / 4046.86;
            else if (fromUnit.equals("Metrekare") && toUnit.equals("Square foot")) result = inputValue * 10.764;
            else if (fromUnit.equals("Kilometrekare") && toUnit.equals("Metrekare")) result = inputValue * 1000000;
            else if (fromUnit.equals("Kilometrekare") && toUnit.equals("Hektar")) result = inputValue * 100;
            else if (fromUnit.equals("Kilometrekare") && toUnit.equals("Dönüm")) result = inputValue * 1000;
            else if (fromUnit.equals("Kilometrekare") && toUnit.equals("Acre")) result = inputValue * 247.105;
            else if (fromUnit.equals("Kilometrekare") && toUnit.equals("Square foot")) result = inputValue * 10763910.4;
            else if (fromUnit.equals("Hektar") && toUnit.equals("Metrekare")) result = inputValue * 10000;
            else if (fromUnit.equals("Hektar") && toUnit.equals("Kilometrekare")) result = inputValue / 100;
            else if (fromUnit.equals("Hektar") && toUnit.equals("Dönüm")) result = inputValue * 10;
            else if (fromUnit.equals("Hektar") && toUnit.equals("Acre")) result = inputValue * 2.47105;
            else if (fromUnit.equals("Hektar") && toUnit.equals("Square foot")) result = inputValue * 107639.104;
            else if (fromUnit.equals("Dönüm") && toUnit.equals("Metrekare")) result = inputValue * 1000;
            else if (fromUnit.equals("Dönüm") && toUnit.equals("Kilometrekare")) result = inputValue / 1000;
            else if (fromUnit.equals("Dönüm") && toUnit.equals("Hektar")) result = inputValue / 10;
            else if (fromUnit.equals("Dönüm") && toUnit.equals("Acre")) result = inputValue / 4.04686;
            else if (fromUnit.equals("Dönüm") && toUnit.equals("Square foot")) result = inputValue * 10763.91;
            else if (fromUnit.equals("Acre") && toUnit.equals("Metrekare")) result = inputValue * 4046.86;
            else if (fromUnit.equals("Acre") && toUnit.equals("Kilometrekare")) result = inputValue / 247.105;
            else if (fromUnit.equals("Acre") && toUnit.equals("Hektar")) result = inputValue / 2.47105;
            else if (fromUnit.equals("Acre") && toUnit.equals("Dönüm")) result = inputValue * 4.04686;
            else if (fromUnit.equals("Acre") && toUnit.equals("Square foot")) result = inputValue * 43560;
            else if (fromUnit.equals("Square foot") && toUnit.equals("Metrekare")) result = inputValue / 10.764;
            else if (fromUnit.equals("Square foot") && toUnit.equals("Kilometrekare")) result = inputValue / 10763910.4;
            else if (fromUnit.equals("Square foot") && toUnit.equals("Hektar")) result = inputValue / 107639.104;
            else if (fromUnit.equals("Square foot") && toUnit.equals("Dönüm")) result = inputValue / 10763.91;
            else if (fromUnit.equals("Square foot") && toUnit.equals("Acre")) result = inputValue / 43560;
            else if (fromUnit.equals("m/s") && toUnit.equals("km/h")) result = inputValue * 3.6;
            else if (fromUnit.equals("m/s") && toUnit.equals("mph")) result = inputValue * 2.23694;
            else if (fromUnit.equals("m/s") && toUnit.equals("Knot")) result = inputValue * 1.94384;
            else if (fromUnit.equals("km/h") && toUnit.equals("m/s")) result = inputValue / 3.6;
            else if (fromUnit.equals("km/h") && toUnit.equals("mph")) result = inputValue / 1.60934;
            else if (fromUnit.equals("km/h") && toUnit.equals("Knot")) result = inputValue / 1.852;
            else if (fromUnit.equals("mph") && toUnit.equals("m/s")) result = inputValue / 2.23694;
            else if (fromUnit.equals("mph") && toUnit.equals("km/h")) result = inputValue * 1.60934;
            else if (fromUnit.equals("mph") && toUnit.equals("Knot")) result = inputValue / 1.15078;
            else if (fromUnit.equals("Knot") && toUnit.equals("m/s")) result = inputValue / 1.94384;
            else if (fromUnit.equals("Knot") && toUnit.equals("km/h")) result = inputValue * 1.852;
            else if (fromUnit.equals("Knot") && toUnit.equals("mph")) result = inputValue * 1.15078;
            else if (fromUnit.equals("Pascal") && toUnit.equals("Bar")) result = inputValue / 100000;
            else if (fromUnit.equals("Pascal") && toUnit.equals("Atm")) result = inputValue / 101325;
            else if (fromUnit.equals("Pascal") && toUnit.equals("mmHg")) result = inputValue / 133.322;
            else if (fromUnit.equals("Bar") && toUnit.equals("Pascal")) result = inputValue * 100000;
            else if (fromUnit.equals("Bar") && toUnit.equals("Atm")) result = inputValue / 1.01325;
            else if (fromUnit.equals("Bar") && toUnit.equals("mmHg")) result = inputValue * 750.062;
            else if (fromUnit.equals("Atm") && toUnit.equals("Pascal")) result = inputValue * 101325;
            else if (fromUnit.equals("Atm") && toUnit.equals("Bar")) result = inputValue * 1.01325;
            else if (fromUnit.equals("Atm") && toUnit.equals("mmHg")) result = inputValue * 760;
            else if (fromUnit.equals("mmHg") && toUnit.equals("Pascal")) result = inputValue * 133.322;
            else if (fromUnit.equals("mmHg") && toUnit.equals("Bar")) result = inputValue / 750.062;
            else if (fromUnit.equals("mmHg") && toUnit.equals("Atm")) result = inputValue / 760;
            else if (fromUnit.equals("Joule") && toUnit.equals("Kalori")) result = inputValue / 4.184;
            else if (fromUnit.equals("Joule") && toUnit.equals("kWh")) result = inputValue / 3600000;
            else if (fromUnit.equals("Joule") && toUnit.equals("eV")) result = inputValue * 6.242e18;
            else if (fromUnit.equals("Joule") && toUnit.equals("BTU")) result = inputValue / 1055.06;
            else if (fromUnit.equals("Kalori") && toUnit.equals("Joule")) result = inputValue * 4.184;
            else if (fromUnit.equals("Kalori") && toUnit.equals("kWh")) result = inputValue / 860421;
            else if (fromUnit.equals("Kalori") && toUnit.equals("eV")) result = inputValue * 2.613e19;
            else if (fromUnit.equals("Kalori") && toUnit.equals("BTU")) result = inputValue / 252.164;
            else if (fromUnit.equals("kWh") && toUnit.equals("Joule")) result = inputValue * 3600000;
            else if (fromUnit.equals("kWh") && toUnit.equals("Kalori")) result = inputValue * 860421;
            else if (fromUnit.equals("kWh") && toUnit.equals("eV")) result = inputValue * 2.247e25;
            else if (fromUnit.equals("kWh") && toUnit.equals("BTU")) result = inputValue * 3412.14;
            else if (fromUnit.equals("eV") && toUnit.equals("Joule")) result = inputValue * 1.602e-19;
            else if (fromUnit.equals("eV") && toUnit.equals("Kalori")) result = inputValue * 3.829e-20;
            else if (fromUnit.equals("eV") && toUnit.equals("kWh")) result = inputValue * 4.45e-26;
            else if (fromUnit.equals("eV") && toUnit.equals("BTU")) result = inputValue * 1.5186e-22;
            else if (fromUnit.equals("BTU") && toUnit.equals("Joule")) result = inputValue * 1055.06;
            else if (fromUnit.equals("BTU") && toUnit.equals("Kalori")) result = inputValue * 252.164;
            else if (fromUnit.equals("BTU") && toUnit.equals("kWh")) result = inputValue / 3412.14;
            else if (fromUnit.equals("BTU") && toUnit.equals("eV")) result = inputValue * 6.585e21;
            else if (fromUnit.equals("Bit") && toUnit.equals("Byte")) result = inputValue / 8;
            else if (fromUnit.equals("Bit") && toUnit.equals("Kilobyte")) result = inputValue / 8192;
            else if (fromUnit.equals("Bit") && toUnit.equals("Megabyte")) result = inputValue / 8.389e+6;
            else if (fromUnit.equals("Bit") && toUnit.equals("Gigabyte")) result = inputValue / 8.59e+9;
            else if (fromUnit.equals("Bit") && toUnit.equals("Terabyte")) result = inputValue / 8.796e+12;
            else if (fromUnit.equals("Byte") && toUnit.equals("Bit")) result = inputValue * 8;
            else if (fromUnit.equals("Byte") && toUnit.equals("Kilobyte")) result = inputValue / 1024;
            else if (fromUnit.equals("Byte") && toUnit.equals("Megabyte")) result = inputValue / 1048576;
            else if (fromUnit.equals("Byte") && toUnit.equals("Gigabyte")) result = inputValue / 1073741824;
            else if (fromUnit.equals("Byte") && toUnit.equals("Terabyte")) result = inputValue / 1099511627776L;
            else if (fromUnit.equals("Kilobyte") && toUnit.equals("Bit")) result = inputValue * 8192;
            else if (fromUnit.equals("Kilobyte") && toUnit.equals("Byte")) result = inputValue * 1024;
            else if (fromUnit.equals("Kilobyte") && toUnit.equals("Megabyte")) result = inputValue / 1024;
            else if (fromUnit.equals("Kilobyte") && toUnit.equals("Gigabyte")) result = inputValue / 1048576;
            else if (fromUnit.equals("Kilobyte") && toUnit.equals("Terabyte")) result = inputValue / 1073741824;
            else if (fromUnit.equals("Megabyte") && toUnit.equals("Bit")) result = inputValue * 8.389e+6;
            else if (fromUnit.equals("Megabyte") && toUnit.equals("Byte")) result = inputValue * 1048576;
            else if (fromUnit.equals("Megabyte") && toUnit.equals("Kilobyte")) result = inputValue * 1024;
            else if (fromUnit.equals("Megabyte") && toUnit.equals("Gigabyte")) result = inputValue / 1024;
            else if (fromUnit.equals("Megabyte") && toUnit.equals("Terabyte")) result = inputValue / 1048576;
            else if (fromUnit.equals("Gigabyte") && toUnit.equals("Bit")) result = inputValue * 8.59e+9;
            else if (fromUnit.equals("Gigabyte") && toUnit.equals("Byte")) result = inputValue * 1073741824;
            else if (fromUnit.equals("Gigabyte") && toUnit.equals("Kilobyte")) result = inputValue * 1048576;
            else if (fromUnit.equals("Gigabyte") && toUnit.equals("Megabyte")) result = inputValue * 1024;
            else if (fromUnit.equals("Gigabyte") && toUnit.equals("Terabyte")) result = inputValue / 1024;
            else if (fromUnit.equals("Terabyte") && toUnit.equals("Bit")) result = inputValue * 8.796e+12;
            else if (fromUnit.equals("Terabyte") && toUnit.equals("Byte")) result = inputValue * 1099511627776L;
            else if (fromUnit.equals("Terabyte") && toUnit.equals("Kilobyte")) result = inputValue * 1073741824;
            else if (fromUnit.equals("Terabyte") && toUnit.equals("Megabyte")) result = inputValue * 1048576;
            else if (fromUnit.equals("Terabyte") && toUnit.equals("Gigabyte")) result = inputValue * 1024;
            else if (fromUnit.equals("Derece") && toUnit.equals("Radyan")) result = inputValue * (Math.PI/180);
            else if (fromUnit.equals("Derece") && toUnit.equals("Grad")) result = inputValue * 10/9;
            else if (fromUnit.equals("Derece") && toUnit.equals("Milyem")) result = inputValue * 17.777778;
            else if (fromUnit.equals("Radyan") && toUnit.equals("Derece")) result = inputValue * (180/Math.PI);
            else if (fromUnit.equals("Radyan") && toUnit.equals("Grad")) result = inputValue * 63.661977;
            else if (fromUnit.equals("Radyan") && toUnit.equals("Milyem")) result = inputValue * 1018.591636;
            else if (fromUnit.equals("Grad") && toUnit.equals("Derece")) result = inputValue * 0.9;
            else if (fromUnit.equals("Grad") && toUnit.equals("Radyan")) result = inputValue * 0.015708;
            else if (fromUnit.equals("Grad") && toUnit.equals("Milyem")) result = inputValue * 16;
            else if (fromUnit.equals("Milyem") && toUnit.equals("Derece")) result = inputValue * 0.05625;
            else if (fromUnit.equals("Milyem") && toUnit.equals("Radyan")) result = inputValue * 0.000982;
            else if (fromUnit.equals("Milyem") && toUnit.equals("Grad")) result = inputValue * 0.0625;
            else if (fromUnit.equals("Piksel") && toUnit.equals("Point")) result = inputValue * 0.75;
            else if (fromUnit.equals("Piksel") && toUnit.equals("Em")) result = inputValue / 16;
            else if (fromUnit.equals("Piksel") && toUnit.equals("Santimetre")) result = inputValue * 0.026458;
            else if (fromUnit.equals("Point") && toUnit.equals("Piksel")) result = inputValue / 0.75;
            else if (fromUnit.equals("Point") && toUnit.equals("Em")) result = inputValue / 12;
            else if (fromUnit.equals("Point") && toUnit.equals("Santimetre")) result = inputValue * 0.035278;
            else if (fromUnit.equals("Em") && toUnit.equals("Piksel")) result = inputValue * 16;
            else if (fromUnit.equals("Em") && toUnit.equals("Point")) result = inputValue * 12;
            else if (fromUnit.equals("Em") && toUnit.equals("Santimetre")) result = inputValue * 0.4233;
            else if (fromUnit.equals("Santimetre") && toUnit.equals("Piksel")) result = inputValue / 0.026458;
            else if (fromUnit.equals("Santimetre") && toUnit.equals("Point")) result = inputValue / 0.035278;
            else if (fromUnit.equals("Santimetre") && toUnit.equals("Em")) result = inputValue / 0.4233;
            else if (fromUnit.equals(toUnit)) {
                result = inputValue;
            } else {
            JOptionPane.showMessageDialog(this, "Bu dönüşüm henüz tanımlanmamıştır.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
            } 
            resultField.setText(formatResult(result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String formatResult(double value) {
        DecimalFormat df = new DecimalFormat("0.########");
        return df.format(value);
    }

    // Ana fonksiyon

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        closeBtn = new com.mycompany.buttons.CustomButtonMain();
        minimizeBtn = new com.mycompany.buttons.CustomButtonMain();
        titleLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\EREN\\Documents\\NetBeansProjects\\MathProjectTheCalc\\image\\analysing30x30.png")); // NOI18N
        jLabel1.setText("  Sayı Analiz Aracı");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(24, 24, 32));

        closeBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\EREN\\Documents\\NetBeansProjects\\MathProjectTheCalc\\image\\close.png")); // NOI18N
        closeBtn.setBorderColor(new java.awt.Color(24, 24, 32));
        closeBtn.setBorderPainted(false);
        closeBtn.setColor(new java.awt.Color(231, 76, 60));
        closeBtn.setColorClick(new java.awt.Color(153, 39, 27));
        closeBtn.setColorOver(new java.awt.Color(192, 57, 43));
        closeBtn.setFocusPainted(false);
        closeBtn.setMargin(new java.awt.Insets(6, 6, 6, 6));
        closeBtn.setRadius(50);
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        minimizeBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\EREN\\Documents\\NetBeansProjects\\MathProjectTheCalc\\image\\minus.png")); // NOI18N
        minimizeBtn.setBorderColor(new java.awt.Color(24, 24, 32));
        minimizeBtn.setBorderPainted(false);
        minimizeBtn.setColorClick(new java.awt.Color(30, 120, 180));
        minimizeBtn.setColorOver(new java.awt.Color(45, 145, 200));
        minimizeBtn.setFocusPainted(false);
        minimizeBtn.setMargin(new java.awt.Insets(6, 6, 6, 6));
        minimizeBtn.setRadius(50);
        minimizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeBtnActionPerformed(evt);
            }
        });

        titleLabel.setBackground(new java.awt.Color(24, 24, 32));
        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\EREN\\Documents\\NetBeansProjects\\MathProjectTheCalc\\image\\convert-230x30.png")); // NOI18N
        titleLabel.setText("  Birim Dönüştürücü");
        titleLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 423, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void minimizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeBtnActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UnitConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UnitConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UnitConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UnitConverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UnitConverter().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.buttons.CustomButtonMain closeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private com.mycompany.buttons.CustomButtonMain minimizeBtn;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
