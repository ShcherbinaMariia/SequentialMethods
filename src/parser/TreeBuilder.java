package parser;

import formulas.*;
import parser.tokens.PredicateToken;
import parser.tokens.QuantifierToken;
import parser.tokens.Token;

import java.util.List;
import java.util.Stack;

public class TreeBuilder {

    static Formula build(List<Token> expressionRPN) throws Exception {

        Stack<Formula> stack = new Stack<>();
        if (expressionRPN.isEmpty()) return null;

        int currentIndex = 0;

        while (currentIndex < expressionRPN.size()){
            Token token = expressionRPN.get(currentIndex);
            switch(token.type){

                case PREDICATE:
                    stack.push(new Predicate((PredicateToken) token));
                    break;
                case NEGATION:
                    stack.push(new Negation(stack.pop()));
                    break;
                case FOR_ALL:
                    stack.push(new ForAll((QuantifierToken) token, stack.pop()));
                    break;
                case EXISTS:
                    stack.push(new Exists((QuantifierToken) token, stack.pop()));
                    break;
                case CONJUNCTION:
                    stack.push(new Conjunction(stack.pop(), stack.pop()));
                    break;
                case DISJUNCTION:
                    stack.push(new Disjunction(stack.pop(), stack.pop()));
                    break;
                case IMPLICATION:
                    Formula right = stack.pop();
                    Formula left = stack.pop();
                    stack.push(new Implication(left, right));
                    break;
            }
            currentIndex++;
        }
        if (stack.size() != 1)
            throw new Exception("Wrong syntax");

        return stack.pop();
    }
}
