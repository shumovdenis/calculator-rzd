package ru.shumov.calculatorrzd.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shumov.calculatorrzd.exceptions.DivideByZeroException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculateRPNExpressionTest {
    private CalculateRPNExpression calculatorRPN;

    @BeforeEach
    public void setUp() {
        calculatorRPN = new CalculateRPNExpression();
    }

    @Test
    public void testCalculateRPN() {
        List<MathSymbol> symbols = new ArrayList<>();
        symbols.add(new MathSymbol("2", MathSymbolType.NUMBER));
        symbols.add(new MathSymbol("3", MathSymbolType.NUMBER));
        symbols.add(new MathSymbol("*", MathSymbolType.OPERATOR));
        symbols.add(new MathSymbol("5", MathSymbolType.NUMBER));
        symbols.add(new MathSymbol("+", MathSymbolType.OPERATOR));

        double result = calculatorRPN.calculateRPN(symbols);

        assertEquals(11.0, result, 0.001);
    }

    @Test
    public void testCalculateRPN_DivideByZeroException() {
        List<MathSymbol> symbols = new ArrayList<>();
        symbols.add(new MathSymbol("2", MathSymbolType.NUMBER));
        symbols.add(new MathSymbol("0", MathSymbolType.NUMBER));
        symbols.add(new MathSymbol("/", MathSymbolType.OPERATOR));
        Throwable exception = assertThrows(DivideByZeroException.class, ()-> calculatorRPN.calculateRPN(symbols));
        assertEquals("Divide by zero exception", exception.getMessage());
    }
}
