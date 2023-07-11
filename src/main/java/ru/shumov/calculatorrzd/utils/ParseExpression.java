package ru.shumov.calculatorrzd.utils;

import ru.shumov.calculatorrzd.exceptions.UnexpectedCharError;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ParseExpression {
    public List<MathSymbol> parseExpressionTORPN(String expression) {
        List<MathSymbol> symbols = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        int priority;
        int pos = 0;


        while (pos < expression.length()) {
            if (expression.charAt(pos) == ' ') {
                pos++;
                continue;
            }
            priority = getPriority((expression.charAt(pos)));

            if (priority == 0) {
                char c = expression.charAt(pos);
                if (c <= '9' && c >= '0') {
                    StringBuilder sb = new StringBuilder();
                    do {
                        sb.append(c);
                        pos++;
                        if (pos >= expression.length()) {
                            break;
                        }
                        c = expression.charAt(pos);
                    } while ((c <= '9' && c >= '0') || c == '.');
                    symbols.add(new MathSymbol(sb.toString(), MathSymbolType.NUMBER));
                }
                continue;
            }

            if (priority == 1) {
                stack.push(expression.charAt(pos));
                pos++;
                continue;
            }

            if (priority > 1) {
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        char c = stack.pop();
                        symbols.add(new MathSymbol(c, MathSymbolType.OPERATOR));
                    } else {
                        break;
                    }
                }
                stack.push(expression.charAt(pos));
                pos++;
                continue;
            }

            if (priority == -1) {
                while (getPriority(stack.peek()) != 1) {
                    char c = stack.pop();
                    symbols.add(new MathSymbol(c, MathSymbolType.OPERATOR));
                }
                stack.pop();
                pos++;
            }
        }

        while (!stack.empty()) {
            char c = stack.pop();
            symbols.add(new MathSymbol(c, MathSymbolType.OPERATOR));
        }
        return symbols;
    }

    private int getPriority(char token) {
        switch (token) {
            case '*', '/' -> {
                return 3;
            }
            case '+', '-' -> {
                return 2;
            }
            case '(' -> {
                return 1;
            }
            case ')' -> {
                return -1;
            }
            default -> {
                if (token <= '9' && token >= '0') {
                    return 0;
                } else {
                    throw new UnexpectedCharError("Unexpected symbol: " + token);
                }
            }
        }
    }
}
