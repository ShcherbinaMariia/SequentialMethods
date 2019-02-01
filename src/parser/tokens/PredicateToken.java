package parser.tokens;

import java.util.List;

public class PredicateToken extends Token {

    public List<Character> variables;
    public char name;

    public PredicateToken(List<Character> variables, char name){
        super(Type.PREDICATE);
        this.type = Type.PREDICATE;
        this.name = name;
        this.variables = variables;
    }

}
