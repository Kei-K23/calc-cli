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
    public static void main( String[] args )
    {
        if (args.length != 1) {
            System.out.println("Usage: java CalcApp <expression>");
            return;
        }

        Tokenizer tokenizer = new Tokenizer("+-*/()");
        ShuntingYard shuntingYard = new ShuntingYard("+-*/", tokenizer);

        String expression = args[0];
        List<String> postfix = shuntingYard.infixToPostfix(expression);
        System.out.println("Postfix: " + postfix);

        try {
            double result = shuntingYard.evaluatePostfix(postfix);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
