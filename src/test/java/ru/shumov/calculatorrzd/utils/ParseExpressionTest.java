package ru.shumov.calculatorrzd.utils;

import org.junit.jupiter.api.Test;
import ru.shumov.calculatorrzd.exceptions.UnexpectedCharError;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParseExpressionTest {
    @Test
    void testParseExpressionTORPN() {
        ParseExpression parser = new ParseExpression();

        String expression1 = "2 + 3";
        List<MathSymbol> expected1 = new LinkedList<>();
        expected1.add(new MathSymbol("2", MathSymbolType.NUMBER));
        expected1.add(new MathSymbol("3", MathSymbolType.NUMBER));
        expected1.add(new MathSymbol("+", MathSymbolType.OPERATOR));

        List<MathSymbol> result1 = parser.parseExpressionTORPN(expression1);
        assertEquals(expected1, result1);

        String expression2 = "5 + 2 * 4 / 3 - 1";
        List<MathSymbol> expected2 = new LinkedList<>();
        expected2.add(new MathSymbol("5", MathSymbolType.NUMBER));
        expected2.add(new MathSymbol("2", MathSymbolType.NUMBER));
        expected2.add(new MathSymbol("4", MathSymbolType.NUMBER));
        expected2.add(new MathSymbol("*", MathSymbolType.OPERATOR));
        expected2.add(new MathSymbol("3", MathSymbolType.NUMBER));
        expected2.add(new MathSymbol("/", MathSymbolType.OPERATOR));
        expected2.add(new MathSymbol("+", MathSymbolType.OPERATOR));
        expected2.add(new MathSymbol("1", MathSymbolType.NUMBER));
        expected2.add(new MathSymbol("-", MathSymbolType.OPERATOR));

        List<MathSymbol> result2 = parser.parseExpressionTORPN(expression2);
        assertEquals(expected2, result2);

        String expression3 = "5 + 2 * 4 / 3 - 1 &";
        Throwable exception = assertThrows(UnexpectedCharError.class, () -> parser.parseExpressionTORPN(expression3));
        assertEquals("Unexpected symbol: &", exception.getMessage());
    }
}
