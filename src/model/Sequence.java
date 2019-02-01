package model;

import formulas.Formula;
import formulas.Predicate;

import java.util.LinkedList;

public class Sequence {

    public LinkedList<Formula> formulas;

    public Sequence(LinkedList<Formula> formulas) {
        this.formulas = formulas;
    }

    public boolean isLeaf() {

        for (int i = 0; i < formulas.size(); i++) {
            if (!formulas.get(i).isAtomic())
                return false;
        }
        return true;

    }

    public Sequence[] transform() throws Exception {

        for (int i = 0; i < formulas.size(); i++) {

            if (!formulas.get(i).isAtomic()) {

                Sequence newSequence = this.clone();
                newSequence.formulas.remove(i);

                return formulas.get(i).applySequentialForm(newSequence);
            }
        }
        throw new Exception("Nothing to expand");
    }

    public boolean isClosed() {

        for (int i = 0; i < formulas.size(); i++) {
            if (formulas.get(i) instanceof Predicate) {
                for (int j = 0; j < formulas.size(); j++) {
                    if (i != j && (formulas.get(j) instanceof Predicate)) {
                        Formula formula = formulas.get(j).clone();
                        formula.logicalValue = !formula.logicalValue;
                        if (((Predicate) formulas.get(i)).equals(formula)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getPresentation() {

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < formulas.size() - 1; i++)
            ans.append(formulas.get(i).getPresentation()).append(", ");

        ans.append(formulas.get(formulas.size() - 1).getPresentation());

        return ans.toString();
    }

    public Sequence clone() {
        return new Sequence(new LinkedList<>(formulas));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sequence))
            return false;
        Sequence other = (Sequence) obj;
        return other.formulas.equals(this.formulas);
    }
}
