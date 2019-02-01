package parser;

import formulas.Formula;

public class Parser {

    public Formula parse(String expression) throws Exception {

        return TreeBuilder.build(new RPNConverter(expression).getRPN());
    }
}
