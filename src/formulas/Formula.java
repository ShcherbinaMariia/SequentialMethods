package formulas;

import model.Sequence;

import java.util.Set;

public abstract class Formula implements Cloneable{

    public Boolean logicalValue = null;

    Formula(){}

    public abstract int Priority();

    public abstract Sequence[] applySequentialForm(Sequence sequence);

    public abstract boolean isAtomic();

    public abstract void rename(Character from, Character to);

    public abstract Set<Character> Free();

    public abstract Formula clone();

    public abstract String getPresentation(int priority);

    public String getPresentation() { return getPresentation(-1); }

    public abstract String getSymbol();

    public String getLogicalValuePresentation(){
        if (logicalValue == null) return "";
        if (logicalValue)
            return "|-";
        return "-|";
    }
}
