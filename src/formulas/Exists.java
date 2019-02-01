package formulas;

import model.Sequence;
import parser.tokens.QuantifierToken;

import java.util.Set;

public class Exists extends Quantifier{

    public Exists(Character variable, Formula formula){
        this.variable = variable;
        this.formula = formula;
    }

    public Exists(QuantifierToken token, formulas.Formula formula){
        super(token, formula);
    }

    public Exists(Character variable, Formula formula, Boolean logicalValue){
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

            Character newName = getNextName(free);
            Formula newFormula = formula.clone();
            newFormula.rename(variable, newName);
            newFormula.logicalValue = logicalValue;
            newSequence.formulas.add(newFormula);
        }
        else{

            free.forEach(name -> {
                Formula newFormula = formula.clone();
                newFormula.rename(variable, name);
                newFormula.logicalValue = logicalValue;
                newSequence.formulas.add(newFormula);
            });

            newSequence.formulas.add(this);

        }
        result[0] = newSequence;
        return result;
    }

    public Formula clone() {
        return new Exists(new Character(variable), formula.clone(), logicalValue);
    }

    @Override
    public String getSymbol() {
        return "#";
    }
}
