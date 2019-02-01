package parser;

import parser.tokens.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RPNConverter {

    Stack<Token> operationsStack = new Stack<>();
    List<Token> result = new LinkedList<>();
    Tokenizer tokenizer;

    RPNConverter(String expression) {
        this.tokenizer = new Tokenizer(expression);
    }

    private int getPriority(Token token) throws Exception {
        if (token.type == Token.Type.EXISTS || token.type == Token.Type.FOR_ALL || token.type == Token.Type.NEGATION)
            return 4;
        if (token.type == Token.Type.CONJUNCTION)
            return 3;
        if (token.type == Token.Type.DISJUNCTION)
            return 2;
        if (token.type == Token.Type.IMPLICATION)
            return 1;
        throw new Exception("Unknown priority");
    }

    List<Token> getRPN() throws Exception {

        while (tokenizer.hasNext()) {
            Token token = tokenizer.getNext();
            switch (token.type) {
                case PREDICATE:
                    result.add(token);
                    break;
                case EXISTS:
                case FOR_ALL:
                case NEGATION:
                    operationsStack.push(token);
                    break;
                case CONJUNCTION:
                case IMPLICATION:
                case DISJUNCTION:
                    while (!operationsStack.empty() &&
                            operationsStack.peek().type != Token.Type.OPEN_PARENTHESIS &&
                            getPriority(operationsStack.peek()) >= getPriority(token)){
                        result.add(operationsStack.pop());
                    }
                    operationsStack.push(token);
                    break;

                case OPEN_PARENTHESIS:
                    operationsStack.push(token);
                    break;
                case CLOSED_PARENTHESIS:
                    while (!operationsStack.empty() && operationsStack.peek().type != Token.Type.OPEN_PARENTHESIS) {
                        result.add(operationsStack.pop());
                    }
                    if (operationsStack.empty() || operationsStack.peek().type != Token.Type.OPEN_PARENTHESIS)
                        throw new Exception("Wrong syntax");
                    operationsStack.pop();
                    break;
                default:
                    throw new Exception("Unexpected token");
            }
        }

        while(!operationsStack.empty()){
            result.add(operationsStack.pop());
        }
        return result;
    }
}
