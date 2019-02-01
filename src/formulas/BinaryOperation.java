package formulas;

import java.util.Set;

public abstract class BinaryOperation extends Formula{

    public Formula left;
    public Formula right;

    public BinaryOperation(Formula left, Formula right){
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isAtomic() {
        return false;
    }

    @Override
    public void rename(Character from, Character to) {
        left.rename(from, to);
        right.rename(from, to);
    }

    @Override
    public Set<Character> Free() {
        Set<Character> union = left.Free();
        union.addAll(right.Free());
        return union;
    }

    @Override
    public String getPresentation(int priority){
        String result = left.getPresentation(this.Priority()) + getSymbol() + right.getPresentation(this.Priority());
        if (priority > this.Priority()){
            return getLogicalValuePresentation() + '(' + result + ')';
        }
        return getLogicalValuePresentation() + result;

    }
}
