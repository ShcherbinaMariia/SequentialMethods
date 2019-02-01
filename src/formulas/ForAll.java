package formulas;

import model.Sequence;
import parser.tokens.QuantifierToken;

import java.util.Set;

public class ForAll extends Quantifier{

    public ForAll(QuantifierToken token, Formula formula){
        super(token, formula);
    }

    public ForAll(Character variable, Formula formula){
        this.variable = variable;
        this.formula = formula;
    }

    public ForAll(Character variable, Formula formula, Boolean logicalValue){
        this.variable = variable;
        this.formula = formula;
        this.logicalValue = logicalValue;
    }

    @Override
    public Sequence[] applySequentialForm(Sequence sequence) {

        Sequence[] result = new Sequence[1];
        Sequence newSequence = sequence.clone();
        Set<Character> free = this.Free();
        sequence.formulas.forEach(formula -> free.addAll(formula.Free()));

        if (logicalValue){

            free.forEach(name -> {
                Formula newFormula = formula.clone();
                newFormula.logicalValue = logicalValue;
                newFormula.rename(variable, name);
                newSequence.formulas.add(newFormula);
            });

            newSequence.formulas.add(this);
        }
        else{

            Character newName = getNextName(free);
            Formula newFormula = formula.clone();
            newFormula.logicalValue = logicalValue;
            newFormula.rename(variable, newName);
            newSequence.formulas.add(newFormula);
        }
        result[0] = newSequence;
        return result;
    }
    @Override
    public Formula clone() {
        return new ForAll(new Character(variable), formula.clone(), this.logicalValue);
    }

    @Override
    public String getSymbol() {
        return "@";
    }
}
