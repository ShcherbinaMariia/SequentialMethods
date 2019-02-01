package formulas;

import model.Sequence;

public class Implication extends BinaryOperation {

    public Implication(Formula left, Formula right) {
        super(left, right);
    }

    public Implication(formulas.Formula left, formulas.Formula right, Boolean logicalValue) {
        super(left, right);
        this.logicalValue = logicalValue;
    }

    @Override
    public int Priority() {
        return 1;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {
        Formula newRight = right.clone();
        Formula newLeft = left.clone();


        newRight.logicalValue = this.logicalValue;

        if (!this.logicalValue) {

            Sequence[] result = new Sequence[1];
            newLeft.logicalValue = true;
            newRight.logicalValue = false;

            Sequence newSequence = sequence.clone();
            newSequence.formulas.add(newLeft);
            newSequence.formulas.add(newRight);

            result[0] = newSequence;

            return result;

        } else {
            Sequence[] result = new Sequence[2];

            newLeft.logicalValue = false;
            newRight.logicalValue = true;

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
        return new Implication(left.clone(), right.clone(), logicalValue);
    }


    @Override
    public String getSymbol() {
        return "->";
    }
}
