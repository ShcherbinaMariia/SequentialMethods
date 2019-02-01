package formulas;

import java.util.Set;

public abstract class UnaryOperation extends Formula {

    public Formula formula;

    public UnaryOperation(Formula formula){
        this.formula = formula;
    }

    @Override
    public boolean isAtomic() {
        return false;
    }

    @Override
    public void rename(Character from, Character to) {
        formula.rename(from, to);
    }

    @Override
    public Set<Character> Free() {
        return formula.Free();
    }

    @Override
    public String getPresentation(int priority){
        String result = getSymbol() + formula.getPresentation(this.Priority());
        if (priority > this.Priority())
            return getLogicalValuePresentation() + '(' + result + ')';
        return getLogicalValuePresentation() + result;
    }
}
