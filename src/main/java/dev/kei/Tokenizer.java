package dev.kei;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {

    private final String OPERATOR_PATTERN;

    public Tokenizer(final String OPERATOR_PATTERN) {
        this.OPERATOR_PATTERN = OPERATOR_PATTERN;
    }

    public List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, this.OPERATOR_PATTERN , true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }

}
