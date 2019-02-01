package parser;

public class Lexer {

    private String expression;
    private int currentIndex = -1;

    Lexer(String expression){
        this.expression = expression.trim();
    }

    private boolean isLowerCaseLetter(char c){
        return c>='a' && c<='z';
    }

    private boolean isUpperCaseLetter(char c){
        return c>='A' && c<='Z';
    }

    public Lexeme getNext() throws Exception {
        currentIndex++;
        char symbol = expression.charAt(currentIndex);

        while (symbol == ' ')  {
            currentIndex ++;
            symbol = expression.charAt(currentIndex);
        }

        if (symbol == '('){
            return new Lexeme(Lexeme.Type.OPEN_PARENTHESIS);
        }
        if (symbol == ')'){
            return new Lexeme(Lexeme.Type.CLOSED_PARENTHESIS);
        }
        if (symbol == '['){
            return new Lexeme(Lexeme.Type.OPEN_SQUARE_PARENTHESIS);
        }
        if (symbol == ']'){
            return new Lexeme(Lexeme.Type.CLOSED_SQUARE_PARENTHESIS);
        }
        if (symbol == ','){
            return new Lexeme(Lexeme.Type.COMMA);
        }
        if (symbol == '&'){
            return new Lexeme(Lexeme.Type.CONJUNCTION);
        }
        if (symbol == '|'){
            return new Lexeme(Lexeme.Type.DISJUNCTION);
        }
        if (symbol == '-' && hasNext()){
            currentIndex++;
            if (expression.charAt(currentIndex) == '>')
                return new Lexeme(Lexeme.Type.IMPLICATION);
        }
        if (symbol == '!'){
            return new Lexeme(Lexeme.Type.NEGATION);
        }
        if (isLowerCaseLetter(symbol)){
            return new Lexeme(Lexeme.Type.VARIABLE_NAME, symbol);
        }
        if (isUpperCaseLetter(symbol)){
            return new Lexeme(Lexeme.Type.PREDICATE_NAME, symbol);
        }
        if (symbol == '#'){
            return new Lexeme(Lexeme.Type.EXISTS);
        }
        if (symbol == '@'){
            return new Lexeme(Lexeme.Type.FOR_ALL);
        }
        throw new Exception();
    }

    public boolean hasNext(){
        return currentIndex != expression.length() - 1;
    }
}
