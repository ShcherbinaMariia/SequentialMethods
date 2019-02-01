package parser.tokens;

public class QuantifierToken extends Token {

    public char variable;

    public QuantifierToken(Token.Type type, char variable) throws Exception {
        super(type);
        if (type != Type.FOR_ALL && type!=Type.EXISTS){
            throw new Exception("Wrong token type");
        }
        this.type = type;
        this.variable = variable;
    }
}
