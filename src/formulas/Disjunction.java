package formulas;

import model.Sequence;


public class Disjunction extends BinaryOperation {

    public Disjunction(formulas.Formula left, formulas.Formula right) {
        super(left, right);
    }

    public Disjunction(formulas.Formula left, formulas.Formula right, Boolean logicalValue) {
        super(left, right);
        this.logicalValue = logicalValue;
    }

    @Override
    public int Priority() {
        return 2;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {

        Formula newRight = right.clone();
        Formula newLeft = left.clone();

        newLeft.logicalValue = this.logicalValue;
        newRight.logicalValue = this.logicalValue;

        if (!this.logicalValue) {

            Sequence[] result = new Sequence[1];
            Sequence newSequence = sequence.clone();
            newSequence.formulas.add(newLeft);
            newSequence.formulas.add(newRight);

            result[0] = newSequence;

            return result;

        } else {
            Sequence[] result = new Sequence[2];

            Sequence newSequenceLeft = sequence.clone();
            Sequence newSequenceRight = sequence.clone();

            newSequenceLeft.formulas.add(newLeft);
            newSequenceRight.formulas.add(newRight);

            result[0] = newSequenceLeft;
            result[1] = newSequenceRight;

            return result;
        }
    }
    @Override
    public Formula clone() {
        return new Disjunction(left.clone(), right.clone(), logicalValue);
    }

    @Override
    public String getSymbol() {
        return "|";
    }
}
