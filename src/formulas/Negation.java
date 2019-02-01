package formulas;

import model.Sequence;

public class Negation extends UnaryOperation {

    public Negation(formulas.Formula formula) {
        super(formula);
    }

    public Negation(formulas.Formula formula, Boolean logicalValue) {
        super(formula);
        this.logicalValue = logicalValue;
    }

    @Override
    public int Priority() {
        return 4;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {

        Sequence[] result = new Sequence[1];
        Sequence newSequence = sequence.clone();

        Formula newFormula = formula.clone();
        newFormula.logicalValue = !this.logicalValue;

        newSequence.formulas.add(newFormula);

        result[0] = newSequence;

        return result;
    }

    @Override
    public Formula clone() {
        return new Negation(formula.clone(), logicalValue);
    }

    @Override
    public String getSymbol() {
        return "!";
    }
}
