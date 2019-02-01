package parser;

import parser.tokens.PredicateToken;
import parser.tokens.QuantifierToken;
import parser.tokens.Token;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    Lexer lexer;

    Tokenizer(String expression){
        this.lexer = new Lexer(expression);
    }

    private Character getVariableName() throws Exception {

        Lexeme nextLexeme = lexer.getNext();
        if (nextLexeme.type == Lexeme.Type.VARIABLE_NAME)
        {
            return nextLexeme.value;
        }
        else throw new Exception("Wrong syntax");

    }

    private QuantifierToken processQuantifier(Lexeme lexeme) throws Exception{

        char variable = getVariableName();

        if (lexeme.type == Lexeme.Type.EXISTS)
            return new QuantifierToken(Token.Type.EXISTS, variable);

        return new QuantifierToken(Token.Type.FOR_ALL, variable);
    }

    private PredicateToken processPredicate(Lexeme lexeme) throws Exception{

        List variables = new LinkedList();

        Lexeme nextLexeme = lexer.getNext();
        if (nextLexeme.type != Lexeme.Type.OPEN_SQUARE_PARENTHESIS) throw new Exception("Wrong syntax");

        while(nextLexeme.type != Lexeme.Type.CLOSED_SQUARE_PARENTHESIS){
            variables.add(getVariableName());
            nextLexeme = lexer.getNext();
            if (nextLexeme.type != Lexeme.Type.COMMA && nextLexeme.type != Lexeme.Type.CLOSED_SQUARE_PARENTHESIS)
                throw new Exception("Wrong syntax");
        }

        return new PredicateToken(variables, lexeme.value);

    }

    Token getNext() throws Exception{

        if (!lexer.hasNext()) throw new Exception("Nothing to tokenize");

        Lexeme lexeme = lexer.getNext();

        if (lexeme.type == Lexeme.Type.OPEN_PARENTHESIS){
            return new Token(Token.Type.OPEN_PARENTHESIS);
        }
        if (lexeme.type == Lexeme.Type.CLOSED_PARENTHESIS){
            return new Token(Token.Type.CLOSED_PARENTHESIS);
        }
        if (lexeme.type == Lexeme.Type.DISJUNCTION){
            return new Token(Token.Type.DISJUNCTION);
        }
        if (lexeme.type == Lexeme.Type.CONJUNCTION){
            return new Token(Token.Type.CONJUNCTION);
        }
        if (lexeme.type == Lexeme.Type.NEGATION){
            return new Token(Token.Type.NEGATION);
        }
        if (lexeme.type == Lexeme.Type.IMPLICATION){
            return new Token(Token.Type.IMPLICATION);
        }
        if (lexeme.type == Lexeme.Type.EXISTS || lexeme.type == Lexeme.Type.FOR_ALL){
            return processQuantifier(lexeme);
        }
        if (lexeme.type == Lexeme.Type.PREDICATE_NAME){
            return processPredicate(lexeme);
        }
        throw new Exception("Can't match token exception");
    }

    boolean hasNext(){
        return lexer.hasNext();
    }

}
