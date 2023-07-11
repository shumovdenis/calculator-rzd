package ru.shumov.calculatorrzd.service;


import org.junit.Assert;
import org.junit.Test;


public class CalcServiceTest {
    private CalcService сalcService = new CalcService();

    @Test
    public void testCalculate() {
        String expression = "2 + 3 * 4";
        double expected = 14.0;

        double actual = сalcService.calculate(expression);

        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testCalculateWithBrackets() {
        String expression = "(6 + 2) * (4 - 1)";
        double expected = 24.0;

        double actual = сalcService.calculate(expression);

        Assert.assertEquals(expected, actual, 0.0001);
    }
}
