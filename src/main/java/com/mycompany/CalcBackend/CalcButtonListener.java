/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CalcBackend;

import com.mycompany.auxilaryforms.FormulaForm;
import com.mycompany.ui.panels.CalcNumbersPanel;
import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;


public class CalcButtonListener{
    private final CalcNumbersPanel clcnp;
    private StringBuilder inputExpression = new StringBuilder();
    FormulaButtonListener buttonListener ;
    private boolean errorState = false;
    private BigDecimal calcVariable;
    private boolean resultJustEvaluated = false;
    
   public CalcButtonListener(CalcNumbersPanel clcnp) {
        this.clcnp = clcnp;
        this.buttonListener = new FormulaButtonListener(this); 
    }
    
    public void handleButtonPress(String buttonText) {
        if (errorState) {  
            clcnp.cleanDisplayText(); 
            inputExpression.setLength(0);
            errorState = false; 
        }
        if ("012345678900".contains(buttonText) || buttonText.equals(".")) {
            if (resultJustEvaluated) {
                clcnp.cleanDisplayText();
                inputExpression.setLength(0);
                resultJustEvaluated = false;
            }
            inputExpression.append(buttonText);
            clcnp.addDisplayText(buttonText);
        } else if ("+-÷*x()".contains(buttonText)) {
            boolean resultJustEvaluated = false;
            if (inputExpression.length() == 0) return;
            inputExpression.append(buttonText.equals("x") ? "*" : (buttonText.equals("÷") ? "/" : buttonText));
            clcnp.addDisplayText(buttonText);
        } else if (buttonText.equals("=")) {
            if(inputExpression.toString().matches(".*[+\\-*/].*")){
                try {
                    double result = evaluateExpression(inputExpression.toString());
                    clcnp.cleanDisplayText();
                    editResult(result);
                    inputExpression.setLength(0);
                    inputExpression.append(result);
                    resultJustEvaluated = true;
                } catch (Exception e) {
                    errorState = true;
                    clcnp.cleanDisplayText();
                    clcnp.addDisplayText("Hata");
                    inputExpression.setLength(0);
                }
            }
        } else if (buttonText.equals("C")) {
            clcnp.cleanDisplayText();
            inputExpression.setLength(0);
            resultJustEvaluated = false;
        } else if (buttonText.equals("←")) {
            if (inputExpression.length() > 0) {
                inputExpression.deleteCharAt(inputExpression.length() - 1);
                clcnp.deleteNumOnCalc();
            }
        }else if (buttonText.equals("%")) {
            inputExpression.append("%");
            clcnp.addDisplayText("%");
        }else if (buttonText.equals("√")) {
             if (inputExpression.length() > 0) {
            String lastNumber = getLastNumber(inputExpression.toString());
            if (!lastNumber.isEmpty()) {
                double num = Double.parseDouble(lastNumber);
                double sqrtResult = Math.sqrt(num);
                String editedResult = returnEditResult(sqrtResult);
                
                inputExpression.setLength(inputExpression.length() - lastNumber.length());
                inputExpression.append(editedResult);
                clcnp.cleanDisplayText();
                clcnp.addDisplayText(inputExpression.toString());  

            }
        }
            
        }else if (buttonText.equals("Formüller")){
             FormulaForm formulaForm = new FormulaForm(this);
             formulaForm.setVisible(true);
        }else if (buttonText.equals("Değişken")){
            if (calcVariable != null){
                inputExpression.append(calcVariable.toString());
                editResult(calcVariable.doubleValue());
            }
        }
    }
     private double evaluateExpression(String expression) {
        return evaluatePostfix(convertToPostfix(expression));
    }
    private String convertToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/%()", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.matches("[0-9]+(\\.[0-9]*)?")) {
                output.append(token).append(" ");
            } else if ("+-*/".contains(token)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token.charAt(0))) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(token.charAt(0));
            } else if (token.equals("(")) {
                stack.push('(');
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop();
            }else if (token.equals("%")) {
            output.append(token).append(" ");
            }
        }
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }
        return output.toString();
    }
    
    private int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/','%' -> 2;
            default -> -1;
        };
    }
    
     private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(postfix);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.matches("[0-9]+(\\.[0-9]*)?")) {
                stack.push(Double.parseDouble(token));
            }else {
                if(token.equals("%")){
                    double num = stack.pop();
                    stack.push(num / 100);
                }else{
                     double num2 = stack.pop();
                double num1 = stack.pop();
                switch (token) {
                    case "+" -> stack.push(num1 + num2);
                    case "-" -> stack.push(num1 - num2);
                    case "*" -> stack.push(num1 * num2);
                    case "/" -> stack.push(num1 / num2);
                }
                }
            }
        }
         return stack.isEmpty() ? 0 : stack.pop();
    }
    private void editResult(double result) {
    if (result == (int) result) {
            clcnp.addDisplayText(String.valueOf((int) result));
        } else {
            clcnp.addDisplayText(String.valueOf(result));
        }
    }
    private String returnEditResult(double result) {
    if (result == (int) result) {
        return String.valueOf((int) result);
    } else {
        return String.valueOf(result);  
    }
}
    private String getLastNumber(String expression) {
    int i = expression.length() - 1;
    StringBuilder number = new StringBuilder();

    while (i >= 0 && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
        number.insert(0, expression.charAt(i));
        i--;
    }
    
    return number.toString();
    }
    public CalcNumbersPanel getClcnp() {
           return clcnp;
       }

    
    public void setVariable(BigDecimal newVariable) {
        calcVariable = newVariable;
    }

}
 