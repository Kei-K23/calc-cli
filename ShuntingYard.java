
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShuntingYard {

    private final String OPERATOR_PATTERN;
    private final Tokenizer tokenizer;

    public ShuntingYard(final String OPERATOR_PATTERN, Tokenizer tokenizer) {
        this.OPERATOR_PATTERN = OPERATOR_PATTERN;
        this.tokenizer = tokenizer;
    }

    public double evaluatePostfix(List<String> postfix) {
        Stack<Double> values = new Stack<>();
        for (String token : postfix) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (values.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
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
                        throw new IllegalArgumentException("Invalid operator in postfix expression");
                }
                values.push(result);
            }
        }
        if (values.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        return values.pop();
    }

    public List<String> infixToPostfix(String expression) {
        Stack<String> operators = new Stack<>();
        List<String> output = new ArrayList<>();
        List<String> tokens = tokenizer.tokenize(expression);

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && !operators.peek().equals("(") && precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return OPERATOR_PATTERN.contains(token);
    }

    private int precedence(String operator) {
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
