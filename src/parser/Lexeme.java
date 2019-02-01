package parser;

public class Lexeme {

    public Type type;
    public char value;

    Lexeme(Type type){
        this.type = type;
    }

    Lexeme(Type type, char value){
        this.type = type;
        this.value = value;
    }

    public enum Type{
        OPEN_PARENTHESIS,
        CLOSED_PARENTHESIS,
        OPEN_SQUARE_PARENTHESIS,
        CLOSED_SQUARE_PARENTHESIS,
        COMMA,
        PREDICATE_NAME,
        VARIABLE_NAME,
        DISJUNCTION,
        CONJUNCTION,
        IMPLICATION,
        NEGATION,
        EXISTS,
        FOR_ALL
    }

}
