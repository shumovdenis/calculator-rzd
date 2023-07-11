package ru.shumov.calculatorrzd.service;

import org.springframework.stereotype.Service;
import ru.shumov.calculatorrzd.utils.CalculateRPNExpression;
import ru.shumov.calculatorrzd.utils.MathSymbol;
import ru.shumov.calculatorrzd.utils.ParseExpression;

import java.util.List;

@Service
public class CalcService {

    public double calculate(String expression) {
        ParseExpression parseExpression = new ParseExpression();
        CalculateRPNExpression calcRPN = new CalculateRPNExpression();
        List<MathSymbol> symbols = parseExpression.parseExpressionTORPN(expression);
        return calcRPN.calculateRPN(symbols);
    }
}
