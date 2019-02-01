package formulas;

import model.Sequence;
import parser.tokens.PredicateToken;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Predicate extends formulas.Formula {

    Character name;
    List<Character> variables;

    public Predicate(PredicateToken token) {
        this.name = token.name;
        this.variables = token.variables;
    }

    public Predicate(Character name, List<Character> variables) {
        this.name = name;
        this.variables = variables;
    }

    public Predicate(Character name, List<Character> variables, Boolean logicalValue) {
        this.name = name;
        this.variables = variables;
        this.logicalValue = logicalValue;
    }


    @Override
    public int Priority() {
        return 5;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {
        return new Sequence[0];
    }

    @Override
    public boolean isAtomic() {
        return true;
    }

    @Override
    public void rename(Character from, Character to) {

        for (int i = 0; i < variables.size(); i++)
            if (variables.get(i).charValue() == from.charValue())
                variables.set(i, to);
    }

    @Override
    public Set<Character> Free() {
        return new HashSet<>(variables);
    }

    @Override
    public Formula clone() {
        return new Predicate(new Character(name), new LinkedList<>(variables), logicalValue);
    }

    @Override
    public String getPresentation(int priority) {
        return getLogicalValuePresentation() + getSymbol();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Predicate)) return false;
        Predicate other = (Predicate) obj;

        if (this.logicalValue.booleanValue() != other.logicalValue.booleanValue())
            return false;

        if (this.name.charValue() != other.name.charValue())
            return false;

        if (this.variables.size() != other.variables.size())
            return false;

        for (int i = 0; i < this.variables.size(); i++) {
            if (this.variables.get(i).charValue() != other.variables.get(i).charValue())
                return false;
        }
        return true;
    }

    @Override
    public String getSymbol() {
        return name + "" + variables;
    }
}
