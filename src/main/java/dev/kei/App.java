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
        System.out.println("Result : " + evaluatePostfix(infixToPostfix(args[0])));
    }

    private static double evaluatePostfix(List<String> postfix ) {
        Stack<Double> values = new Stack<>();
        for (String token : postfix) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double v2 = values.pop();
                double v1 = values.pop();
                double result = 0;
                switch (token) {
                    case "+":
                        result = v1 + v2;
                        break;
                    case "-":
                        result = v1 - v2;
                        break;
                    case "*":
                        result = v1 * v2;
                        break;
                    case "/":
                        result = v1 / v2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid postfix");
                }
                values.push(result);
            }
        }
        return values.pop();
    }

    private static List<String> infixToPostfix(String expression) {
        Stack<String> operators = new Stack<>();
        List<String> output = new ArrayList<>();
        List<String> tokens = tokenize(expression);

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

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

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }
}
