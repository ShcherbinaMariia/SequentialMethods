package formulas;

import parser.tokens.QuantifierToken;
import java.util.Set;

public abstract class Quantifier extends Formula{

    Character variable;
    Formula formula;

    public Quantifier(){}

    public Quantifier(QuantifierToken token, Formula formula){
        this.variable = token.variable;
        this.formula = formula;
    }

    @Override
    public int Priority(){
        return 4;
    }

    @Override
    public boolean isAtomic() {
        return false;
    }

    @Override
    public void rename(Character from, Character to) {
        if (variable != from)
            formula.rename(from, to);
    }

    @Override
    public Set<Character> Free() {
        Set<Character> formulaVariables = formula.Free();
        if (formulaVariables.contains(variable))
            formulaVariables.add(variable);
        return formulaVariables;
    }

    @Override
    public String getPresentation(int priority){
        String result = getSymbol() + variable + formula.getPresentation(this.Priority());
        if (priority > this.Priority())
            return getLogicalValuePresentation() + '(' + result + ')';
        return getLogicalValuePresentation() + result;
    }

    public Character getNextName(Set<Character> Free){
        for (char i = 'a'; i<='z'; i++){
            if (!Free.contains(i))
                return i;
        }
        return 'a';
    }
}
