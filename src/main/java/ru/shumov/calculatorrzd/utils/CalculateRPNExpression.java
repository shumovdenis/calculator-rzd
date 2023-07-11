package ru.shumov.calculatorrzd.utils;

import ru.shumov.calculatorrzd.exceptions.DivideByZeroException;

import java.util.List;
import java.util.Stack;

public class CalculateRPNExpression {
    public double calculateRPN(List<MathSymbol> symbols) {

        Stack<Double> stack = new Stack<>();

        for (MathSymbol symbol : symbols) {

            if (symbol.getType() == MathSymbolType.NUMBER) {
                stack.push(Double.parseDouble(symbol.getValue()));
            }

            if (symbol.getType() == MathSymbolType.OPERATOR) {
                double a = stack.pop();
                double b = stack.pop();
                if (symbol.getValue().equals("*")) stack.push(b * a);
                if (symbol.getValue().equals("/")) {
                    if (a == 0) {
                        throw new DivideByZeroException("Divide by zero exception");
                    }
                    stack.push(b / a);
                }
                if (symbol.getValue().equals("+")) stack.push(b + a);
                if (symbol.getValue().equals("-")) stack.push(b - a);
            }
        }
        return stack.pop();
    }
}
