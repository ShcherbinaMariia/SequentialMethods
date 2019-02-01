package parser.tokens;

public class Token {

    public Type type;

    public Token(){}

    public Token(Type type){ this.type = type; }

    public enum Type{
        OPEN_PARENTHESIS,
        CLOSED_PARENTHESIS,
        PREDICATE,
        DISJUNCTION,
        CONJUNCTION,
        IMPLICATION,
        NEGATION,
        EXISTS,
        FOR_ALL
    }
}
