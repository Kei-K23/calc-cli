package dev.kei;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * calc CLI also a parser that can perform mathematical calculations and written in Java.
 */
public class App
{
    private static final String OPERATOR_PATTERN = "+-*/()";

    public static void main( String[] args )
    {
        System.out.println(tokenize(args[0]));
    }

//    private static List<String> infixToPostfix(String expression) {
//        Stack<String> operators = new Stack<String>();
//        List<String> output = new ArrayList<String>();
//        List<String> tokens = tokenize(expression);
//
//        for (String token : tokens) {
//
//        }
//    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, OPERATOR_PATTERN, true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return OPERATOR_PATTERN.contains(token);
    }
}
