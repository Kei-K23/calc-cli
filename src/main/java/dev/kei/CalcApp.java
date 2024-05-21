package dev.kei;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * calc CLI also a parser that can perform mathematical calculations and written in Java.
 */
public class CalcApp
{
    private static final String OPERATOR_PATTERN = "+-*/()";

    public static void main( String[] args )
    {
        String expression = args[0];
        Tokenizer tokenizer = new Tokenizer(expression);
        ShuntingYard shuntingYard = new ShuntingYard(OPERATOR_PATTERN, tokenizer);
        List<String> postfix = shuntingYard.infixToPostfix(expression);
        System.out.println("Result : " + shuntingYard.evaluatePostfix(postfix));
    }

}
