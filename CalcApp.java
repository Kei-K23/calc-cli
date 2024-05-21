import java.util.List;

/**
 * calc CLI also a parser that can perform mathematical calculations and written in Java.
 */
public class CalcApp
{
    public static void main( String[] args )
    {
        if (args.length != 1) {
            System.out.println("Usage: java CalcApp.java <expression> Eg: java CalcApp.java '1 + 2 - 1'");
            return;
        }
        String expression = args[0];

        Tokenizer tokenizer = new Tokenizer("+-*/()");
        ShuntingYard shuntingYard = new ShuntingYard("+-*/", tokenizer);

        List<String> postfix = shuntingYard.infixToPostfix(expression);
        try {
            double result = shuntingYard.evaluatePostfix(postfix);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
