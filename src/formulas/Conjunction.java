package formulas;

import model.Sequence;

public class Conjunction extends BinaryOperation{

    public Conjunction(Formula left, Formula right) {
        super(left, right);
    }

    public Conjunction(Formula left, Formula right, Boolean logicalValue) {
        super(left, right);
        this.logicalValue = logicalValue;
    }

    @Override
    public int Priority() {
        return 3;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {

        Formula newRight = right.clone();
        Formula newLeft = left.clone();

        newLeft.logicalValue = this.logicalValue;
        newRight.logicalValue = this.logicalValue;

        if (this.logicalValue) {

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
        return new Conjunction(left.clone(), right.clone(), logicalValue);
    }

    @Override
    public String getSymbol() {
        return "&";
    }
}
